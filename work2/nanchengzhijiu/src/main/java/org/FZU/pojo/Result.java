package org.FZU.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Result {
    private String FullName;
    private int Rank;
    private int Rank1;
    private int Rank2;
    private String Preliminary;
    private String SemifinalScore;
    private String FinalScore;

    public Result(String fullName, int rank, int rank1,int rank2,String finalScore, String preliminary, String semifinalScore) {
        this.FullName = fullName;
        this.Rank = rank;
        this.Rank1=rank1;
        this.Rank2=rank2;
        this.Preliminary = preliminary;
        this.SemifinalScore = semifinalScore;
        this.FinalScore = finalScore;
    }
    public Result(String fullName, int rank, String finalScore) {
        this.FullName = fullName;
        this.Rank = rank;
        this.FinalScore = finalScore;
    }

    public static String displayResultsForEachEven (HttpResponse response) {
        ArrayList<Result> results = new ArrayList<Result>();
        if (response==null) {
            return " ";
        }
//        数据入口
        JSONArray dataInfo = JSONObject.parseObject(response.getContent()).getJSONArray("Heats");
        String FullName;
        String TotalPoints;
        int Rank;
        StringBuilder Score= new StringBuilder();
//        获取总决赛分数
        JSONArray allResults = dataInfo.getJSONObject(0).getJSONArray("Results");
        for (int j = 0; j < allResults.size(); j++) {
            JSONObject playInfo = allResults.getJSONObject(j);
            TotalPoints = playInfo.getString("TotalPoints");
            FullName = playInfo.getString("FullName");
            Rank = playInfo.getInteger("Rank");
//            获取每次的分数
            JSONArray dives = playInfo.getJSONArray("Dives");
            for (int k = 0; k < dives.size(); k++) {
                JSONObject div = dives.getJSONObject(k);
                if (k < dives.size() - 1) {
                    Score.append(div.getString("TotalPoints")).append(" + ");
                } else {
                    Score.append(div.getString("TotalPoints"));
                    Score.append(" = ").append(TotalPoints);
                }
            }
            results.add(new Result(FullName, Rank, Score.toString()));
        }
        StringBuilder output = new StringBuilder();
        for (Result result : results) {
            output.append("Full Name:")
                    .append(result.getFullName())
                    .append("\n")
                    .append("Rank:")
                    .append(result.getRank())
                    .append("\n")
                    .append("Score:")
                    .append(result.getFinalScore())
                    .append("\n")
                    .append("-----")
                    .append("\n");
        }
        return  output.toString();
    }

    public static String displayDetailsForEachEven(HttpResponse response) {
        ArrayList<Result> results = new ArrayList<Result>();
        if (response==null) {
            return "";
        }
        Map<String, Result> allPhaseResults = new HashMap<>();
        String FullName;
        String FinalScore="*";
        String preliminary="*";
        String SemifinalScore="*";
        StringBuilder Score= new StringBuilder();
        String personId="";
//        决赛
        int Rank=0;
//        初赛
        int Rank1=0;
//        半决赛
        int Rank2=0;
//        数据入口
        JSONArray dataInfo = JSONObject.parseObject(response.getContent()).getJSONArray("Heats");
        String competeName;
        for (int i = 0; i < dataInfo.size(); i++) {
            competeName=dataInfo.getJSONObject(i).getString("PhaseName");
            JSONArray allResults = dataInfo.getJSONObject(i).getJSONArray("Results");
            if (competeName.equals("Finals") || competeName.equals("Preliminaries") || competeName.equals("Semifinals")) {
                for (int j = 0; j < allResults.size(); j++) {
                    JSONObject playInfo = allResults.getJSONObject(j);
//                    增加一步判断有无competitions
                    if(playInfo.getJSONArray("Competitors")!=null) {
                        JSONArray competitors = playInfo.getJSONArray("Competitors");
                        personId="";
                        for (int j2 = 0; j2 < competitors.size(); j2++) {
                            personId+=competitors.getJSONObject(j2).getString("GmsId");
                        }
                    }else{
                        personId = playInfo.getString("PersonId");
                    }
                    FullName = playInfo.getString("FullName");

                    // 构建分数字符串
                    Score.setLength(0); // 清空StringBuilder
                    JSONArray dives = playInfo.getJSONArray("Dives");
                    String totalPoints = playInfo.getString("TotalPoints");

                    for (int k = 0; k < dives.size(); k++) {
                        JSONObject div = dives.getJSONObject(k);
                        Score.append(div.getString("TotalPoints"));
                        if (k < dives.size() - 1) {
                            Score.append(" + ");
                        } else {
                            Score.append(" = ").append(totalPoints);
                        }
                    }

                    // 使用Result变量暂存结果
                    Result existingResult = allPhaseResults.get(personId);
                    if (existingResult == null) {
                        existingResult = new Result(FullName, Rank, Rank1, Rank2, FinalScore, preliminary, SemifinalScore);
                    // 放入运动员映射中
                        allPhaseResults.put(personId, existingResult);
                    }

                    // 根据阶段设置不同字段
                    switch (competeName) {
                        case "Finals":
                            Rank = playInfo.getInteger("Rank");
                            FinalScore = Score.toString();
                            existingResult.FinalScore = FinalScore;
                            existingResult.Rank = Rank;
                            break;
                        case "Preliminaries":
                            Rank1 = playInfo.getInteger("Rank");
                            preliminary = Score.toString();
                            existingResult.Preliminary = preliminary;
                            existingResult.Rank1 = Rank1;
                            break;
                        case "Semifinals":
                            Rank2 = playInfo.getInteger("Rank");
                            SemifinalScore = Score.toString();
                            existingResult.SemifinalScore = SemifinalScore;
                            existingResult.Rank2 = Rank2;
                            break;
                    }
                }
            }

        }
        for (Map.Entry<String, Result> entry : allPhaseResults.entrySet()) {
            results.add(entry.getValue());
        }
        results.sort(Comparator.comparing(result -> {
            if (result.Rank1 != 0) {
                return result.Rank1;
            } else if (result.Rank2 != 0) {
                return result.Rank2;
            } else if (result.Rank != 0) {
                return result.Rank;
            } else {
                // 如果都是0，返回一个很大的值让它们排到最后
                return Integer.MAX_VALUE;
            }
        }));
        StringBuilder output = new StringBuilder();
        for (Result result : results) {
            output.append("Full Name:")
                    .append(formatDualName(result.getFullName()))
                    .append("\n")
                    .append("Rank:")
                    .append(result.getRank())
                    .append(" | ")
                    .append(result.getRank1())
                    .append(" | ")
                    .append(result.getRank2())
                    .append("\n")
                    .append("Preliminary Score:")
                    .append(result.getPreliminary())
                    .append("\n")
                    .append("Semifinal Score:")
                    .append(result.getSemifinalScore())
                    .append("\n")
                    .append("Final Score:")
                    .append(result.getFinalScore())
                    .append("\n")
                    .append("-----")
                    .append("\n");
        }
        return output.toString();
    }
    //提取首字母
    private static String extractLastName(String personName) {
//        如果没有名称或为空格
        if (personName == null || personName.isBlank()) {
            return "";
        }
//        返回第一个字母
        String[] parts = personName.split("\\s+");
        return parts.length > 0 ? parts[0] : "";
    }
    //重新组合多人项目名称
    private static String formatDualName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return fullName;
        }
        String trimmedName = fullName.trim();
        if (trimmedName.contains("/")) {
            String[] persons = trimmedName.split("/");
            if (persons.length == 2) {
                String personA = persons[0].trim();
                String personB = persons[1].trim();

                String lastNameA = extractLastName(personA);
                String lastNameB = extractLastName(personB);

                if (lastNameA.compareToIgnoreCase(lastNameB) > 0) {
                    return personB + " / " + personA;
                }
                return fullName;
            }
        }
        return fullName;
    }

    public String getFullName() {
        return FullName;
    }


    public String getRank() {
        String rank=String.valueOf(Rank);
        if(rank.equals("0")){
            rank="*";
        }
        return rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getRank1() {
        String rank1=String.valueOf(Rank1);
        if(rank1.equals("0")){
            rank1="*";
        }
        return rank1;
    }


    public String getRank2() {
        String rank2=String.valueOf(Rank2);
        if(rank2.equals("0")){
            rank2="*";
        }
        return rank2;
    }


    public String getPreliminary() {
        return Preliminary;
    }


    public String getSemifinalScore() {
        return SemifinalScore;
    }

    public String getFinalScore() {
        return FinalScore;
    }

}
