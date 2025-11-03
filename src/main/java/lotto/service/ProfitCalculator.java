package lotto.service;

import lotto.domain.Money;
import lotto.domain.Result;

public class ProfitCalculator {
    public String calculateRate(Result result, Money purchase) {
        Money totalPrize = result.getTotalPrize();
        return totalPrize.formatRateAgainst(purchase);
    }
}


