package com.org.Lskar.Swimming.utils;

import com.org.Lskar.Swimming.lib.Contest.AthleteResult;
import com.org.Lskar.Swimming.lib.Contest.Dive;
import com.org.Lskar.Swimming.lib.Player;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputData {

    public static String outputPlayersData(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : players) {
            stringBuilder.append(player.toString());
            stringBuilder.append("\n");
            stringBuilder.append("-----");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String buildScoreString(List<Dive> dives, String total) {
        if (dives.isEmpty()) return "*";
        return dives.stream()
                .sorted(Comparator.comparingInt(d -> d.getOrder()))
                .map(d -> d.getPoints())
                .collect(Collectors.joining(" + ")) + " = " + total;
    }

    public static String formatName(String original) {
        if (!original.contains("/")) return original;

        List<String> originalParts = Arrays.asList(original.split("/"));

        return originalParts.stream()
                .map(name -> {
                    String[] parts = name.trim().split(" ");
                    return parts.length > 1 ?
                            parts[0] + " " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) :
                            name;
                })
                .sorted(Comparator.comparing(n -> n.split(" ")[0]))
                .collect(Collectors.joining(" & "));
    }

    public static String outputAthleteResults(List<AthleteResult> athleteResults){
        StringBuilder stringBuilder = new StringBuilder();
        for(AthleteResult athleteResult : athleteResults) {
            stringBuilder.append(athleteResult.outputDetailInfo());
        }
        return stringBuilder.toString();
    }

    public static String outputAthleteSimpleResults(List<AthleteResult> athleteResults){
        StringBuilder stringBuilder = new StringBuilder();
        for(AthleteResult athleteResult : athleteResults) {
            stringBuilder.append(athleteResult.outputSimpleInfo());
        }
        return stringBuilder.toString();
    }


}
