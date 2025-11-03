package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class Result {
    private final Map<Rank, Integer> rankCounts;

    private Result(Map<Rank, Integer> rankCounts) {
        this.rankCounts = new EnumMap<>(rankCounts);
    }

    public static Result empty() {
        EnumMap<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            map.put(r, 0);
        }
        return new Result(map);
    }

    public static Result fromRanks(List<Rank> ranks) {
        Result result = Result.empty();
        for (Rank rank : ranks) {
            result = result.add(rank);
        }
        return result;
    }

    public Result add(Rank rank) {
        EnumMap<Rank, Integer> next = new EnumMap<>(rankCounts);
        next.put(rank, next.get(rank) + 1);
        return new Result(next);
    }

    public int getCount(Rank rank) {
        return rankCounts.get(rank);
    }

    public Map<Rank, Integer> getCounts() {
        return Collections.unmodifiableMap(rankCounts);
    }

    public Money getTotalPrize() {
        long sum = 0L;
        for (Map.Entry<Rank, Integer> e : rankCounts.entrySet()) {
            sum += e.getKey().getPrize() * e.getValue();
        }
        return Money.of(sum);
    }
}