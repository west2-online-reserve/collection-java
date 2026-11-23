package com.diving.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String type;       // "player" 或 "result"
    private String name;       // 对应 player 的 fullName，或 result 的 diverName
    private String gender;
    private String country;
    private String event;      // 比赛项目
    private int rank;          // 排名
    private double totalScore; // 总分
    private List<Double> scores = new ArrayList<>(); // 记录每一跳得分

    public Player() {}

    // 创建选手基础信息
    public Player(String name, String gender, String country) {
        this.type = "player";
        this.name = name;
        this.gender = gender;
        this.country = country;
    }

    // 创建比赛成绩信息
    public Player(String event, int rank, String name, List<Double> scores, double totalScore) {
        this.type = "result";
        this.event = event;
        this.rank = rank;
        this.name = name;
        this.scores = scores;
        this.totalScore = totalScore;
    }

    // 获取数据类型
    public String getType() { return type; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getCountry() { return country; }
    public String getEvent() { return event; }
    public int getRank() { return rank; }
    public double getTotalScore() { return totalScore; }
    public List<Double> getScores() { return scores; }
}