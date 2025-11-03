package lotto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.Money;

public final class Parser {
    private Parser() {
    }

    public static Money parsePurchaseMoney(String input) {
        Validator.requireNonBlank(input, "[ERROR] 구입 금액에는 숫자만 포함되어야합니다.");
        String trimmed = input.trim();
        Validator.requireNumeric(trimmed, "[ERROR] 구입 금액에는 숫자만 포함되어야합니다.");
        long amount = Long.parseLong(trimmed);
        Validator.requireNonNegative(amount, "[ERROR] 구입 금액은 0 이상이어야합니다.");
        return Money.of(amount);
    }

    public static List<LottoNumber> parseCsvWinningNumbers(String input) {
        Validator.requireNonBlank(input, "[ERROR] 당첨 번호에는 숫자만 포함되어야합니다.");
        List<Integer> ints = splitToInts(input);
        List<LottoNumber> numbers = ints.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        Validator.requireSize(numbers, 6, "[ERROR] 당첨 번호는 6개이어야합니다.");
        Validator.requireNoDuplicates(numbers, "[ERROR] 당첨 번호는 서로 중복되지 않아야합니다.");
        return numbers;
    }

    public static LottoNumber parseBonusNumber(String input, List<LottoNumber> winningNumbers) {
        Validator.requireNonBlank(input, "[ERROR] 보너스 번호에는 숫자만 포함되어야합니다.");
        String trimmed = input.trim();
        Validator.requireNumeric(trimmed, "[ERROR] 보너스 번호는 숫자만 포함되어야합니다.");
        int value = Integer.parseInt(trimmed);
        LottoNumber bonus = LottoNumber.of(value);
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복되선 안됩니다.");
        }
        return bonus;
    }

    private static List<Integer> splitToInts(String input) {
        String[] tokens = input.split(",");
        List<Integer> result = new ArrayList<>();
        for (String token : tokens) {
            String t = token.trim();
            Validator.requireNonBlank(t, "[ERROR] 당첨 번호에는 숫자만 포함되어야합니다.(공백 문자 포함 불가)");
            Validator.requireNumeric(t, "[ERROR] 당첨 번호에는 숫자만 포함되어야합니다.");
            result.add(Integer.parseInt(t));
        }
        return result;
    }
}


