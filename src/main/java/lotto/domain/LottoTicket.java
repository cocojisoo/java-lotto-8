package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class LottoTicket {
    private static final int REQUIRED_SIZE = 6;

    private final List<LottoNumber> numbers;

    private LottoTicket(List<LottoNumber> numbers) {
        List<LottoNumber> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        this.numbers = Collections.unmodifiableList(sorted);
    }

    public static LottoTicket ofNumbers(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }

    public static LottoTicket ofIntegers(List<Integer> integers) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (Integer i : integers) {
            numbers.add(LottoNumber.of(i));
        }
        return new LottoTicket(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoTicket)) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        List<Integer> ints = new ArrayList<>();
        for (LottoNumber n : numbers) {
            ints.add(n.getValue());
        }
        return ints.toString();
    }
}


