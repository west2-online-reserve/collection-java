package com.diving.controller;

import com.diving.model.Player;
import com.diving.service.QueryService;
import java.io.PrintWriter;
import java.util.List;

public class CommandController {
    private final QueryService queryService;
    private final PrintWriter out;

    public CommandController(QueryService queryService) {
        this(queryService, new PrintWriter(System.out, true));
    }

    public CommandController(QueryService queryService, PrintWriter out) {
        this.queryService = queryService;
        this.out = out;
    }

    public void executeCommand(String input) {
        String[] parts = input.split("\\s+");//按空格切分
        String command = parts[0].toLowerCase();

        if ("players".equals(command)) {
            printPlayers();
        } 
        else if ("result".equals(command)) {
            if (parts.length < 4) {
                out.println("N/A");
                return;
            }
            // 提取 "result " 后面的完整项目名
            String projectName = input.substring(7).trim();
            printResults(projectName);
        } 
        else {
            out.println("Error");
        }
    }

    private void printPlayers() {
        List<Player> players = queryService.getAllPlayers();
        if (players.isEmpty()) {
            out.println("N/A");
            return;
        }
        for (Player p : players) {//for-each循环输出每个选手
            out.println("Full Name: " + p.getName());
            out.println("Gender: " + p.getGender());
            out.println("Country: " + p.getCountry());
            out.println("-----");
        }
    }

    private void printResults(String projectName) {
        List<Player> results = queryService.getResultsByProject(projectName);
        if (results.isEmpty()) {
            out.println("N/A");
            return;
        }

        out.println(projectName.toLowerCase());
        //循环输出选手成绩
        for (Player p : results) { 
            String rankStr = getRankSuffix(p.getRank());
            out.println(rankStr + " Full Name: " + p.getName());
            
            // 拼接分数格式
            StringBuilder scoreStr = new StringBuilder("Score: ");
            if (p.getScores().isEmpty()) {
                scoreStr.append("0.00");
            } else {
                for (int i = 0; i < p.getScores().size(); i++) {
                    scoreStr.append(String.format("%.2f", p.getScores().get(i)));//控制格式 保留两位小数
                    if (i < p.getScores().size() - 1) scoreStr.append(" + ");
                }
            }
            scoreStr.append(String.format(" = %.2f", p.getTotalScore()));
            out.println(scoreStr.toString());
            out.println("-----");
        }
    }
    private String getRankSuffix(int rank) {
        if (rank == 1) return "1st";
        if (rank == 2) return "2nd";
        if (rank == 3) return "3rd";
        return rank + "th";
    }
}