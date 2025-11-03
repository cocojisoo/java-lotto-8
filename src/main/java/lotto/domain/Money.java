package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public final class Money {
    private static final long ZERO_AMOUNT = 0L;

    private final long amount;

    private Money(long amount) {
        validateNonNegative(amount);
        this.amount = amount;
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public long getAmount() {
        return amount;
    }

    public Money plus(Money other) {
        return new Money(Math.addExact(this.amount, other.amount));
    }

    public Money multiply(long times) {
        return new Money(Math.multiplyExact(this.amount, times));
    }

    public boolean isZero() {
        return amount == ZERO_AMOUNT;
    }

    public String formatRateAgainst(Money base) {
        if (base.amount == 0L) {
            return formatPercentage(0.0);
        }
        BigDecimal prize = BigDecimal.valueOf(this.amount);
        BigDecimal purchase = BigDecimal.valueOf(base.amount);
        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal ratio = prize
                .multiply(hundred)
                .divide(purchase, 2, RoundingMode.HALF_UP);
        return formatPercentage(ratio.doubleValue());
    }

    private String formatPercentage(double value) {
        DecimalFormat format = new DecimalFormat("#,##0.0'%'");
        return format.format(value);
    }

    private void validateNonNegative(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0 이상이어야합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}


