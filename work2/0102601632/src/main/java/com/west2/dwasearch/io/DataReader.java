package com.west2.dwasearch.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.west2.dwasearch.data.DataStore;
import com.west2.dwasearch.data.DiveScore;
import com.west2.dwasearch.data.EventCatalog;
import com.west2.dwasearch.data.EventResult;
import com.west2.dwasearch.data.Player;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataReader {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String ATHLETES_FILE = "data/athletes.json";
    private static final String FINALS = "Finals";

    public DataStore loadFromClasspath() throws DataException {
        List<Player> players = readPlayers(readResource(ATHLETES_FILE), ATHLETES_FILE);

        Map<String, List<EventResult>> results = new HashMap<>();
        String[] keywords = EventCatalog.keywords();
        for (int i = 0; i < keywords.length; i++) {
            String fileName = "data/" + EventCatalog.fileNameOf(keywords[i]);
            results.put(keywords[i], readEvent(readResource(fileName), fileName));
        }
        return new DataStore(players, results);
    }

    public DataStore loadFromDirectory(Path dataDir) throws DataException {
        Path athletesFile = dataDir.resolve("athletes.json");
        List<Player> players = readPlayers(readFile(athletesFile), athletesFile.toString());

        Map<String, List<EventResult>> results = new HashMap<>();
        String[] keywords = EventCatalog.keywords();
        for (int i = 0; i < keywords.length; i++) {
            Path eventFile = dataDir.resolve(EventCatalog.fileNameOf(keywords[i]));
            results.put(keywords[i], readEvent(readFile(eventFile), eventFile.toString()));
        }
        return new DataStore(players, results);
    }

    private byte[] readResource(String name) throws DataException {
        InputStream in = DataReader.class.getClassLoader().getResourceAsStream(name);
        if (in == null) {
            throw new DataException("数据文件不存在：" + name);
        }
        try (InputStream input = in) {
            return input.readAllBytes();
        } catch (IOException e) {
            throw new DataException("数据文件读取失败：" + name + "（" + e.getMessage() + "）", e);
        }
    }

    private byte[] readFile(Path file) throws DataException {
        if (!Files.exists(file)) {
            throw new DataException("数据文件不存在：" + file.toAbsolutePath());
        }
        if (!Files.isRegularFile(file)) {
            throw new DataException("数据文件不是普通文件：" + file.toAbsolutePath());
        }
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new DataException("数据文件读取失败：" + file.toAbsolutePath() + "（" + e.getMessage() + "）", e);
        }
    }

    private JsonNode parse(byte[] json, String fileName) throws DataException {
        if (json.length == 0) {
            throw new DataException("数据文件是空的：" + fileName);
        }
        try {
            JsonNode root = MAPPER.readTree(json);
            if (root == null || root.isNull() || root.isMissingNode()) {
                throw new DataException("数据文件里没有内容：" + fileName);
            }
            return root;
        } catch (DataException e) {
            throw e;
        } catch (Exception e) {
            throw new DataException("数据文件不是合法的 json：" + fileName + "（" + e.getMessage() + "）", e);
        }
    }

    private List<Player> readPlayers(byte[] json, String fileName) throws DataException {
        JsonNode root = parse(json, fileName);
        if (!root.isArray()) {
            throw new DataException("数据格式不正确：最外层应该是数组（" + fileName + "）");
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < root.size(); i++) {
            JsonNode country = root.get(i);
            String countryName = requiredText(country, "CountryName", fileName);

            JsonNode participations = country.get("Participations");
            if (participations == null || !participations.isArray()) {
                throw new DataException("数据格式不正确：缺少 Participations 数组（" + fileName + "）");
            }

            for (int j = 0; j < participations.size(); j++) {
                JsonNode item = participations.get(j);
                String lastName = requiredText(item, "PreferredLastName", fileName);
                String firstName = requiredText(item, "PreferredFirstName", fileName);

                JsonNode genderNode = item.get("Gender");
                if (genderNode == null || !genderNode.isNumber()) {
                    throw new DataException("数据格式不正确：缺少字段 Gender（" + fileName + "）");
                }
                String gender = genderNode.asInt() == 1 ? "Female" : "Male";

                players.add(new Player(firstName, lastName, gender, countryName));
            }
        }
        return players;
    }

    private List<EventResult> readEvent(byte[] json, String fileName) throws DataException {
        JsonNode root = parse(json, fileName);

        JsonNode heats = root.get("Heats");
        if (heats == null || !heats.isArray()) {
            throw new DataException("数据格式不正确：缺少 Heats 数组（" + fileName + "）");
        }

        JsonNode finals = null;
        for (int i = 0; i < heats.size(); i++) {
            if (FINALS.equals(heats.get(i).path("Name").asText())) {
                finals = heats.get(i);
                break;
            }
        }
        if (finals == null) {
            throw new DataException("数据格式不正确：找不到决赛（Finals）成绩（" + fileName + "）");
        }

        JsonNode results = finals.get("Results");
        if (results == null || !results.isArray()) {
            throw new DataException("数据格式不正确：决赛缺少 Results 数组（" + fileName + "）");
        }
        if (results.size() == 0) {
            throw new DataException("决赛成绩是空的（" + fileName + "），不输出空结果");
        }

        List<EventResult> list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            JsonNode item = results.get(i);
            String fullName = requiredText(item, "FullName", fileName);

            JsonNode rankNode = item.get("Rank");
            if (rankNode == null || !rankNode.isNumber()) {
                throw new DataException("数据格式不正确：缺少字段 Rank（" + fileName + "）");
            }
            String totalPoints = requiredNumber(item, "TotalPoints", fileName);

            JsonNode dives = item.get("Dives");
            if (dives == null || !dives.isArray() || dives.size() == 0) {
                throw new DataException("数据格式不正确：缺少 Dives 成绩（" + fileName + "）");
            }

            List<DiveScore> diveScores = new ArrayList<>();
            for (int k = 0; k < dives.size(); k++) {
                JsonNode dive = dives.get(k);
                String points = requiredNumber(dive, "DivePoints", fileName);
                JsonNode orderNode = dive.get("DiveOrder");
                int order = orderNode != null && orderNode.isNumber() ? orderNode.asInt() : k + 1;
                diveScores.add(new DiveScore(order, points));
            }

            list.add(new EventResult(fullName, rankNode.asInt(), totalPoints, diveScores));
        }

        Collections.sort(list, new Comparator<EventResult>() {
            @Override
            public int compare(EventResult a, EventResult b) {
                return Integer.compare(a.getRank(), b.getRank());
            }
        });
        return list;
    }

    private String requiredText(JsonNode node, String field, String fileName) throws DataException {
        JsonNode value = node.get(field);
        if (value == null || value.isNull() || !value.isTextual() || value.asText().trim().isEmpty()) {
            throw new DataException("数据格式不正确：缺少字段 " + field + "（" + fileName + "）");
        }
        return value.asText();
    }

    private String requiredNumber(JsonNode node, String field, String fileName) throws DataException {
        String text = requiredText(node, field, fileName);
        try {
            new BigDecimal(text.trim());
        } catch (NumberFormatException e) {
            throw new DataException("数据格式不正确：字段 " + field + " 不是数字（" + fileName + "）", e);
        }
        return text;
    }
}
