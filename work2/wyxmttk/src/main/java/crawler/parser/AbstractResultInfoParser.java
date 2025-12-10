package crawler.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractResultInfoParser implements Parser {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public List<ResultInfoWrapper> parseInitially(String rawData,boolean detail) {
        Map<String,ResultInfoWrapper> resultMap = new HashMap<>();

        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(rawData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode heats = rootNode.path("Heats");
        for(int i = 0; i < heats.size(); i++) {
            String stage = switch (i) {
                case 0 -> ResultInfoWrapper.FINAL_SCORE;
                case 1 -> ResultInfoWrapper.SEMIFINAL_SCORE;
                case 2 -> ResultInfoWrapper.PRELIMINARY_SCORE;
                default -> throw new RuntimeException("Unknown stage");
            };
            JsonNode resultsNode = heats.get(i).path("Results");

            for (int j = 0; j < resultsNode.size(); j++) {

                JsonNode playerNode = resultsNode.path(j);

                String fullName = playerNode.path("FullName").asText();
                if(fullName.contains("/")){
                    String[] split = fullName.split("/");
                    split[0]=split[0].trim();
                    split[1]=split[1].trim();

                    if(split[0].compareTo(split[1]) > 0){
                        String temp = split[0];
                        split[0] = split[1];
                        split[1] = temp;
                    }
                    fullName = split[0] + " & " + split[1];
                }
                ResultInfoWrapper resultInfoWrapper = resultMap.get(fullName);
                if(resultInfoWrapper == null){
                    resultInfoWrapper = new ResultInfoWrapper();
                    resultInfoWrapper.setFullName(fullName);
                    resultMap.put(fullName, resultInfoWrapper);
                }

                ResultInfoWrapper.StageResultInfo stageResultInfo = new ResultInfoWrapper.StageResultInfo();

                int rank = playerNode.path("Rank").asInt();
                stageResultInfo.setRank(rank);

                String totalScore= String.valueOf(playerNode.path("TotalPoints").asDouble());
                JsonNode dives = playerNode.path("Dives");
                StringBuilder scoreInfo = new StringBuilder();
                for(int k = 0; k < dives.size(); k++){
                    scoreInfo.append(dives.get(k).path("DivePoints").asDouble());
                    if(k < dives.size()-1){
                        scoreInfo.append(" + ");
                    }else{
                        scoreInfo.append(" = ");
                        scoreInfo.append(totalScore);
                    }
                }
                stageResultInfo.setScoreInfo(scoreInfo.toString());

                Map<String, ResultInfoWrapper.StageResultInfo>  stageResultInfoMap = resultInfoWrapper.getStageResultInfoMap();
                if(stageResultInfoMap == null){
                    stageResultInfoMap = new HashMap<>();
                }
                stageResultInfoMap.put(stage, stageResultInfo);
                resultInfoWrapper.setStageResultInfoMap(stageResultInfoMap);

                }
            }

        return resultMap.values().stream()
                .sorted(Comparator.comparing(o -> {
                    ResultInfoWrapper.StageResultInfo stageResultInfo = o.getStageResultInfoMap().get(ResultInfoWrapper.PRELIMINARY_SCORE);
                    if(stageResultInfo == null){
                        return Integer.MAX_VALUE;
                    }
                    return stageResultInfo.getRank();
                }))
                .collect(Collectors.toList());
    }
}
