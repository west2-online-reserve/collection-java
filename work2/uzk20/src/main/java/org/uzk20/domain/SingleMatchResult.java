package org.uzk20.domain;

import org.uzk20.utils.Constants;

public class SingleMatchResult {
    private String fullName;
    private String rank;
    private DiveScore diveScore;

    public SingleMatchResult(String fullName, String rank, DiveScore diveScore) {
        this.fullName = fullName;
        if(rank==null||rank.isEmpty()){
            this.rank=Constants.PLACEHOLDER;
        }
        else{
            this.rank=rank;
        }
        this.diveScore = diveScore;
    }

    public String toBasicResultString() {
        return String.format("Full Name:%s\nRank:%s\nScore:%s\n%s", fullName, rank, diveScore.toFormattedString(), Constants.SEPARATOR);
    }

    public String getFullName() {
        return fullName;
    }

    public String getRank() {
        return rank;
    }

    public DiveScore getDiveScore() {
        return diveScore;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setDiveScore(DiveScore diveScore) {
        this.diveScore = diveScore;
    }
}
