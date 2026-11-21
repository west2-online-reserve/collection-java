package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class JsonLoaderTest {

    private final JsonLoader jsonLoader = new JsonLoader();

    // 场景1：合法JSON，正常解析
    @Test
    void loadValidJson_shouldReturnDataObject(@TempDir Path tempDir) throws IOException {
        String jsonText = """
                {
                  "players": [
                    {
                      "fullName": "Li Ming",
                      "gender": true,
                      "country": "CHN"
                    }
                  ],
                  "finals": []
                }
                """;
        Path testJsonFile = tempDir.resolve("test.json");
        Files.writeString(testJsonFile, jsonText);

        Data resultData = jsonLoader.load(testJsonFile);

        assertNotNull(resultData);
        assertFalse(resultData.getPlayers().isEmpty());
        Player player = resultData.getPlayers().get(0);
        assertEquals("Li Ming", player.getFullName());
        assertEquals(true, player.getGender());
        assertEquals("CHN", player.getCountry());
    }

    // 场景2：JSON语法损坏，预期抛出解析异常
    @Test
    void loadBrokenJson_shouldThrowParseException(@TempDir Path tempDir) throws IOException {
        String badJson = """
                {
                  "players": [
                    {
                      "fullName": "Li Ming",
                      "gender": true
                      "country": "CHN"
                    }
                  ]
                """;
        Path badFile = tempDir.resolve("bad.json");
        Files.writeString(badFile, badJson);

        assertThrows(JsonProcessingException.class, () -> {
            jsonLoader.load(badFile);
        });
    }

    // 场景3：读取项目根目录真实data.json（集成测试）
    @Test
    void loadRealDataJson() throws IOException {
        Path realJson = Path.of("data.json");
        Data data = jsonLoader.load(realJson);
        assertNotNull(data);
        assertNotNull(data.getPlayers());
        assertNotNull(data.getFinals());
    }
}
