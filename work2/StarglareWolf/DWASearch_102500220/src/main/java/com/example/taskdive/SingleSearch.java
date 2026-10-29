package com.example.taskdive;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

public class SingleSearch {
    public static void SearchFor(List<Disciplines> Discipline)
    {
        ObjectMapper mapper = new ObjectMapper();
        // 重构读取数据方法
        Map<String, String>fileMap = DataUtil.readFiles("Athlete_Contest/Contest/Single");
        for(Map.Entry<String, String> entry : fileMap.entrySet())   //遍历所有文件数据
        {
            String file = entry.getValue();
            List<Phase>Phases = new ArrayList<>();
            try {
                Disciplines parts = mapper.readValue(file,
                        new TypeReference<>() {
                        }
                );
                JsonNode root = mapper.readTree(file);


                {
                    parts.setPhases(Phases);                                    // 赛事的各个阶段


                    JsonNode Heats = root.path("Heats");
                    for (JsonNode item : Heats)      // 赛事阶段处理
                    {
                        Phase phase = new Phase();
                        List<RankPoint> RankPoints = new ArrayList<>();

                        phase.setRankPoint(RankPoints);

                        phase.setHeatName(item.path("Name").asText());         //设置赛事阶段名称
                        phase.setPhaseName(item.path("PhaseName").asText());

                        JsonNode Result = item.path("Results");

                        for (JsonNode item2 : Result) {
                            List<Athlete> Athle = new ArrayList<>();
                            RankPoint rankPoint = mapper.treeToValue(item2, RankPoint.class);
                            rankPoint.setAthletes(Athle);
                            Athlete athlete = mapper.treeToValue(item2, Athlete.class);
                            List<String> DivePoints = new ArrayList<>();
                            rankPoint.setDivePoints(DivePoints);
                            JsonNode Dive = item2.path("Dives");
                            for (JsonNode item3 : Dive)        //装入各跳数据
                            {
                                rankPoint.addDivePoint(item3.path("DivePoints").asText());
                            }
                            rankPoint.addAthlete(athlete);           //装入运动员数据
                            phase.addRankPoint(rankPoint);
                        }

                        parts.addPhase(phase);             //赛程增加
                    }

                    Discipline.add(parts);
                }
               }
            catch (Exception e) {
                System.err.println("解析 JSON 文件失败!");
                e.printStackTrace();
            }
        }
    }
}
