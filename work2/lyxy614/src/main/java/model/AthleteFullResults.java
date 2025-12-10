package main.java.model;

import java.util.List;
// 辅助类：存储单个运动员的所有阶段结果
public class AthleteFullResults {
    // 三个阶段的排名（按初赛→半决赛→决赛顺序）
    private String preliminaryRank = "*";
    private String semifinalRank = "*";
    private String finalRank = "*";

    // 三个阶段的得分信息（得分列表+总分）
    //得分信息有两部分，创建一个内部类StageScore封装
    private StageScore preliminaryScore;
    private StageScore semifinalScore;
    private StageScore finalScore;

    public String getPreliminaryRank() {
        return preliminaryRank;
    }
    public void setPreliminaryRank(String preliminaryRank) {
        this.preliminaryRank = preliminaryRank;
    }
    public String getSemifinalRank() {
        return semifinalRank;
    }
    public void setSemifinalRank(String semifinalRank) {
        this.semifinalRank = semifinalRank;
    }
    public String getFinalRank() {
        return finalRank;
    }
    public void setFinalRank(String finalRank) {
        this.finalRank = finalRank;
    }

    public StageScore getPreliminaryScore() {
        return preliminaryScore;
    }
    public void setPreliminaryScore(StageScore preliminaryScore) {
        this.preliminaryScore = preliminaryScore;
    }
    public StageScore getSemifinalScore() {
        return semifinalScore;
    }
    public void setSemifinalScore(StageScore semifinalScore) {
        this.semifinalScore = semifinalScore;
    }
    public StageScore getFinalScore() {
        return finalScore;
    }
    public void setFinalScore(StageScore finalScore) {
        this.finalScore = finalScore;
    }
    @Override
    public String toString(){
        StringBuilder detailResult = new StringBuilder();
        detailResult.append("Rank:" + preliminaryRank + " | " +  semifinalRank + " | " + finalRank + "\n");
        if (preliminaryScore == null){
            detailResult.append("Preliminary Score:" + "*" + "\n");
        }
        else {
            detailResult.append("Preliminary Score:" + preliminaryScore + "\n");
        }
        if (semifinalScore == null){
            detailResult.append("Semifinal Score:" + "*" + "\n");
        }
        else {
            detailResult.append("Semifinal Score:" + semifinalScore + "\n");
        }
        if (finalScore == null){
            detailResult.append("Final Score:" + "*" + "\n");
        }
        else {
            detailResult.append("Final Score:" + finalScore + "\n");
        }
        return detailResult.toString();
    }

    // 内部类：存储单个阶段的得分和总分
    public static class StageScore {
        List<Dives> dives;
        Double totalScore;

        public StageScore(List<Dives> dives, Double totalScore) {
            this.dives = dives;
            this.totalScore = totalScore;
        }
        //顺便完成小分的拼接
        @Override
        public String toString(){
            StringBuilder stageScore = new StringBuilder();
            for (int i = 0; i < dives.size(); i++){
                Dives dive =  dives.get(i);
                if (i == dives.size() - 1){
                    stageScore.append(dive.getDivePoints() + " = ");
                }
                else {
                    stageScore.append(dive.getDivePoints() + " + ");
                }
            }
            stageScore.append(totalScore);
            return stageScore.toString();
        }
    }

}
