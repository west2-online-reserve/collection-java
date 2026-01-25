package com.wdasearch.domain;

public class Score {
    private final String score;
    private final int rank;

    public Score(String score, int rank) {
        this.score = score;
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }
}
