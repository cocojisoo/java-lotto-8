package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("경계값 1, 45 생성 테스트")
    void boundary_ok() {
        assertThat(LottoNumber.of(1).getValue()).isEqualTo(1);
        assertThat(LottoNumber.of(45).getValue()).isEqualTo(45);
    }

    @Test
    @DisplayName("0 이하 예외처리")
    void below_min_throws() {
        assertThatThrownBy(() -> LottoNumber.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 당첨 번호는 (1 ~ 45)사이의 숫자여야합니다.");
    }

    @Test
    @DisplayName("45 초과 예외처리")
    void above_max_throws() {
        assertThatThrownBy(() -> LottoNumber.of(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 당첨 번호는 (1 ~ 45)사이의 숫자여야합니다.");
    }
}