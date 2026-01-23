package org.example.lib;

import java.util.ArrayList;

public class TotalResult {

    private String preliminaryRank;
    private String semifinalRank;
    private String finalRank;
    private ArrayList<Dive> preliminaryDives;
    private ArrayList<Dive> semifinalDives;
    private ArrayList<Dive> finalDives;
    private String preliminaryScore = "";
    private String semifinalScore = "";
    private String finalScore = "";

    public int getFirstRankValue() {
        if (!preliminaryRank.equals("*")) {
            return Integer.parseInt(preliminaryRank);
        } else if (!semifinalRank.equals("*")) {
            return Integer.parseInt(semifinalRank);
        } else if (!finalRank.equals("*")) {
            return Integer.parseInt(finalRank);
        } else {
            return Integer.MAX_VALUE; // 理论上不会出现
        }
    }

    public TotalResult() {
        this.preliminaryRank = "*";
        this.semifinalRank = "*";
        this.finalRank = "*";
        this.preliminaryDives = new ArrayList<>();
        this.semifinalDives = new ArrayList<>();
        this.finalDives = new ArrayList<>();

    }


    public void setResult(Result result, String heatName) {
        ArrayList<Dive> dives = result.getDives();
        double points = 0;
        StringBuilder score = new StringBuilder();
        for (int i = 0; i < dives.size(); i++) {
            Dive dive = dives.get(i);
            points += dive.getDivePoints();
            score.append(String.format("%.2f", dive.getDivePoints()));
            if (i == dives.size() - 1) {
                score.append(String.format(" = %.2f", points));
            } else {
                score.append(" + ");
            }
        }

        switch (heatName) {
            case "Preliminary":
                preliminaryRank = result.getRank();
                preliminaryDives = dives;
                preliminaryScore = score.toString();
                break;
            case "Semifinal":
                semifinalRank = result.getRank();
                semifinalDives = dives;
                semifinalScore = score.toString();
                break;
            case "Final":
                finalRank = result.getRank();
                finalDives = dives;
                finalScore = score.toString();
                break;
        }

    }


    @Override
    public String toString() {

        return "Rank:" + preliminaryRank + "|" + semifinalRank + "|" + finalRank
                + "\nPreliminary Score:" + ((preliminaryScore.isEmpty()) ? "*" : preliminaryScore)
                + "\nSemifinal Score:" + ((semifinalScore.isEmpty()) ? "*" : semifinalScore)
                + "\nFinal Score:" + ((finalScore.isEmpty()) ? "*" : finalScore);
    }

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

    public ArrayList<Dive> getPreliminaryDives() {
        return preliminaryDives;
    }

    public void setPreliminaryDives(ArrayList<Dive> preliminaryDives) {
        this.preliminaryDives = preliminaryDives;
    }

    public ArrayList<Dive> getSemifinalDives() {
        return semifinalDives;
    }

    public void setSemifinalDives(ArrayList<Dive> semifinalDives) {
        this.semifinalDives = semifinalDives;
    }

    public ArrayList<Dive> getFinalDives() {
        return finalDives;
    }

    public void setFinalDives(ArrayList<Dive> finalDives) {
        this.finalDives = finalDives;
    }
}
