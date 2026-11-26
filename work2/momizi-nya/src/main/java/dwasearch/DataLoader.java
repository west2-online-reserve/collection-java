package dwasearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class DataLoader {

    public static class BadDataException extends RuntimeException {
        public BadDataException(String msg) {
            super(msg);
        }

        public BadDataException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static GameData load(Path file) {

        if (!Files.exists(file)) {
            throw new BadDataException("数据文件不存在: " + file.toAbsolutePath());
        }
        if (!Files.isReadable(file)) {
            throw new BadDataException("数据文件无法读取: " + file.toAbsolutePath());
        }

        String text = null;
        try {
            text = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BadDataException("读取数据文件失败: " + file.toAbsolutePath(), e);
        }

        if (text.startsWith("\uFEFF")) {
            text = text.substring(1);
        }

        JsonNode root = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(text);
        } catch (IOException e) {
            throw new BadDataException("数据 JSON 无法解析: " + file.toAbsolutePath(), e);
        }
        if (root == null || root.isNull()) {
            throw new BadDataException("数据 JSON 内容为空");
        }

        GameData data = new GameData();

        JsonNode playersNode = root.get("players");
        if (playersNode == null || !playersNode.isArray() || playersNode.size() == 0) {
            throw new BadDataException("数据缺少 players，或 players 为空");
        }
        for (int i = 0; i < playersNode.size(); i++) {
            JsonNode p = playersNode.get(i);
            if (p.get("fullName") == null || p.get("gender") == null || p.get("country") == null) {
                throw new BadDataException("players[" + i + "] 缺少 fullName / gender / country");
            }
            Player player = new Player();
            player.fullName = p.get("fullName").asText();
            player.gender = p.get("gender").asText();
            player.country = p.get("country").asText();
            if (p.get("countryCode") != null) {
                player.countryCode = p.get("countryCode").asText();
            }
            data.players.add(player);
        }

        JsonNode eventsNode = root.get("events");
        if (eventsNode == null || !eventsNode.isArray() || eventsNode.size() == 0) {
            throw new BadDataException("数据缺少 events，或 events 为空");
        }
        for (int i = 0; i < eventsNode.size(); i++) {
            JsonNode e = eventsNode.get(i);
            if (e.get("command") == null || e.get("results") == null) {
                throw new BadDataException("events[" + i + "] 缺少 command 或 results");
            }
            String command = e.get("command").asText().trim();
            if (command.startsWith("result ")) {
                command = command.substring("result ".length());
            }
            ArrayList<ResultItem> list = new ArrayList<ResultItem>();
            JsonNode resultsNode = e.get("results");
            for (int j = 0; j < resultsNode.size(); j++) {
                JsonNode r = resultsNode.get(j);
                if (r.get("fullName") == null || r.get("scores") == null || r.get("totalPoints") == null) {
                    throw new BadDataException("events[" + i + "].results[" + j
                            + "] 缺少 fullName / scores / totalPoints");
                }
                ResultItem item = new ResultItem();
                item.fullName = r.get("fullName").asText();
                if (r.get("rank") != null) {
                    item.rank = r.get("rank").asInt();
                }
                if (r.get("countryCode") != null) {
                    item.countryCode = r.get("countryCode").asText();
                }
                JsonNode scoresNode = r.get("scores");
                for (int k = 0; k < scoresNode.size(); k++) {
                    item.scores.add(scoresNode.get(k).asText());
                }
                item.totalPoints = r.get("totalPoints").asText();
                list.add(item);
            }
            data.events.put(command, list);
        }

        Iterator<Map.Entry<String, ArrayList<ResultItem>>> it = data.events.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
        }

        return data;
    }
}
