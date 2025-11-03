package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class WinningNumbers {
    private static final int REQUIRED_SIZE = 6;

    private final List<LottoNumber> numbers; 
    private final LottoNumber bonus;

    private WinningNumbers(List<LottoNumber> numbers, LottoNumber bonus) {
        validate(numbers, bonus);
        List<LottoNumber> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        this.numbers = Collections.unmodifiableList(sorted);
        this.bonus = bonus;
    }

    public static WinningNumbers of(List<LottoNumber> numbers, LottoNumber bonus) {
        return new WinningNumbers(numbers, bonus);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

    private void validate(List<LottoNumber> candidates, LottoNumber bonus) {
        if (candidates == null || bonus == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호 또는 보너스 번호가 유효하지 않습니다.");
        }
        if (candidates.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개이어야합니다.");
        }
        Set<LottoNumber> unique = new HashSet<>(candidates);
        if (unique.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 서로 중복되지 않아야합니다.");
        }
        if (unique.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복되선 안됩니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningNumbers)) return false;
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(numbers, that.numbers) && Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, bonus);
    }

    @Override
    public String toString() {
        return numbers.toString() + " + " + bonus.getValue();
    }
}


