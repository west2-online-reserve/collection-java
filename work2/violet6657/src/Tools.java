import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Tools {
    public static void fetchAndWriteAthletes(String outputFile) throws IOException {
        String url = "https://api.worldaquatics.com/fina/competitions/3337/athletes?gender=&countryId=";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json"); // 确保请求 JSON 格式
            try (CloseableHttpResponse response = client.execute(request)) {
                String jsonString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("响应内容: " + jsonString); // 调试输出

                // 检查是否为有效 JSON 数组
                if (!jsonString.trim().startsWith("[")) {
                    throw new IllegalStateException("响应不是 JSON 数组格式: " + jsonString);
                }

                // 使用 Gson 解析 JSON 数组
                Gson gson = new Gson();
                JsonArray countries;
                try {
                    countries = gson.fromJson(jsonString, JsonArray.class);
                } catch (JsonSyntaxException e) {
                    System.err.println("JSON 解析失败: " + e.getMessage());
                    throw e;
                }

                // 存储所有选手
                List<Athlete> athletes = new ArrayList<>();

                // 遍历国家
                for (int i = 0; i < countries.size(); i++) {
                    JsonObject country = countries.get(i).getAsJsonObject();
                    String countryName = country.get("CountryName").getAsString();
                    JsonArray participations = country.getAsJsonArray("Participations");

                    // 遍历运动员
                    for (int j = 0; j < participations.size(); j++) {
                        JsonObject athlete = participations.get(j).getAsJsonObject();
                        String lastName = athlete.get("PreferredLastName").getAsString();
                        String firstName = athlete.get("PreferredFirstName").getAsString();
                        String fullName = lastName + " " + firstName;
                        String gender = athlete.get("Gender").getAsInt() == 0 ? "Male" : "Female";
                        athletes.add(new Athlete(fullName, gender, countryName));
                    }
                }

                // 按国籍（首要）和姓氏（次要）升序排序
                athletes.sort(Comparator.comparing(Athlete::getCountry)
                        .thenComparing(a -> a.getFullName().split(" ")[0]));

                // 格式化输出
                StringBuilder output = new StringBuilder();
                for (Athlete athlete : athletes) {
                    output.append("Full Name:").append(reverseName(athlete.getFullName())).append("\n")
                            .append("Gender:").append(athlete.getGender()).append("\n")
                            .append("Country:").append(athlete.getCountry()).append("\n")
                            .append("-----\n");
                }

                // 写入文件
                FileUtils.writeStringToFile(new File(outputFile), output.toString(), StandardCharsets.UTF_8,true);
            }
        } catch (Exception e) {
            System.err.println("请求或解析过程中出错: " + e.getMessage());
            throw e;
        }
    }


    public static ArrayList<String> WriteCompetition(String output,String fileName) throws IOException {
        ArrayList<String> testExample=new ArrayList<>();
        Gson gson = new Gson();
        String jsonString = FileUtils.readFileToString(new File("src/JsonData/"+fileName+".json"), StandardCharsets.UTF_8);
        JsonArray competititon;
        try {
            competititon = gson.fromJson(jsonString, JsonArray.class);
        } catch (JsonSyntaxException e) {
            System.err.println("JSON 解析失败: " + e.getMessage());
            throw e;
        }

        List<Competition> competitions = new ArrayList<>();
        for (int j = 0; j < competititon.size(); j++) {
            JsonObject competitor = competititon.get(j).getAsJsonObject();
            if(competitor.get("PhaseName").getAsString().equals("Finals")){
                String fullName = competitor.get("FullName").getAsString();
                String rank = competitor.get("Rank").getAsString();
                StringBuilder sb = new StringBuilder();
                String totalScore = competitor.get("TotalScore").getAsString();
                JsonArray Scores = competitor.get("Scores").getAsJsonArray();
                for (int i = 0; i < Scores.size(); i++) {
                    String score = Scores.get(i).getAsString();
                    if(i!=Scores.size()-1){
                        sb.append(score);
                        sb.append("+");
                    }else{
                        sb.append(score);
                        sb.append("=");
                        sb.append(totalScore);
                    }
                }
                String score = sb.toString();
                competitions.add(new Competition(fullName,rank,score));
            }
        }
        StringBuilder outputSB = new StringBuilder();
        for (Competition competition : competitions) {
            outputSB.append("Full Name:").append(reverseName(competition.getFullName())).append("\n")
                    .append("Rank:").append(competition.getRank()).append("\n")
                    .append("Score:").append(competition.getScore()).append("\n")
                    .append("-----\n");
            testExample.add(competition.toString());
        }
        FileUtils.writeStringToFile(new File(output), outputSB.toString(), StandardCharsets.UTF_8,true);
        return testExample;
    }


    public static ArrayList<String> writeCompetitionDetails(String outputFile, String event) throws IOException {
        Gson gson = new Gson();
        String jsonFile = "src/JsonData/" + event + ".json";
        String jsonString = FileUtils.readFileToString(new File(jsonFile), StandardCharsets.UTF_8);
        JsonArray competition;
        ArrayList <String> testExample=new ArrayList<>();
        try {
            competition = gson.fromJson(jsonString, JsonArray.class);
        } catch (JsonSyntaxException e) {
            System.err.println("JSON 解析失败: " + event + ".json - " + e.getMessage());
            throw e;
        }

        Map<String, CompetitionDetail> athleteMap = new HashMap<>();
        for (int j = 0; j < competition.size(); j++) {
            JsonObject competitor = competition.get(j).getAsJsonObject();
            String fullName = competitor.get("FullName").getAsString();
            String phaseName = competitor.get("PhaseName").getAsString();
            String rank = competitor.get("Rank").getAsString();
            String totalScore = competitor.get("TotalScore").getAsString();
            JsonArray scores = competitor.get("Scores").getAsJsonArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                String score = scores.get(i).getAsString();
                if (i != scores.size() - 1) {
                    sb.append(score).append(" + ");
                } else {
                    sb.append(score).append(" = ").append(totalScore);
                }
            }
            String scoreString = sb.toString();

            CompetitionDetail detail = athleteMap.getOrDefault(fullName,
                    new CompetitionDetail(fullName,"*","*","*","*","*","*"));
            if (phaseName.equals("Preliminaries")) {
                detail.setPreliminaryScore(scoreString);
                detail.setPreliminaryRank(rank);
            } else if (phaseName.equals("Semifinals")) {
                detail.setSemifinalScore(scoreString);
                detail.setSemifinalRank(rank);
            } else if (phaseName.equals("Finals")) {
                detail.setFinalScore(scoreString);
                detail.setFinalsRank(rank);
            }
            athleteMap.put(fullName, detail);
        }

        List<CompetitionDetail> competitions = new ArrayList<>(athleteMap.values());
        competitions.sort(Comparator.comparingInt((CompetitionDetail c) -> {
            try {
                return (!c.getPreliminaryRank().equals("*")) ? (!(c.getFinalsRank().equals("*") )? Integer.parseInt(c.getPreliminaryRank()) : Integer.parseInt(c.getSemifinalRank())): Integer.parseInt(c.getFinalsRank());
            } catch (NumberFormatException e) {
                return Integer.MAX_VALUE;
            }
        }));

        StringBuilder output = new StringBuilder();
        for (CompetitionDetail comp : competitions) {
            output.append("Full Name:").append(reverseName(comp.getFullName())).append("\n")
                    .append("Rank:").append(comp.getPreliminaryRank()).append(" | ")
                    .append(comp.getSemifinalRank()).append(" | ")
                    .append(comp.getFinalsRank()).append("\n")
                    .append("Preliminary Score:").append(comp.getPreliminaryScore()).append("\n")
                    .append("Semifinal Score:").append(comp.getSemifinalScore()).append("\n")
                    .append("Final Score:").append(comp.getFinalScore()).append("\n")
                    .append("-----\n");
            testExample.add(comp.toString());
        }

        FileUtils.writeStringToFile(new File(outputFile), output.toString(), StandardCharsets.UTF_8, true);
        return testExample;
    }




    public static void choose(List<String> commands, String outputFile) throws IOException {
        for (String command : commands) {
            command = command.trim();
            if (command.equals("players")) {
                fetchAndWriteAthletes(outputFile);
            } else if (command.startsWith("result ")&&!command.endsWith(" detail")) {
                if(checkSpaces(command)){
                    String event = command.substring(7).trim();
                    if(check(event)) {
                        WriteCompetition(outputFile, event);
                    }else {
                        print("N/A\n",outputFile);
                        print("-----\n",outputFile);}
                }else {
                    print("N/A\n",outputFile);
                    print("-----\n",outputFile);
                }


            } else if (command.startsWith("result ") && command.endsWith(" detail")) {
                if(checkSpaces(command)){
                    String event = command.substring(7, command.length() - 7).trim();
                    if(check(event)) {
                        writeCompetitionDetails(outputFile, event);
                    }else {
                        print("N/A\n",outputFile);
                        print("-----\n",outputFile);}
                }else {
                    print("N/A\n",outputFile);
                    print("-----\n",outputFile);
                }

            } else {
                print("error\n",outputFile);
                print("-----\n",outputFile);
            }
        }
        System.out.println("处理完成，结果已保存至 " + outputFile);
    }



    public static boolean check(String competition)  {
        return Const.EVENTS.contains(competition);
    }


    public static void print(String warning,String outputFile) throws IOException {
        FileUtils.writeStringToFile(new File(outputFile), warning, StandardCharsets.UTF_8, true);

    }
    public static boolean checkSpaces(String str) {

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) == ' ') {
                return false;
            }
        }
        if (str.length()<=13){
            return false;
        }
        return true;
    }


    public static String reverseName(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input; // 空输入返回原值
        }

        String trimmedInput = input.trim();

        if (trimmedInput.contains("&")) {
            // 有&，分割左右两侧
            String[] sides = trimmedInput.split("&", 2); // 假设只有一个&
            if (sides.length == 2) {
                // 处理左侧
                String leftTrim = sides[0].trim();
                String[] leftParts = leftTrim.split("\\s+");
                String leftReversed = (leftParts.length == 2) ? leftParts[1] + " " + leftParts[0] : leftTrim;

                // 处理右侧
                String rightTrim = sides[1].trim();
                String[] rightParts = rightTrim.split("\\s+");
                String rightReversed = (rightParts.length == 2) ? rightParts[1] + " " + rightParts[0] : rightTrim;

                return leftReversed + " & " + rightReversed; // 保持顺序：左侧反转 & 右侧反转
            } else {
                return trimmedInput; // 异常情况返回原值
            }
        } else {
            // 无&，直接反转
            String[] parts = trimmedInput.split("\\s+");
            if (parts.length == 2) {
                return parts[1] + " " + parts[0];
            } else {
                return trimmedInput; // 如果不是两个部分，返回原值
            }
        }
    }
}