// 数据仓库 —— 负责从 classpath 资源（data/data.json）加载并缓存整个数据包，
// 提供 getAllPlayers() 返回所有选手列表，提供 findEventByCommand(String) 根据命令字符串匹配赛事项目。
// 加载失败时应抛出明确的异常信息；数据只读，不得修改原始 JSON 文件。
package com.dwa.core;

import com.dwa.model.DataFile;
import com.dwa.model.Event;
import com.dwa.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class DataRepository {
    private final DataFile dataFile;

    public DataRepository() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/data.json")) {
            if (is == null) {
                throw new IOException("data/data.json not found in classpath");
            }
            dataFile = mapper.readValue(is, DataFile.class);
        }
    }

    public List<Player> getAllPlayers() {
        return dataFile.getPlayers() != null
                ? Collections.unmodifiableList(dataFile.getPlayers())
                : Collections.emptyList();
    }

    public Event findEventByCommand(String command) {
        if (command == null || dataFile.getEvents() == null) return null;
        for (Event event : dataFile.getEvents()) {
            if (command.equals(event.getCommand())) {
                return event;
            }
        }
        return null;
    }
}
