package core.lib;

import core.data.Player;
import core.data.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataOutput {
    // 一些输出格式约束 均有换行
    public static String outputLine(int len) {
        return "-".repeat(len)+"\n";
    }

    public static String outputLine() {
        return outputLine(5);
    }

    // about Player选手信息
    public static String outputPlayerName(Player p) {
        String t = p.getFullName();
        return "Full Name:"+t+"\n";
    }

    public static String outputPlayerGender(Player p) {
        String t = p.getGender();
        return "Gender:"+t+"\n";
    }

    public static String outputPlayerCountry(Player p) {
        String t = p.getCountry();
        return "Country:"+t+"\n";
    }

    // about 比赛结果
    public static String outputResultPlayerName(Result r) {
        String t = r.getFullName();
        return "Full Name:"+t+"\n";
    }

    public static String outputResultPlayerRank(Result r) {
        String t = r.getFullName();
        return "Rank:"+t+"\n";
    }

    public static String outputResultPlayerScore(Result r) {
        List<Double> scores = r.getScores();
        double totalPoint = r.getTotalPoints();

        String result = scores.stream()
                .map(d -> String.format("%.2f", d))
                .collect(Collectors.joining(" + "));
        return result + " = " + String.format("%.2f",totalPoint) + "\n";
    }
}



