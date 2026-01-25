package org.example;

import java.util.List;

public class Athlete {
    protected String fullName;
    protected String gender;
    protected String country;

    protected String preliminaryRank="*";
    protected String semifinalRank="*";
    protected String finalRank="*";
    protected float preliminaryTotalPoint;
    protected float seminifinalTotalPoint;
    protected float finalTotalPoint;
    protected String preliminaryScore="*";
    protected String semifinalScore="*";
    protected String finalScore="*";
    public float getFinalTotalPoint() {
        return finalTotalPoint;
    }

    public void setFinalTotalPoint(float finalTotalPoint) {
        this.finalTotalPoint = finalTotalPoint;
    }

    public float getPreliminaryTotalPoint() {
        return preliminaryTotalPoint;
    }

    public void setPreliminaryTotalPoint(float preliminaryTotalPoint) {
        this.preliminaryTotalPoint = preliminaryTotalPoint;
    }

    public float getSeminifinalTotalPoint() {
        return seminifinalTotalPoint;
    }

    public void setSeminifinalTotalPoint(float seminifinalTotalPoint) {
        this.seminifinalTotalPoint = seminifinalTotalPoint;
    }



    public String getFinalRank() {
        return finalRank;
    }
    public void setFinalRank(String finalRank) {
        this.finalRank = finalRank;
    }

    public String getSemifinalRank() {
        return semifinalRank;
    }

    public void setSemifinalRank(String semifinalRank) {
        this.semifinalRank = semifinalRank;
    }

    public String getPreliminaryRank() {
        return preliminaryRank;
    }

    public void setPreliminaryRank(String preliminaryRank) {
        this.preliminaryRank = preliminaryRank;
    }


    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public Athlete(String fullName,  String gender,String country) {
        this.fullName = fullName;
        this.country = country;
        this.gender = gender;
    }

    public Athlete(){}


}
