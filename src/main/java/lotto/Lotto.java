package lotto;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 중복되지 않아야합니다.");
        }
        for (Integer n : numbers) {
            if (n == null || n < 1 || n > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 (1 ~ 45)사이의 숫자여야 합니다.");
            }
        }
    }
}