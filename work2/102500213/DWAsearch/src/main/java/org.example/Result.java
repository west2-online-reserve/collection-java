package org.example;

import java.util.List;
import java.util.ArrayList;

public class Result {
    private String fullName;
    private String totalPoints;
    private int rank;
    private List<String> scores = new ArrayList<>();

    public Result(){}
    public String getFullName(){ return fullName; }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getTotalPoints(){ return totalPoints; }
    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
    public int getRank(){ return rank; }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public List<String> getScores(){ return scores; }
    public void setScores(List<String> scores) {
        this.scores = scores;
    }

    public String getContent(){
        StringBuilder str = new StringBuilder();
        str.append("Full Name:"+fullName+"\n");
        str.append("Rank:"+rank+"\n");
        str.append("Score:"+scores.getFirst());
        for(int i=1;i<scores.size();i++){
            str.append(" + "+scores.get(i));
        }
        str.append(" = "+totalPoints+"\n");
        str.append("-----\n");
        return str.toString();
    }

    public void showResult(){
        System.out.println("Full Name:"+fullName);
        System.out.println("Rank:"+rank);
        System.out.print("Score:"+scores.getFirst());
        for(int i=1;i<scores.size();i++){
            System.out.print(" + "+scores.get(i));
        }
        System.out.println(" = "+totalPoints);
        System.out.println("-----");
    }
}
