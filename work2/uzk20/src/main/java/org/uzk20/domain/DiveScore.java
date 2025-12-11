package org.uzk20.domain;

import org.uzk20.utils.Constants;
import java.util.List;

public class DiveScore {
    private List<String> score;
    private String totalScore;

    public DiveScore(List<String> score, String totalScore) {
        this.score = score;
        this.totalScore = totalScore;
    }

    public String toFormattedString(){
        if(score==null||score.isEmpty()){
            return Constants.PLACEHOLDER;
        }
        String scoreStr=String.join(" + ",score);
        return String.format("%s = %s",scoreStr,totalScore);
    }

    public List<String> getScore() {
        return score;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }

}
