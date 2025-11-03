package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("수익률: 5000/8000 = 62.5%")
    void rate_example_62_5() {
        Money prize = Money.of(5_000);
        Money purchase = Money.of(8_000);
        assertThat(prize.formatRateAgainst(purchase)).isEqualTo("62.5%");
    }

    @Test
    @DisplayName("수익률: 1000/1000 = 100.0%")
    void rate_100_percent() {
        Money prize = Money.of(1_000);
        Money purchase = Money.of(1_000);
        assertThat(prize.formatRateAgainst(purchase)).isEqualTo("100.0%");
    }

    @Test
    @DisplayName("수익률 반올림: 66.7% ")
    void rate_rounding_half_up() {
        Money prize = Money.of(2);
        Money purchase = Money.of(3);
        assertThat(prize.formatRateAgainst(purchase)).isEqualTo("66.7%");
    }

    @Test
    @DisplayName("큰 값 포맷: 10,000,000.0%")
    void large_number_format() {
        Money prize = Money.of(10_000_000);
        Money purchase = Money.of(1_000);
        assertThat(prize.formatRateAgainst(purchase)).isEqualTo("1,000,000.0%");
    }

    @Test
    @DisplayName("음수 금액 예외처리")
    void negative_amount_throws() {
        assertThatThrownBy(() -> Money.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}


