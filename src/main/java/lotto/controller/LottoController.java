package lotto.controller;

import java.util.List;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import lotto.service.LottoGenerator;
import lotto.service.LottoJudge;
import lotto.service.ProfitCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator generator;
    private final LottoJudge judge;
    private final ProfitCalculator profitCalculator;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator generator,
                           LottoJudge judge, ProfitCalculator profitCalculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
        this.judge = judge;
        this.profitCalculator = profitCalculator;
    }

    public void run() {
        outputView.printPurchasePrompt();
        Money purchase = readPurchaseMoneyWithRetry();

        int ticketCount = (int) (purchase.getAmount() / 1_000);
        List<LottoTicket> tickets = generator.generate(ticketCount);
        outputView.printPurchaseCount(ticketCount);
        outputView.printTickets(tickets);

        outputView.printWinningPrompt();
        List<LottoNumber> winningNumbers = readWinningNumbersWithRetry();
        outputView.printBonusPrompt();
        LottoNumber bonus = readBonusNumberWithRetry(winningNumbers);

        WinningNumbers winning = WinningNumbers.of(winningNumbers, bonus);
        Result result = judge.judge(tickets, winning);
        String rate = profitCalculator.calculateRate(result, purchase);
        outputView.printStatistics(result, rate);
    }

    private Money readPurchaseMoneyWithRetry() {
        while (true) {
            try {
                return inputView.readPurchaseMoney();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<LottoNumber> readWinningNumbersWithRetry() {
        while (true) {
            try {
                return inputView.readWinningNumbers();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LottoNumber readBonusNumberWithRetry(List<LottoNumber> winningNumbers) {
        while (true) {
            try {
                return inputView.readBonusNumber(winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}


