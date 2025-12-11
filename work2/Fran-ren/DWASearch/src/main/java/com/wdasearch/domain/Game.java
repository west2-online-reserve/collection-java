package com.wdasearch.domain;

public class Game {
    private String type;
    private final Score[] scores = new Score[3];//0:预赛 1:半决赛 2:决赛
    private String competitor;

    public Game(String type) {
        this.type = type;
    }

    public Score getScore(int stage) {
        return scores[stage];
    }

    public int getRank(int stage) {
        return scores[stage].getRank();
    }

    public void setScore(String state, Score score) {
        int i = switch (state) {
            case "Final" -> 2;
            case "Semifinal" -> 1;
            case "Preliminary" -> 0;
            default -> -1;
        };
        this.scores[i] = score;
    }

    public String getType() {
        return type;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }
}
