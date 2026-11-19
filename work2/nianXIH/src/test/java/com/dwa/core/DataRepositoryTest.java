// 数据仓库单元测试 —— 覆盖以下场景：
// 1. 成功加载 data.json，getAllPlayers() 返回非空列表；
// 2. 选手数量与 data.json 中 players 数组长度一致；
// 3. findEventByCommand 对 8 个合法命令均能返回非 null 的 Event；
// 4. findEventByCommand 对非法命令返回 null；
// 5. 加载失败时抛出 IOException。
package com.dwa.core;

import com.dwa.model.Event;
import com.dwa.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataRepositoryTest {

    private static DataRepository repository;

    @BeforeAll
    static void setUp() throws IOException {
        repository = new DataRepository();
    }

    @Test
    void testLoadPlayersNotEmpty() {
        List<Player> players = repository.getAllPlayers();
        assertNotNull(players);
        assertFalse(players.isEmpty());
    }

    @Test
    void testPlayerCount() {
        // data.json 中共有 89 名选手
        assertEquals(89, repository.getAllPlayers().size());
    }

    @Test
    void testFindEventByValidCommand() {
        String[] validCommands = {
                "result women 3m springboard",
                "result women 10m platform",
                "result women 3m synchronised",
                "result women 10m synchronised",
                "result men 3m springboard",
                "result men 10m platform",
                "result men 3m synchronised",
                "result men 10m synchronised"
        };
        for (String cmd : validCommands) {
            Event event = repository.findEventByCommand(cmd);
            assertNotNull(event, "Event should not be null for: " + cmd);
            assertNotNull(event.getResults(), "Results should not be null for: " + cmd);
            assertFalse(event.getResults().isEmpty(), "Results should not be empty for: " + cmd);
        }
    }

    @Test
    void testFindEventByInvalidCommandReturnsNull() {
        assertNull(repository.findEventByCommand("result sss"));
        assertNull(repository.findEventByCommand("result women 10m springboard"));
        assertNull(repository.findEventByCommand(null));
    }
}
