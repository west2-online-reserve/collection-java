package com.dwa.Lib;

public class Detail {
    private String fullName;
    private String rank = "*|*|*";
    private String preliminaryScore = "*";
    private String semifinalScore = "*";
    private String finalScore = "*";

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPreliminaryScore() {
        return preliminaryScore;
    }

    public void setPreliminaryScore(String preliminaryScore) {
        this.preliminaryScore = preliminaryScore;
    }

    public String getSemifinalScore() {
        return semifinalScore;
    }

    public void setSemifinalScore(String semifinalScore) {
        this.semifinalScore = semifinalScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }
}

