package lib;

import java.io.*;
import java.util.*;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class CoreModule {
    private List<Athlete> athletes;
    private List<EventResult> eventResults;

    public CoreModule() {
        athletes = loadAthletesData();
        eventResults = loadEventResultsData();
    }

    //显示所有选手信息
    public void displayAllPlayersInfo(BufferedWriter writer) throws IOException {
        athletes.sort(Comparator.comparing(Athlete::getCountry).thenComparing(Athlete::getLastName));
        for (Athlete athlete : athletes) {
            writer.write(String.format("Full Name:%s\nGender:%s\nCountry:%s\n-----\n",
                    athlete.getFullName(), athlete.getGender(), athlete.getCountry()));
        }
    }

    //显示比赛结果信息
    public void displayResultsForEvent(BufferedWriter writer, String eventName) throws IOException {
        for (EventResult result : eventResults) {
            if (result.getEventName().equalsIgnoreCase(eventName)) {
                writer.write(String.format("Full Name:%s\nRank:%d\nScore:%s\n-----\n",
                        result.getAthleteName(), result.getRank(), result.getScoreString()));
            }
        }
    }

    //显示详细比赛结果信息
    public void displayDetailedResults(BufferedWriter writer, String eventName) throws IOException {
        // 筛选指定赛事的结果
        List<EventResult> filteredResults = new ArrayList<>();
        for (EventResult result : eventResults) {
            if (result.getEventName().equalsIgnoreCase(eventName)) {
                filteredResults.add(result);
            }
        }

        // 按照要求排序
        filteredResults.sort(new Comparator<EventResult>() {
            @Override
            public int compare(EventResult o1, EventResult o2) {
                // 处理双人项目排序
                if (isSynchronizedEvent(eventName)) {
                    String lastName1 = getFirstLastName(o1.getAthleteName());
                    String lastName2 = getFirstLastName(o2.getAthleteName());
                    return lastName1.compareTo(lastName2);
                } else {
                    // 优先按照决赛排名排序
                    int rank1 = parseRank(o1.getFinalRank());
                    int rank2 = parseRank(o2.getFinalRank());
                    if (rank1 != -1 && rank2 != -1) {
                        return Integer.compare(rank1, rank2);
                    }
                    // 如果没有决赛排名，按照半决赛排名
                    rank1 = parseRank(o1.getSemifinalRank());
                    rank2 = parseRank(o2.getSemifinalRank());
                    if (rank1 != -1 && rank2 != -1) {
                        return Integer.compare(rank1, rank2);
                    }
                    // 如果没有半决赛排名，按照初赛排名
                    rank1 = parseRank(o1.getPreliminaryRank());
                    rank2 = parseRank(o2.getPreliminaryRank());
                    if (rank1 != -1 && rank2 != -1) {
                        return Integer.compare(rank1, rank2);
                    }
                    // 如果都没有排名，保持原顺序
                    return 0;
                }
            }

            private int parseRank(String rankStr) {
                if (rankStr != null && !rankStr.equals("*")) {
                    try {
                        return Integer.parseInt(rankStr);
                    } catch (NumberFormatException e) {
                        return -1;
                    }
                }
                return Integer.MAX_VALUE; // 未参加该阶段比赛，放在后面
            }
        });

        // 输出结果
        for (EventResult result : filteredResults) {
            writer.write(String.format("Full Name:%s\n", result.getAthleteName()));
            writer.write(String.format("Rank:%s\n", result.getRankString()));
            writer.write(String.format("Preliminary Score:%s\n", result.getPreliminaryScore() != null && !result.getPreliminaryScore().equals("*") ? formatScore(result.getPreliminaryScore()) : "*"));
            writer.write(String.format("Semifinal Score:%s\n", result.getSemifinalScore() != null && !result.getSemifinalScore().equals("*") ? formatScore(result.getSemifinalScore()) : "*"));
            writer.write(String.format("Final Score:%s\n", result.getFinalScore() != null && !result.getFinalScore().equals("*") ? formatScore(result.getFinalScore()) : "*"));
            writer.write("-----\n");
        }
    }

    // 判断是否为双人项目
    private boolean isSynchronizedEvent(String eventName) {
        return eventName.toLowerCase().contains("synchronised") || eventName.toLowerCase().contains("synchronized");
    }

    // 获取双人项目中第一个选手的姓氏
    private String getFirstLastName(String athleteName) {
        String[] names = athleteName.split("&");
        String firstName = names[0].trim();
        String[] nameParts = firstName.split(" ");
        return nameParts[nameParts.length - 1];
    }

    private String formatScore(String scoreString) {
        String[] scores = scoreString.split("\\+");
        double total = 0.0;
        StringBuilder formattedScores = new StringBuilder();
        for (String score : scores) {
            String trimmedScore = score.trim();
            if (!trimmedScore.isEmpty()) {
                double scoreValue = Double.parseDouble(trimmedScore);
                total += scoreValue;
                formattedScores.append(String.format("%.2f", scoreValue)).append(" + ");
            }
        }
        if (formattedScores.length() > 3) {
            formattedScores.setLength(formattedScores.length() - 3); // 移除最后的 " + "
        }
        return formattedScores.toString() + " = " + String.format("%.2f", total);
    }

    //导入选手数据
    private List<Athlete> loadAthletesData() {
        List<Athlete> athletesList = new ArrayList<>();
        try {
            String jsonStr = new String(new FileInputStream(new File("data/athletes.json")).readAllBytes());
            JSONArray root = JSON.parseArray(jsonStr);
            for (Object countryObj : root) {
                JSONObject countryNode = (JSONObject) countryObj;
                JSONArray participations = countryNode.getJSONArray("Participations");
                for (Object athleteObj : participations) {
                    JSONObject athleteNode = (JSONObject) athleteObj;
                    String fullName = athleteNode.getString("PreferredLastName") + " " + athleteNode.getString("PreferredFirstName");
                    String gender = athleteNode.getInteger("Gender") == 0 ? "Male" : "Female";
                    String country = countryNode.getString("CountryName");
                    athletesList.add(new Athlete(fullName, gender, country));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return athletesList;
    }

    //导入比赛结果信息数据
    private List<EventResult> loadEventResultsData() {
        List<EventResult> eventResultsList = new ArrayList<>();
        Map<String, EventResult> eventResultMap = new HashMap<>();
        try {
            String[] eventFiles = {
                    "data/men 1m springboard.json",
                    "data/men 3m springboard.json",
                    "data/men 3m synchronlsed.json",
                    "data/men 10m synchronlsed.json",
                    "data/men 10m platform.json",
                    "data/women 1m springboard.json",
                    "data/women 3m springboard.json",
                    "data/women 3m synchronlsed.json",
                    "data/women 10m synchronlsed.json",
                    "data/women 10m platform.json"
            };
            for (String eventFile : eventFiles) {
                String jsonStr = new String(new FileInputStream(new File(eventFile)).readAllBytes());
                JSONObject root = JSON.parseObject(jsonStr);
                JSONArray heats = root.getJSONArray("Heats");
                for (Object heatObj : heats) {
                    JSONObject heat = (JSONObject) heatObj;
                    String heatName = heat.getString("Name").toLowerCase();
                    JSONArray results = heat.getJSONArray("Results");
                    for (Object resultObj : results) {
                        JSONObject resultNode = (JSONObject) resultObj;
                        String eventName = root.getString("DisciplineName");
                        String athleteName = resultNode.getString("FullName");
                        int rank = resultNode.getIntValue("Rank");

                        StringBuilder scoreBuilder = new StringBuilder();
                        JSONArray dives = resultNode.getJSONArray("Dives");
                        for (Object diveObj : dives) {
                            JSONObject dive = (JSONObject) diveObj;
                            scoreBuilder.append(dive.getString("DivePoints")).append(" + ");
                        }
                        String scoreString = scoreBuilder.length() > 3 ? scoreBuilder.substring(0, scoreBuilder.length() - 3) : "*";

                        // 构建唯一键，用于识别同一位选手在同一赛事中的成绩
                        String key = eventName + "_" + athleteName;
                        EventResult eventResult = eventResultMap.getOrDefault(key, new EventResult(eventName, athleteName));

                        if (heatName.contains("preliminaries") || heatName.contains("preliminary")) {
                            eventResult.setPreliminaryRank(String.valueOf(rank));
                            eventResult.setPreliminaryScore(scoreString);
                        } else if (heatName.contains("semifinals") || heatName.contains("semifinal")) {
                            eventResult.setSemifinalRank(String.valueOf(rank));
                            eventResult.setSemifinalScore(scoreString);
                        } else if (heatName.contains("finals") || heatName.contains("final")) {
                            eventResult.setFinalRank(String.valueOf(rank));
                            eventResult.setFinalScore(scoreString);
                            eventResult.setRank(rank); // 设置总排名
                            eventResult.setScoreString(scoreString); // 设置总成绩
                        }

                        // 更新总排名字符串
                        String rankString = String.format("%s | %s | %s",
                                eventResult.getPreliminaryRank() != null ? eventResult.getPreliminaryRank() : "*",
                                eventResult.getSemifinalRank() != null ? eventResult.getSemifinalRank() : "*",
                                eventResult.getFinalRank() != null ? eventResult.getFinalRank() : "*");
                        eventResult.setRankString(rankString);

                        // 将更新后的结果放回Map中
                        eventResultMap.put(key, eventResult);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将Map中的所有EventResult添加到列表中
        eventResultsList.addAll(eventResultMap.values());
        return eventResultsList;
    }
}
