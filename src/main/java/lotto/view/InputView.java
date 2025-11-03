package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.util.Parser;

public class InputView {
    public Money readPurchaseMoney() {
        String line = Console.readLine();
        return Parser.parsePurchaseMoney(line);
    }

    public List<LottoNumber> readWinningNumbers() {
        String line = Console.readLine();
        return Parser.parseCsvWinningNumbers(line);
    }

    public LottoNumber readBonusNumber(List<LottoNumber> winningNumbers) {
        String line = Console.readLine();
        return Parser.parseBonusNumber(line, winningNumbers);
    }
}


