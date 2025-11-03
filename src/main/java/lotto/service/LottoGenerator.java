package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoTicket;

public class LottoGenerator {
    public List<LottoTicket> generate(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            tickets.add(LottoTicket.ofIntegers(numbers));
        }
        return tickets;
    }
}


