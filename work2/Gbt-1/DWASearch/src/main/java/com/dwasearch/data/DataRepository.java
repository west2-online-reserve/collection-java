package com.dwasearch.data;

import com.dwasearch.model.CompetitionData;
import com.dwasearch.model.EventResult;
import com.dwasearch.model.FinalResult;
import com.dwasearch.model.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static final List<String> REQUIRED_EVENTS = List.of(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );

    private final ObjectMapper mapper = new ObjectMapper();

    public CompetitionData load(Path path) throws IOException, DataException {
        JsonNode root;
        try {
            root = mapper.readTree(path.toFile());
        } catch (JsonProcessingException e) {
            throw new DataException("数据 JSON 无法解析: " + path, e);
        } catch (IOException e) {
            throw new IOException("无法读取数据文件: " + path, e);
        }

        if (root == null || !root.isObject()) {
            throw new DataException("数据 JSON 根节点必须是对象");
        }

        List<Player> players = new ArrayList<>();
        for (JsonNode node : array(root, "players")) {
            players.add(new Player(
                    text(node, "firstName"),
                    text(node, "lastName"),
                    text(node, "gender"),
                    text(node, "country")
            ));
        }

        List<EventResult> events = new ArrayList<>();
        for (JsonNode eventNode : array(root, "events")) {
            List<FinalResult> results = new ArrayList<>();
            for (JsonNode resultNode : array(eventNode, "results")) {
                List<BigDecimal> scores = new ArrayList<>();
                for (JsonNode scoreNode : array(resultNode, "diveScores")) {
                    if (!scoreNode.isTextual()) {
                        throw new DataException("diveScores 中的分数必须是字符串");
                    }
                    scores.add(decimal(scoreNode.asText(), "diveScores"));
                }
                results.add(new FinalResult(
                        text(resultNode, "fullName"),
                        integer(resultNode, "rank"),
                        scores,
                        decimal(text(resultNode, "totalScore"), "totalScore")
                ));
            }
            events.add(new EventResult(text(eventNode, "eventName"), results));
        }

        CompetitionData data = new CompetitionData(players, events);
        validateSnapshot(data);
        return data;
    }

    private void validateSnapshot(CompetitionData data) throws DataException {
        if (data.getPlayers().isEmpty()) {
            throw new DataException("数据快照缺少选手");
        }

        for (String requiredName : REQUIRED_EVENTS) {
            EventResult event = null;
            for (EventResult candidate : data.getEvents()) {
                if (candidate.getEventName().equals(requiredName)) {
                    event = candidate;
                    break;
                }
            }

            if (event == null) {
                throw new DataException("数据快照缺少必需项目: " + requiredName);
            }

            if (event.getResults().isEmpty()) {
                throw new DataException("项目没有决赛成绩: " + requiredName);
            }

            for (FinalResult result : event.getResults()) {
                if (result.getDiveScores().isEmpty()) {
                    throw new DataException(
                            "选手缺少各跳成绩: " + requiredName + " / " + result.getFullName()
                    );
                }
            }
        }

        if (data.getEvents().size() != REQUIRED_EVENTS.size()) {
            throw new DataException(
                    "数据快照项目数量不正确，期望 8 个，实际 "
                            + data.getEvents().size() + " 个"
            );
        }
    }

    private JsonNode array(JsonNode parent, String field) throws DataException {
        JsonNode node = parent.get(field);
        if (node == null || !node.isArray()) {
            throw new DataException("缺少数组字段: " + field);
        }
        return node;
    }

    private String text(JsonNode parent, String field) throws DataException {
        JsonNode node = parent.get(field);
        if (node == null || !node.isTextual() || node.asText().isBlank()) {
            throw new DataException("缺少或非法文本字段: " + field);
        }
        return node.asText();
    }

    private int integer(JsonNode parent, String field) throws DataException {
        JsonNode node = parent.get(field);
        if (node == null || !node.isIntegralNumber()) {
            throw new DataException("缺少或非法整数字段: " + field);
        }
        return node.asInt();
    }

    private BigDecimal decimal(String value, String field) throws DataException {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new DataException("字段不是合法数字: " + field + " = " + value, e);
        }
    }
}