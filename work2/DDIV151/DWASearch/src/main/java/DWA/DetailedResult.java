package DWA;

import java.util.ArrayList;

public class DetailedResult extends Result implements Comparable<DetailedResult> {
    public DetailedResult() {
    }

    private int finalRank = 0;
    private int semiRank = 0;
    private int preRank = 0;
    private ArrayList<Double> finalScores;
    private ArrayList<Double> semiScores;
    private ArrayList<Double> preScores;
    private String name;


    public ArrayList<Double> getFinalScores() {
        return finalScores;
    }

    public void setFinalScores(ArrayList<Double> finalScores) {
        this.finalScores = finalScores;
    }

    public ArrayList<Double> getSemiScore() {
        return semiScores;
    }

    public void setSemiScore(ArrayList<Double> semiScore) {
        this.semiScores = semiScore;
    }

    public ArrayList<Double> getPreScores() {
        return preScores;
    }

    public void setPreScores(ArrayList<Double> preScores) {
        this.preScores = preScores;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getFinalRank() {
        return finalRank;
    }

    public void setFinalRank(int finalRank) {
        this.finalRank = finalRank;
    }

    public int getSemiRank() {
        return semiRank;
    }

    public void setSemiRank(int semiRank) {
        this.semiRank = semiRank;
    }

    public int getPreRank() {
        return preRank;
    }

    public void setPreRank(int preRank) {
        this.preRank = preRank;
    }

    public DetailedResult(Result result, ArrayList<Result> results) {
        name = result.getAthleteFullName();
        if (results == null || results.isEmpty())
            throw new RuntimeException("比赛列表不应为空");
        for (Result r : results) {
            //判断是不是这个运动员的信息
            if (!r.getAthleteFullName().equals(name))
                continue;
            //是什么比赛set哪种变量
            switch (r.getName()) {
                case "Final", "Finals":
                    this.setFinalRank(r.getRank());
                    this.finalScores = r.getDives();
                    break;
                case "Semifinals":
                    this.setSemiRank(r.getRank());
                    this.semiScores = r.getDives();
                    break;
                case "Preliminaries", "Preliminary":
                    this.setPreRank(r.getRank());
                    this.preScores = r.getDives();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + r.getName());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Full Name:" + name);
        builder.append("\nRank:");
        if (preRank > 0)
            builder.append(preRank + "|");
        else
            builder.append("*|");
        if (semiRank > 0)
            builder.append(semiRank + "|");
        else
            builder.append("*|");
        if (finalRank > 0)
            builder.append(finalRank);
        else
            builder.append("*");
        if (preScores != null)
            builder.append("\nPreliminary Score:" + parsePointsString(preScores));
        else
            builder.append("\nPreliminary Score:*");
        if (semiScores != null)
            builder.append("\nSemiminary Score:" + parsePointsString(semiScores));
        else
            builder.append("\nSemiminary Score:*");
        if (finalScores != null)
            builder.append("\nFinal Score:" + parsePointsString(finalScores));
        else
            builder.append("\nFinal Score:*");
        return builder.toString();
    }

    @Override
    public int compareTo(DetailedResult o) {
        if (this.preRank > 0 && o.preRank > 0) {
            return this.preRank - o.preRank;
        } else if (this.semiRank > 0 && o.semiRank > 0) {
            return this.semiRank - o.semiRank;
        } else {
            return this.finalRank - o.finalRank;
        }
    }
}
