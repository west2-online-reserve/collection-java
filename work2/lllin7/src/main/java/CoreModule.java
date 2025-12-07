import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.*;
import java.util.*;

public class CoreModule {
    //读取数据
    private static String getJsonContent(String eventName) {
        StringBuilder jsonContent = new StringBuilder();
        try (InputStream is = CoreModule.class.getClassLoader().getResourceAsStream(eventName + ".json");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonContent.toString();
    }

    // 输出所有选手信息
    public static void displayAllPlayersInfo(String outputPath) {
        String jsonContent = getJsonContent("players");
        //解析数据
        JSONArray countries = JSONArray.parseArray(jsonContent);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))){
            for (int i = 0; i < countries.size(); i++) {
                JSONObject country = countries.getJSONObject(i);

                String countryName = country.getString("CountryName");
                JSONArray participations = country.getJSONArray("Participations");
                for (int j = 0; j < participations.size(); j++) {
                    JSONObject paticipation = participations.getJSONObject(j);

                    String gender = paticipation.getBoolean("Gender") ? "Female" : "Male";
                    String preferredLastName = paticipation.getString("PreferredLastName");
                    String preferredFirstName = paticipation.getString("PreferredFirstName");

                    Player player = new Player(preferredLastName, preferredFirstName, gender, countryName);
                    bw.write(player.toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 输出每个比赛项目的结果
    public static void displayResultsForEachEvent(String outputPath, String eventName, boolean isDetailed) {
        String jsonContent = getJsonContent(eventName);
        //解析数据
        Map<String, List<Result>> heatMap = new HashMap<>();

        JSONObject event = JSONObject.parseObject(jsonContent);
        JSONArray heats = event.getJSONArray("Heats");
        for (int i = 0; i < heats.size(); i++) {
            JSONObject heat = heats.getJSONObject(i);
            String name = heat.getString("Name");

            List<Result> resultList = new ArrayList<>();
            JSONArray results = heat.getJSONArray("Results");
            for (int j = 0; j < results.size(); j++) {
                JSONObject result = results.getJSONObject(j);
                String fullName = result.getString("FullName").replace('/', '&');
                int rank = result.getInteger("Rank");
                String totalPoint = result.getString("TotalPoints");

                String points = "";
                JSONArray dives = result.getJSONArray("Dives");
                for (int k = 0; k < dives.size(); k++) {
                    JSONObject dive = dives.getJSONObject(k);
                    points += dive.getString("TotalPoints");
                    if (k < dives.size() - 1) {
                        points += " + ";
                    }
                }
                points += (" = " + totalPoint);
                resultList.add(new Result(fullName, rank, points));
            }
            heatMap.put(name, resultList);
        }

        //输出决赛信息
        if (!isDetailed) {
            List<Result> finalResults = heatMap.get("Final");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
                for (Result result : finalResults) {
                    bw.write(result.toString());
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //输出详细信息
        else {
            Map<String, Result> preliminaryMap = new HashMap<>();
            Map<String, Result> semifinalMap = new HashMap<>();
            Map<String, Result> finalMap = new HashMap<>();
            List<String> firstHeatNames = new ArrayList<>();

            List<Result> preliminaryList = heatMap.get("Preliminary");
            if (preliminaryList != null) {
                for (Result result : preliminaryList) {
                    preliminaryMap.put(result.getFullName(), result);
                    firstHeatNames.add(result.getFullName());
                }
            }
            List<Result> semifinalList = heatMap.get("Semifinal");
            if (semifinalList != null) {
                for (Result result : semifinalList) {
                    semifinalMap.put(result.getFullName(), result);
                    if (preliminaryList == null) {
                        firstHeatNames.add(result.getFullName());
                    }
                }
            }
            List<Result> finalList = heatMap.get("Final");
            if (finalList != null) {
                for (Result result : finalList) {
                    finalMap.put(result.getFullName(), result);
                    if (preliminaryList == null && semifinalList == null) {
                        firstHeatNames.add(result.getFullName());
                    }
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))){
                for (String fullName : firstHeatNames) {
                    Result preliminaryResult = preliminaryMap.get(fullName);
                    Result semifinalResult = semifinalMap.get(fullName);
                    Result finalResult = finalMap.get(fullName);

                    int preliminaryRank = (preliminaryResult == null) ? 0 : preliminaryResult.getRank();
                    int semifinalRank = (semifinalResult == null) ? 0 : semifinalResult.getRank();
                    int finalRank = (finalResult == null) ? 0 : finalResult.getRank();
                    String preliminaryScores = (preliminaryResult == null) ? "*" : preliminaryResult.getScores();
                    String semifinalScores = (semifinalResult == null) ? "*" : semifinalResult.getScores();
                    String finalScores = (finalResult == null) ? "*" : finalResult.getScores();

                    DetailedResult detailedResult = new DetailedResult(fullName, preliminaryRank, semifinalRank, finalRank,
                            preliminaryScores, semifinalScores, finalScores);
                    bw.write(detailedResult.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}