package com.dwasearch.query.util;

import com.dwasearch.model.DataSnapshot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DWADataTest {
    DWAData dwaData =  new DWAData();

    @Test
    @DisplayName("读取data.json应正确加载快照")
    void readData() throws IOException {
        DataSnapshot dataSnapshot = dwaData.readData(Paths.get("data.json"));

        //检查dataSnapshot下的字段是否为空
        assertNotNull(dataSnapshot);
        assertNotNull(dataSnapshot.getSource());
        assertNotNull(dataSnapshot.getCountries());
        assertNotNull(dataSnapshot.getCompetitions());

        assertFalse(dataSnapshot.getCompetitions().isEmpty());
        assertFalse(dataSnapshot.getCountries().isEmpty());
    }

    @Test
    @DisplayName("读取错误文件路径时应抛出IOException")
    void readDataByWrongPath() throws IOException {
        Path tmp = Paths.get("dwa.json");
        assertThrows(IOException.class, () -> this.dwaData.readData(tmp));
    }

    @Test
    @DisplayName("读取的json结构错误时，应抛出IOException")
    void readDataByWrongJson() throws IOException {
        Path tmp = Files.createTempFile("dwa-mismatch", ".json");
        Files.writeString(tmp, """
        {
          "source": { "event": "x", "url": "x", "fetchedAt": "2026-10-01T06:30:00Z" },
          "players": "not-an-array",
          "results": {}
        }
        """);

        assertThrows(IOException.class, () -> dwaData.readData(tmp));

        Files.deleteIfExists(tmp);
    }
}