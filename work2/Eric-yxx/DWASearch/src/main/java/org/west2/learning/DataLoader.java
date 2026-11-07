package org.west2.learning;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DataLoader {
    private static final ObjectMapper JSON = new ObjectMapper();

    private DataLoader() {}

    public static ContestData read(Path path) throws IOException {
        ContestData data = JSON.readValue(path.toFile(), ContestData.class);
        validate(data);
        return data;
    }

    public static void validate(ContestData data) throws IOException {
        if (data == null || blank(data.source()) || blank(data.capturedAt())
                || data.players() == null || data.players().isEmpty() || data.events() == null) {
            throw new IOException("赛事 JSON 缺少来源、抓取时间、选手或项目");
        }
        Set<String> names = new HashSet<>();
        for (ContestData.Player player : data.players()) {
            if (player == null || blank(player.fullName()) || blank(player.lastName())
                    || blank(player.gender()) || blank(player.country())) {
                throw new IOException("赛事 JSON 中有不完整的选手记录");
            }
            if (!names.add(player.fullName())) {
                throw new IOException("赛事 JSON 中有重复选手: " + player.fullName());
            }
        }
        if (!data.events().keySet().equals(QueryEngine.EVENT_NAMES)) {
            throw new IOException("赛事 JSON 必须恰好包含八个指定决赛项目");
        }
        for (Map.Entry<String, List<ContestData.Result>> event : data.events().entrySet()) {
            if (event.getValue() == null || event.getValue().isEmpty()) {
                throw new IOException("赛事 JSON 中缺少决赛成绩: " + event.getKey());
            }
            Set<Integer> ranks = new HashSet<>();
            for (ContestData.Result result : event.getValue()) {
                if (result == null || blank(result.fullName()) || result.rank() <= 0
                        || !ranks.add(result.rank()) || result.scores() == null
                        || result.scores().isEmpty() || blank(result.total())) {
                    throw new IOException("赛事 JSON 中有不完整或重复排名: " + event.getKey());
                }
                try {
                    BigDecimal sum = BigDecimal.ZERO;
                    for (String score : result.scores()) {
                        BigDecimal part = new BigDecimal(score);
                        if (part.signum() < 0 || part.scale() > 2) throw new NumberFormatException(score);
                        sum = sum.add(part);
                    }
                    BigDecimal total = new BigDecimal(result.total());
                    if (total.signum() < 0 || total.scale() > 2 || sum.compareTo(total) != 0) {
                        throw new NumberFormatException("总分与逐跳得分不一致");
                    }
                } catch (NumberFormatException exception) {
                    throw new IOException("赛事 JSON 分数无效: " + event.getKey()
                            + " / " + result.fullName(), exception);
                }
            }
        }
    }

    private static boolean blank(String value) {
        return value == null || value.isBlank();
    }
}
