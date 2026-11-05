import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 数据层测试：验证固定数据能正确加载且返回结构不可变。
class DataStoreTest {

    @BeforeAll
    static void loadData() {
        DataStore.loadDataToCache();
    }

    @Test
    void loadsExpectedCompetition() {
        CompetitionData cache = DataStore.getCache();
        assertEquals(1, cache.schemaVersion());
        assertTrue(cache.competition().name().contains("World Cup 2026"));
        assertEquals(89, cache.players().size());
        assertEquals(8, cache.events().size());
    }

    @Test
    void buildsFullPlayerIndex() {
        Map<String, Player> players = DataStore.getPlayersByFullName();
        assertEquals(89, players.size());
        assertTrue(players.containsKey("COLE Ellie"));
    }

    @Test
    void buildsFullEventIndex() {
        Map<String, Event> events = DataStore.getEventsByCommand();
        assertEquals(8, events.size());
        assertTrue(events.containsKey("result women 3m springboard"));
    }

    @Test
    void returnedEventMapIsImmutable() {
        Map<String, Event> events = DataStore.getEventsByCommand();
        assertThrows(UnsupportedOperationException.class, events::clear);
    }

    @Test
    void returnedPlayerMapIsImmutable() {
        Map<String, Player> players = DataStore.getPlayersByFullName();
        assertThrows(UnsupportedOperationException.class, players::clear);
    }

    @Test
    void cachedPlayerListIsImmutable() {
        List<Player> players = DataStore.getCache().players();
        assertThrows(UnsupportedOperationException.class, players::clear);
        assertFalse(players.isEmpty());
    }

    @Test
    void brokenJsonFailsClearly() {
        JsonSyntaxException e = assertThrows(JsonSyntaxException.class,
                () -> DataStore.parse(new StringReader("{\"players\": [")));
        // 报错要带位置信息，否则使用者无从排查是哪一行坏了
        assertTrue(e.getMessage().contains("line 1"), e.getMessage());
    }

    @Test
    void missingRequiredFieldsFailsClearly() {
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> DataStore.parse(new StringReader("{\"schemaVersion\": 1}")));
        assertTrue(e.getMessage().contains("结构"), e.getMessage());
    }
}
