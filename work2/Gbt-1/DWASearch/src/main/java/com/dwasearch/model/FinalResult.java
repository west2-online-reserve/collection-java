package com.dwasearch.model;

import java.math.BigDecimal;
import java.util.List;

public class FinalResult {
    private final String fullName;
    private final int rank;
    private final List<BigDecimal> diveScores;
    private final BigDecimal totalScore;

    public FinalResult(String fullName,
                       int rank,
                       List<BigDecimal> diveScores,
                       BigDecimal totalScore) {
        this.fullName = fullName;
        this.rank = rank;
        this.diveScores = List.copyOf(diveScores);
        this.totalScore = totalScore;
    }

    public String getFullName() {
        return fullName;
    }

    public int getRank() {
        return rank;
    }

    public List<BigDecimal> getDiveScores() {
        return diveScores;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }
}