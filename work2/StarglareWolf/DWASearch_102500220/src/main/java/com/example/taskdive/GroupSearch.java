package com.example.taskdive;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

// 数组是不能用 path 寻找的

public class GroupSearch {
    public static void SearchFor(List<Disciplines>Discipline){
        ObjectMapper mapper = new ObjectMapper();

      // 重构数据读取方法
      Map<String, String> fileMap = DataUtil.readFiles("Athlete_Contest/Contest/Group");
      for(Map.Entry<String, String> entry : fileMap.entrySet())
        {
            String file = entry.getValue();
            try{
                Disciplines parts = mapper.readValue(file, Disciplines.class);

                {
                    List<Phase> Phases = new ArrayList<>();
                    parts.setPhases(Phases);

                    JsonNode root = mapper.readTree(file);
                    JsonNode Heats = root.path("Heats");

                    for (JsonNode item0 : Heats) {

                        Phase phase = mapper.treeToValue(item0, Phase.class);   // 赛事阶段
                        List<RankPoint> RankPoints = new ArrayList<>();
                        phase.setRankPoint(RankPoints);

                        JsonNode Results = item0.path("Results");
                        // 每支队伍的数据
                        for (JsonNode item : Results) {
                            RankPoint rankPoint = new RankPoint();
                            rankPoint.setDiveRank(item.path("Rank").asText());
                            rankPoint.setTotalPoints(item.path("TotalPoints").asText());
                            List<Athlete> Athletes = new ArrayList<>();
                            List<String> DivePoints = new ArrayList<>();
                            JsonNode Dives = item.path("Dives");
                            // 队伍分数
                            for (JsonNode item1 : Dives) {
                                DivePoints.add(item1.path("DivePoints").asText());
                            }
                            rankPoint.setDivePoints(DivePoints);
                            JsonNode Competitors = item.path("Competitors");
                            // 队伍的人员组成
                            for (JsonNode item2 : Competitors) {
                                Athlete athlete = mapper.treeToValue(item2, Athlete.class);
                                Athletes.add(athlete);
                            }
                            rankPoint.setAthletes(Athletes);
                            phase.addRankPoint(rankPoint);
                        }
                        parts.addPhase(phase);
                    }
                    Discipline.add(parts);
                }

            }
            catch (Exception e){
                System.err.println("解析 JSON 文件失败!");
                e.printStackTrace();
            }
        }
    }
}
