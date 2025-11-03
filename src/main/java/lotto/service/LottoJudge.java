package lotto.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;

public class LottoJudge {
    public Result judge(List<LottoTicket> tickets, WinningNumbers winning) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            ranks.add(judgeSingle(ticket, winning));
        }
        return Result.fromRanks(ranks);
    }

    private Rank judgeSingle(LottoTicket ticket, WinningNumbers winning) {
        int matchCount = countMatches(ticket.getNumbers(), winning.getNumbers());
        boolean bonusMatched = contains(ticket.getNumbers(), winning.getBonus());
        return Rank.findBy(matchCount, bonusMatched);
    }

    private int countMatches(List<LottoNumber> ticketNumbers, List<LottoNumber> winningNumbers) {
        Set<LottoNumber> set = new HashSet<>(ticketNumbers);
        int count = 0;
        for (LottoNumber n : winningNumbers) {
            if (set.contains(n)) {
                count++;
            }
        }
        return count;
    }

    private boolean contains(List<LottoNumber> numbers, LottoNumber target) {
        for (LottoNumber n : numbers) {
            if (n.equals(target)) {
                return true;
            }
        }
        return false;
    }
}


