package top.yangguangmc.dwasearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public record Result(
        String playerFullName,
        int rank,
        List<BigDecimal> scores
) implements Comparable<Result> {

    @Override
    public int compareTo(Result o) {
        return Comparator.comparingInt(Result::rank).compare(this, o);
    }

    public void print(IODataSet out) {
        out.println("Full Name:%s", playerFullName)
                .println("Rank:%d", rank)
                .println(
                        "Score:%s = %s",
                        scores.stream()
                                .map(bigDecimal -> bigDecimal.setScale(2, RoundingMode.HALF_UP).toPlainString())
                                .collect(Collectors.joining(" + ")),
                        scores.stream()
                                .reduce(BigDecimal::add)
                                .orElseThrow()
                                .setScale(2, RoundingMode.HALF_UP)
                                .toPlainString()
                );
    }
}
