package lotto.view;

import java.text.DecimalFormat;
import java.util.List;

import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView {
    public void printPurchasePrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printWinningPrompt() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusPrompt() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket.toString() + " ");
        }
    }

    public void printStatistics(Result result, String rate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printRankLine(Rank.FIFTH, result.getCount(Rank.FIFTH));
        printRankLine(Rank.FOURTH, result.getCount(Rank.FOURTH));
        printRankLine(Rank.THIRD, result.getCount(Rank.THIRD));
        printSecondLine(result.getCount(Rank.SECOND));
        printRankLine(Rank.FIRST, result.getCount(Rank.FIRST));
        System.out.println("총 수익률은 " + rate + "입니다.");
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printRankLine(Rank rank, int count) {
        int match = rank.getMatchCount();
        String prize = formatCurrency(rank.getPrize());
        System.out.println(match + "개 일치 (" + prize + "원) - " + count + "개");
    }

    private void printSecondLine(int count) {
        String prize = formatCurrency(Rank.SECOND.getPrize());
        System.out.println("5개 일치, 보너스 볼 일치 (" + prize + "원) - " + count + "개");
    }

    private String formatCurrency(long amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(amount);
    }
}


