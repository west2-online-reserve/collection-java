package com.org.Lskar.Swimming.lib.Contest;

public class AthleteResult {
    String name;
    String prelimRank = "*";
    String semiRank = "*";
    String finalRank = "*";
    String prelimScores = "*";
    String semiScores = "*";
    String finalScores = "*";
    int firstAppearanceRank = Integer.MAX_VALUE;
    String firstAppearancePhase = "";

    public void setName(String name){

        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrelimRank(String prelimRank){
        this.prelimRank = prelimRank;
    }

    public String getPrelimRank(){
        return prelimRank;
    }

    public void setSemiRank(String semiRank){
        this.semiRank = semiRank;
    }

    public String getSemiRank(){
        return semiRank;
    }

    public void setFinalRank(String finalRank){
        this.finalRank = finalRank;
    }

    public String getFinalRank(){
        return finalRank;
    }

    public void setPrelimScores(String prelimScores){
        this.prelimScores = prelimScores;
    }

    public String getPrelimScores(){
        return prelimScores;
    }

    public void setSemiScores(String semiScores){
        this.semiScores = semiScores;
    }

    public String getSemiScores(){
        return semiScores;
    }

    public void setFinalScores(String finalScores){
        this.finalScores = finalScores;
    }

    public String getFinalScores(){
        return finalScores;
    }

    public void setFirstAppearanceRank(int firstAppearanceRank){
        this.firstAppearanceRank = firstAppearanceRank;
    }

    public int getFirstAppearanceRank(){
        return firstAppearanceRank;
    }

    public void setFirstAppearancePhase(String firstAppearancePhase){
        this.firstAppearancePhase = firstAppearancePhase;
    }

    public String getFirstAppearancePhase(){
        return firstAppearancePhase;
    }


    public String outputDetailInfo(){
        return "Full Name:"+name+"\n"+
                "Rank:"+prelimRank+" | "+semiRank+" | "+finalRank+"\n"+
                "Preliminary Score:"+prelimScores+"\n"+
                "Semifinal Score:"+semiScores+"\n"+
                "Final Score:"+finalScores+"\n"+
                "-----\n";
    }

    public String outputSimpleInfo(){

        return "Full Name:"+name+"\n"+
                "Rank:"+finalRank+"\n"+
                "Score:"+finalScores+"\n"+
                "-----\n";
    }


}
