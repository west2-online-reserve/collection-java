package org.uzk20.domain;

import org.uzk20.utils.Constants;

public class EventDetailedResult {
    private String fullName;
    private String rank;
    private String preliminaryScore;
    private String semifinalScore;
    private String finalScore;

    public EventDetailedResult(SingleMatchResult preliminaryResult, SingleMatchResult semifinalResult, SingleMatchResult finalResult){
        if (preliminaryResult != null) {
            this.fullName = preliminaryResult.getFullName();
        } else if (semifinalResult != null) {
            this.fullName = semifinalResult.getFullName();
        } else {
            this.fullName = finalResult.getFullName();
        }
        String preRank=preliminaryResult!=null?preliminaryResult.getRank():Constants.PLACEHOLDER;
        String semiRank = semifinalResult != null ? semifinalResult.getRank() : Constants.PLACEHOLDER;
        String finalRank = finalResult != null ? finalResult.getRank() : Constants.PLACEHOLDER;
        this.rank = String.format("%s | %s | %s", preRank, semiRank, finalRank);

        this.preliminaryScore = preliminaryResult != null ? preliminaryResult.getDiveScore().toFormattedString() : Constants.PLACEHOLDER;
        this.semifinalScore = semifinalResult != null ? semifinalResult.getDiveScore().toFormattedString() : Constants.PLACEHOLDER;
        this.finalScore = finalResult != null ? finalResult.getDiveScore().toFormattedString() : Constants.PLACEHOLDER;
    }

    public String toDetailedResultString(){
        return String.format("Full Name:%s\nRank:%s\nPreliminary Score:%s\nSemifinal Score:%s\nFinal Score:%s\n%s", fullName, rank, preliminaryScore, semifinalScore, finalScore, Constants.SEPARATOR);
    }

    public String getFullName() {
        return fullName;
    }

    public String getRank() {
        return rank;
    }

    public String getPreliminaryScore() {
        return preliminaryScore;
    }

    public String getSemifinalScore() {
        return semifinalScore;
    }

    public String getFinalScore() {
        return finalScore;
    }
}
