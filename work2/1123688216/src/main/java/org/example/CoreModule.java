package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class CoreModule {

    public static void main(String[] args) {

    }

    public void dispalyAllPlayersInfo(PrintWriter writer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File("json/athletes.json"));
            if (root.isArray()) {
                for (JsonNode countryNode : root) {
                    JsonNode participations = countryNode.path("Participations");
                    if (participations.isArray()) {
                        for (JsonNode participation : participations) {
                            String firstName = participation.path("PreferredFirstName").asText();
                            String lastName = participation.path("PreferredLastName").asText();
                            String fullName = firstName + " " + lastName;

                            String gender = "0".equals(participation.path("Gender").asText()) ? "男" : "女";
                            String country = participation.path("NAT").asText();
                            String output = "Full name:" + fullName + "\n" + "Gender:" + gender + "\n" + "Country:" + country + "\n" + "-----";
                            writer.println(output);
                        }
                    }
                }
                System.out.println("全部运动员信息写入完毕");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //各类赛事的json格式相同，只需通过input.txt中的内容判断读取哪个文件即可
    public void displayResultForEachEvent(String inputComment, boolean isDetail, PrintWriter writer) {
        try {
            Map<String, Athlete> athleteMap = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();

            File inputFile = new File("json/" + inputComment + ".json");
            if (!inputFile.exists()) {
                writer.println("N/A");
                writer.println("-----");
                return;
            }
            String realPath = inputFile.getCanonicalPath();
            // 获取硬盘上真实的文件名（例如 "Women 10m Platform.json"）
            String realFileName = new File(realPath).getName();
            String expectedFileName = inputComment + ".json";
            if (!realFileName.equals(expectedFileName)) {
                writer.println("N/A"); // 虽然文件在，但大小写不对，视为不在
                writer.println("-----");
                return;
            }


            JsonNode root = objectMapper.readTree(inputFile);
            System.out.println("开始处理：" + inputComment);
            JsonNode heats = root.path("Heats");
            if (heats.isArray()) {
                for (JsonNode heat : heats) {
                    String phaseName = heat.path("PhaseName").asText();
                    JsonNode results = heat.path("Results");
                    for (JsonNode result : results) {
                        String rawName = result.path("FullName").asText();//由于部分FullName包含多人姓名，因此需要处理
                        String fullName = formatAndSortTeamNames(rawName);
                        //创建储存表
                        if (!athleteMap.containsKey(fullName)) {
                            athleteMap.put(fullName, new Athlete());
                        }
                        Athlete athlete = athleteMap.get(fullName);
                        athlete.setFullName(fullName);
                        //跳水成绩
                        JsonNode dives = result.path("Dives");
                        StringBuilder tempScore = new StringBuilder();
                        float totalScore = 0;
                        for (JsonNode dive : dives) {
                            String divePoints = dive.path("DivePoints").asText();
                            if (!divePoints.isEmpty() && !divePoints.equalsIgnoreCase("null")) {
                                if (tempScore.length() > 0) {
                                    tempScore.append(" + ");
                                }
                                tempScore.append(divePoints);
                                totalScore += Float.parseFloat(divePoints);
                            }
                        }
                        String tempScoreStr = tempScore.toString();
                        //排名查询
                        String rank = result.path("Rank").asText();
                        if (rank == "") rank = "*";
                        if (phaseName.contains("Finals")) {
                            athlete.setFinalScore(tempScoreStr);
                            athlete.setFinalRank(rank);
                            athlete.setFinalTotalPoint(totalScore);
                        } else if (phaseName.contains("Semifinals")) {
                            athlete.setSemifinalScore(tempScoreStr);
                            athlete.setSemifinalRank(rank);
                            athlete.setSeminifinalTotalPoint(totalScore);
                        } else if (phaseName.contains("Preliminaries")) {
                            athlete.setPreliminaryScore(tempScoreStr);
                            athlete.setPreliminaryRank(rank);
                            athlete.setPreliminaryTotalPoint(totalScore);
                        }
                    }

                }
            }


            List<Athlete> athleteList = new ArrayList<>(athleteMap.values());
            // 2. 使用自定义比较器进行排序
            athleteList.sort((a1, a2) -> {
                int r1, r2;
                if (!a1.getPreliminaryRank().equals("*")) {
                    r1 = parseRankToInt(a1.getPreliminaryRank());
                } else if (!a1.getSemifinalRank().equals("*")) {
                    r1 = parseRankToInt(a1.getSemifinalRank());
                } else {
                    r1 = parseRankToInt(a1.getFinalRank());
                }

                if (!a2.getPreliminaryRank().equals("*")) {
                    r2 = parseRankToInt(a2.getPreliminaryRank());
                } else if (!a2.getSemifinalRank().equals("*")) {
                    r2 = parseRankToInt(a2.getSemifinalRank());
                } else {
                    r2 = parseRankToInt(a2.getFinalRank());
                }

                return Integer.compare(r1, r2); // 升序排列：1, 2, 3...
            });

            for (Athlete athlete : athleteList) {
                writer.println("Full Name:" + athlete.getFullName());
                writer.println("Rank:" + athlete.getPreliminaryRank() + " | " + athlete.getSemifinalRank() + " | " + athlete.getFinalRank());
                if (isDetail) {
                    if (athlete.getPreliminaryTotalPoint() == 0) {
                        writer.println("Preliminary Score:" + athlete.getPreliminaryScore());
                    } else {
                        writer.println("Preliminary Score:" + athlete.getPreliminaryScore() + " = " + String.format("%.2f", athlete.getPreliminaryTotalPoint()));
                    }
                    if (athlete.getSeminifinalTotalPoint() == 0) {
                        writer.println("Semifinal Score:" + athlete.getSemifinalScore());
                    } else {
                        writer.println("Semifinal Score:" + athlete.getSemifinalScore() + " = " + String.format("%.2f", athlete.getSeminifinalTotalPoint()));
                    }
                    if (athlete.getFinalTotalPoint() == 0) {
                        writer.println("Final Score:" + athlete.getFinalScore());
                    } else {
                        writer.println("Final Score:" + athlete.getFinalScore() + " = " + String.format("%.2f", athlete.getFinalTotalPoint()));
                    }
                    writer.println("-----");

                } else {
                    if (athlete.getFinalTotalPoint() == 0) {
                        writer.println("Score:" + athlete.getFinalScore());
                    } else {
                        writer.println("Score:" + athlete.getFinalScore() + " = " + String.format("%.2f", athlete.getFinalTotalPoint()));
                    }
                    writer.println("-----");
                }
            }
            System.out.println("处理完成");
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //将比赛排名从String转为Int
    private static int parseRankToInt(String rankStr) {
        if (rankStr == null || rankStr.equals("N/A") || rankStr.isEmpty()) {
            return 999; // 没参加比赛的人排到最后
        }
        try {
            // 去掉并列符号 =，只保留数字部分
//            String cleanRank = rankStr.replace("=", "").trim();
            return Integer.parseInt(rankStr);
        } catch (NumberFormatException e) {
            return 999;
        }
    }

    public static String formatAndSortTeamNames(String rawFullName) {
        if (rawFullName == null || !rawFullName.contains("/")) {
            return rawFullName; // 如果不是多人项目，直接返回
        }

        // 1. 拆分队伍成员
        // 使用正则表达式 \\s*/\\s* 可以处理 "A/B" 或 "A / B" 的情况
        String[] members = rawFullName.split("\\s*/\\s*");

        // 2. 将成员放入 List 并根据 LastName 排序
        List<String> memberList = Arrays.asList(members);

        memberList.sort((name1, name2) -> {
            // 提取 LastName。假设格式是 "LASTNAME Firstname"
            // 这里的逻辑是取空格前的第一个单词
            String ln1 = name1.trim().split(" ")[0];
            String ln2 = name2.trim().split(" ")[0];
            return ln1.compareTo(ln2); // 字母升序
        });

        // 3. 用 " & " 重新连接
        return String.join(" & ", memberList);
    }

    public  String[] judgmentFormat(String input){
        String eventName = "";
        String isDetail="false";
        if(input.equals("players")){
            return new String[]{"players",isDetail};
        }
        if(!input.startsWith("result ")||input.length()<=7){
            return new String[]{"Error",isDetail};
        }
        if(input.endsWith(" detail")){
            //防止”result detail“的情况出现
            if(input.length()<=14){
                return new String[]{"N/A",isDetail};
            }
            eventName = input.substring(7,input.length()-7);
            isDetail = "true";
        }else{
            eventName = input.substring(7);
            isDetail = "false";
        }
        return new String[]{eventName,isDetail};
    }
}
