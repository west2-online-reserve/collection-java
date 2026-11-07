package org.west2.learning;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class QueryEngine {
    public static final Set<String> EVENT_NAMES = Set.copyOf(new LinkedHashSet<>(List.of(
            "women 3m springboard", "women 10m platform", "women 3m synchronised",
            "women 10m synchronised", "men 3m springboard", "men 10m platform",
            "men 3m synchronised", "men 10m synchronised")));

    private final ContestData data;

    public QueryEngine(ContestData data) {
        this.data = data;
    }

    /** An empty line is ignored. Every other input line generates a complete block. */
    public String execute(List<String> commands) {
        StringBuilder output = new StringBuilder();
        for (String command : commands) {
            if (command.isEmpty()) continue;
            if (command.equals("players")) {
                List<ContestData.Player> players = new ArrayList<>(data.players());
                players.sort(Comparator.comparing(ContestData.Player::country)
                        .thenComparing(ContestData.Player::lastName)
                        .thenComparing(ContestData.Player::fullName));
                for (ContestData.Player player : players) {
                    output.append("Full Name:").append(player.fullName()).append('\n')
                            .append("Gender:").append(player.gender()).append('\n')
                            .append("Country:").append(player.country()).append("\n-----\n");
                }
            } else if (command.equals("result") || command.startsWith("result ")) {
                String name = command.equals("result") ? "" : command.substring(7);
                if (!EVENT_NAMES.contains(name)) {
                    output.append("N/A\n-----\n");
                    continue;
                }
                List<ContestData.Result> results = new ArrayList<>(data.events().get(name));
                results.sort(Comparator.comparingInt(ContestData.Result::rank));
                for (ContestData.Result result : results) {
                    output.append("Full Name:").append(result.fullName()).append('\n')
                            .append("Rank:").append(result.rank()).append('\n')
                            .append("Score:");
                    List<String> scores = result.scores().stream().map(QueryEngine::twoDecimals).toList();
                    output.append(String.join(" + ", scores)).append(" = ")
                            .append(twoDecimals(result.total())).append("\n-----\n");
                }
            } else {
                output.append("Error\n-----\n");
            }
        }
        return output.toString();
    }

    private static String twoDecimals(String value) {
        return new BigDecimal(value).setScale(2, RoundingMode.UNNECESSARY).toPlainString();
    }
}
