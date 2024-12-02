package src.Lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class createAthleteList {
    public static List<athlete> createPlayerlist(String fileName) throws IOException {

        int athleteCount = 0;
        StringBuilder athleteScore = new StringBuilder();
        List<athlete> allAthleteList = new ArrayList<>();
        HashMap<String, Integer> athleteHashMap = new HashMap<>();

        String allAthlete = fileReadAndWrite.readFile("src/Data/athleteInformation/" + fileName);
        String[] allAthleteArray = allAthlete.split("\n+");

        int markToTellGameType = 0;
        athlete athlete = new athlete();

        for (String data : allAthleteArray) {
            if (data.startsWith("Final"))
                markToTellGameType = 1;//1表示是决赛,2表示是半决赛，3表示初赛
            else if (data.startsWith("Semi"))
                markToTellGameType = 2;
            else if (data.startsWith("Preliminary"))
                markToTellGameType = 3;

            else if (data.startsWith("Rank")) {
                String tempRank =data.replace("Rank:", "").trim();
                if (markToTellGameType == 1){
                    athlete.setFinalScore(processScore(athleteScore.toString(), "Final Score:"));
                    athlete.setFinalRank(tempRank);
                }
                else if (markToTellGameType == 2){
                    athlete.setSemiScore(processScore(athleteScore.toString(), "Semifinal Score:"));
                    athlete.setSemiRank(tempRank);
                }
                else if (markToTellGameType == 3){
                    athlete.setPreScore(processScore(athleteScore.toString(), "Preliminary Score:"));
                    athlete.setPreRank(tempRank);
                }
                athleteScore.setLength(0);
            }
            else if (data.startsWith("FullName")) {
                athlete.setFullName(data.replace("FullName:", "Full Name:"));

                if (athleteHashMap.containsKey(athlete.getFullName())) {
                    athlete existingAthlete = allAthleteList.get(athleteHashMap.get(athlete.getFullName()));
                    if (markToTellGameType == 2){
                        existingAthlete.setSemiScore(athlete.getSemiScore());
                        existingAthlete.setSemiRank(athlete.getSemiRank());
                    }
                    else if (markToTellGameType == 3){
                        existingAthlete.setPreScore(athlete.getPreScore());
                        existingAthlete.setPreRank(athlete.getPreRank());
                    }
                    athlete.resetAthleteInfo();
                }
                else {
                    athleteHashMap.put(athlete.getFullName(), athleteCount++);
                    allAthleteList.add(new athlete(athlete.getPreScore(), athlete.getSemiScore(),athlete.getFinalScore(), athlete.getPreRank(), athlete.getSemiRank(), athlete.getFinalRank(),athlete.getFullName()));
                }
            }
            else
                athleteScore.append(data);
        }
        return allAthleteList;
    }

    //由于我的文件中的有关运动员的分数是6个逐级累加的形式，所以我要对其进行处理，将其转换为一个个的分数，然后求和，最后输出。
    public static String processScore(String athleteScore, String gameType) {
        List<Double> scoreList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(athleteScore);

        while (matcher.find())
            scoreList.add(Double.parseDouble(matcher.group()));

        double sum =scoreList.getLast();

        for (int i = scoreList.size()-1; i >0; i--) {
            scoreList.set(i, scoreList.get(i) - scoreList.get(i-1));
        }

        return gameType + scoreList.stream().map( score -> String.format("%.2f", score))
                .collect(Collectors.joining(" + "))
                + " = " + String.format("%.2f", sum) ;
    }
}