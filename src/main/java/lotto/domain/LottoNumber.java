package lotto.domain;
import java.util.Objects;

public final class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final LottoNumber[] CACHE = new LottoNumber[MAX_NUMBER + 1];

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            CACHE[i] = new LottoNumber(i);
        }
    }

    private final int value;

    private LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public static LottoNumber of(int value) {
        validateRange(value);
        return CACHE[value];
    }

    public int getValue() {
        return value;
    }

    private static void validateRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
