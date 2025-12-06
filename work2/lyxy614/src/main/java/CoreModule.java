package main.java;

import main.java.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Comparator;

public class CoreModule implements OutputModule {
    private static boolean isWritten = false;

    @Override
    public void displayAllPlayersInfo(String outputFile, List<Athlete> athletes) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, isWritten))){
            for (Athlete athlete : athletes) {
                if (athlete.getParticipations() != null){
                    for (Participations participation : athlete.getParticipations()) {
                        bufferedWriter.write(participation + "\n" + athlete + "\n" + "-----" + "\n");

                    }
                }
            }
            isWritten = true;
        }catch (IOException e){
            System.out.println("写入失败" + e.getMessage());
        }
    }

    @Override
    public void displayResultsForEachEvent(String outputFile, CompetitionResultRoot competitionResultRoot) {
        List<Results> results = competitionResultRoot.getHeats().getFirst().getResults();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, isWritten))){
            for (Results result : results) {
                bufferedWriter.write(result.toString());
            }
            isWritten = true;
        }catch (IOException e){
            System.out.println("写入失败" + e.getMessage());
        }
    }

    @Override
    public void displayDetailResultsForEachEvent(String outputFile, CompetitionResultRoot competitionResultRoot){
        //创建Map，使得运动员的名字可以与其比赛结果一一对应，不会出现重复运动员的情况
        Map<String, AthleteFullResults> fullResultsMap = new HashMap<>();
        //获取Heats的最后一个元素，因为该元素就是每个项目第一次比赛的内容，获取该阶段名称，为后面的排序做准备
        String firstStageName = competitionResultRoot.getHeats().getLast().getStageName();
        //先从Heats开始遍历，首先获取比赛阶段信息(stageName)
        for (Heats heat : competitionResultRoot.getHeats()) {
            String stageName = heat.getStageName();
            //然后再遍历Results，向Map中放入运动员fullName-AthleteFullResults
            for (Results result : heat.getResults()) {
                //判断是否为双人项目，并获取运动员的fullName
                //单人项目的Results下的Competitors为null，双人项目的Competitors为一个内含两个元素的数组
                //Results中已经处理过双人项目名字先后顺序的问题
                String fullName;
                if (result.getCompetitors() != null){
                    List<Competitors> competitorsList = result.getSortedCompetitors();
                    fullName = competitorsList.getFirst().getFullName() + " & "  + competitorsList.getLast().getFullName();
                }
                else {
                    fullName = result.getFullName();
                }
                AthleteFullResults athleteFullResults = fullResultsMap.getOrDefault(fullName, new AthleteFullResults());
                //同时用switch检查当前比赛阶段，逐步初始化AthleteFullResults
                switch (stageName) {
                    case "Preliminary":
                        athleteFullResults.setPreliminaryRank(String.valueOf(result.getRank()));
                        athleteFullResults.setPreliminaryScore(new AthleteFullResults.StageScore(result.getDives(), result.getTotalPoints()));
                        break;
                    case "Semifinal":
                        athleteFullResults.setSemifinalRank(String.valueOf(result.getRank()));
                        athleteFullResults.setSemifinalScore(new AthleteFullResults.StageScore(result.getDives(), result.getTotalPoints()));
                        break;
                    case "Final":
                        athleteFullResults.setFinalRank(String.valueOf(result.getRank()));
                        athleteFullResults.setFinalScore(new AthleteFullResults.StageScore(result.getDives(), result.getTotalPoints()));
                }
                fullResultsMap.put(fullName, athleteFullResults);
            }
        }
        //最后统一写入数据
        //但是要按照第一次比赛的Rank来排序，需要自定义比较逻辑，所以再创建一个List，传入刚刚Map中初始化好的数据，使用比较器
        List<Map.Entry<String, AthleteFullResults>> resultsList = getEntries(fullResultsMap, firstStageName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, isWritten))){
            for (Map.Entry<String, AthleteFullResults> entry : resultsList) {
                bufferedWriter.write("Full Name:" + entry.getKey().replace("/", "&"));
                bufferedWriter.newLine();
                bufferedWriter.write(entry.getValue().toString());
                bufferedWriter.write("-----");
                bufferedWriter.newLine();
            }
            isWritten = true;
        }catch (IOException e){
            System.out.println("写入失败" + e.getMessage());
        }
    }
    //这个方法专门将Map里的数据转入List,并完成排序，以便上面的输出方法直接获取到排序后的数据
    private static List<Map.Entry<String, AthleteFullResults>> getEntries(Map<String, AthleteFullResults> fullResultsMap, String firstStageName) {
        List<Map.Entry<String, AthleteFullResults>> resultsList = new ArrayList<>(fullResultsMap.entrySet());
        Comparator<Map.Entry<String, AthleteFullResults>> comparator = (entry1, entry2) -> {
            Integer firstRank = 0;
            Integer secondRank = 0;
            //这里直接使用前面获取到的项目的第一次比赛来判断该用哪一阶段的Rank来排序
            switch (firstStageName) {
                case "Preliminary":
                    firstRank = Integer.valueOf(entry1.getValue().getPreliminaryRank());
                    secondRank = Integer.valueOf(entry2.getValue().getPreliminaryRank());
                    break;
                case "Semifinal":
                    firstRank = Integer.valueOf(entry1.getValue().getSemifinalRank());
                    secondRank = Integer.valueOf(entry2.getValue().getSemifinalRank());
                    break;
                case "Final":
                    firstRank = Integer.valueOf(entry1.getValue().getFinalRank());
                    secondRank = Integer.valueOf(entry2.getValue().getFinalRank());
                    break;
            }
            return Integer.compare(firstRank, secondRank);
        };
        resultsList.sort(comparator);
        return resultsList;
    }
    //这个方法用于输出错误信息
    @Override
    public void displayWrongMessage(String outputFile, String wrongMessage) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, isWritten))){
            bufferedWriter.write(wrongMessage);
            bufferedWriter.newLine();
            bufferedWriter.write("-----");
            bufferedWriter.newLine();
            isWritten = true;
        }catch (IOException e){
            System.out.println("写入失败" + e.getMessage());
        }
    }
}
