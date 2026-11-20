package com.west2online.dwasearch.core;

import com.west2online.dwasearch.model.CompetitionData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 数据读取测试：正常解析、JSON 损坏、缺少字段。
 */
class DataLoaderTest {

    private final DataLoader loader = new DataLoader();

    /** 构造最小可用的 data.json 字符串：8 个项目齐全 + 1 名选手。 */
    private static String validJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"players\":[{\"country\":\"Australia\",\"lastName\":\"COLE\",\"firstName\":\"Ellie\",\"gender\":\"Female\"}],");
        sb.append("\"results\":{");
        List<String> keys = new ArrayList<>(Events.NAMES);
        for (int i = 0; i < keys.size(); i++) {
            sb.append("\"").append(keys.get(i)).append("\":[]");
            if (i < keys.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    @Test
    void parsesValidData() {
        CompetitionData data = loader.parse(validJson());
        assertEquals(1, data.getPlayers().size());
        assertEquals(true, data.getResults().containsKey("men 10m synchronised"));
    }

    @Test
    void corruptJsonFails() {
        assertThrows(DataException.class, () -> loader.parse("{not valid json"));
    }

    @Test
    void missingPlayersFieldFails() {
        assertThrows(DataException.class, () -> loader.parse("{\"results\":{}}"));
    }

    @Test
    void missingOneEventFails() {
        // 只给 7 个项目（缺最后一个），应明确失败
        StringBuilder sb = new StringBuilder();
        sb.append("{\"players\":[],\"results\":{");
        for (int i = 0; i < Events.NAMES.size() - 1; i++) {
            sb.append("\"").append(Events.NAMES.get(i)).append("\":[]");
            if (i < Events.NAMES.size() - 2) {
                sb.append(",");
            }
        }
        sb.append("}}");
        assertThrows(DataException.class, () -> loader.parse(sb.toString()));
    }
}
