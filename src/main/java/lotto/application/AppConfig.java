package lotto.application;

import lotto.controller.LottoController;
import lotto.service.LottoGenerator;
import lotto.service.LottoJudge;
import lotto.service.ProfitCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public LottoController lottoController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator generator = new LottoGenerator();
        LottoJudge judge = new LottoJudge();
        ProfitCalculator profitCalculator = new ProfitCalculator();
        return new LottoController(inputView, outputView, generator, judge, profitCalculator);
    }
}


