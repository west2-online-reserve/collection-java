import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoreModuleTest {

    private CoreModule coreModule;
    private Path classpathRoot;
    private final List<Path> filesToCleanup = new ArrayList<>();

    @BeforeEach
    void setUp() throws URISyntaxException {
        coreModule = new CoreModule();

        // 1. 获取 classpath 根目录的 Path 对象
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceUrl = classLoader.getResource("");
        if (resourceUrl == null) {
            fail("Classpath root directory not found.");
        }
        classpathRoot = Paths.get(resourceUrl.toURI());
    }

    @AfterEach
    void tearDown() {
        // 3. 测试结束后，自动清理所有创建的临时文件
        for (Path file : filesToCleanup) {
            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                System.err.println("Warning: Failed to delete temporary file " + file);
                e.printStackTrace();
            }
        }
        filesToCleanup.clear();
    }

    /**
     * 辅助方法：在 classpath 下创建一个测试文件，并返回其文件名。
     */
    private String createTestFileInClasspath(String fileName, String content) throws IOException {
        Path filePath = classpathRoot.resolve(fileName);
        // 确保父目录存在（通常 classpath 根目录已存在）
        Files.createDirectories(filePath.getParent());
        // 写入文件内容
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(content);
        }
        // 记录文件路径，以便后续清理
        filesToCleanup.add(filePath);
        // 返回文件名，供 CoreModule 加载
        return fileName;
    }

    // ==================== 测试运动员信息提取 ====================
    @Test
    void testPlayerInfo_NotEmpty() {
        String info = coreModule.playerInfo();
        assertFalse(info.isEmpty(), "运动员信息不应为空");
        assertTrue(info.contains("Full Name:"), "信息应包含运动员姓名");
        assertTrue(info.contains("Gender:"), "信息应包含性别");
        assertTrue(info.contains("Country:"), "信息应包含国家");
    }

    @Test
    void testPlayerInfo_GenderConversion() {
        String info = coreModule.playerInfo();
        assertTrue(info.contains("Gender:Male") || info.contains("Gender:Female"), "性别应转换为 Male 或 Female");
    }

    // ==================== 测试决赛成绩提取 ====================
    @Test
    void testFinalResults_ValidData() throws IOException {
        String testJson = """
                {
                  "DisciplineName": "Men 10m Platform",
                  "Heats": [
                    {
                      "PhaseName": "Finals",
                      "Results": [
                        {
                          "FullName": "TEST Athlete",
                          "Rank": 1,
                          "TotalPoints": "500.00",
                          "Dives": [
                            {"DivePoints": "100.00"},
                            {"DivePoints": "100.00"},
                            {"DivePoints": "100.00"},
                            {"DivePoints": "100.00"},
                            {"DivePoints": "100.00"}
                          ]
                        }
                      ]
                    }
                  ]
                }
                """;
        String fileName = createTestFileInClasspath("test_final.json", testJson);

        String result = coreModule.finalResults(fileName);
        System.out.print(result);
        assertTrue(result.contains("Full Name:TEST Athlete"), "应包含测试运动员姓名");
        assertTrue(result.contains("Rank:1"), "排名应正确");
        assertTrue(result.contains("Score:100.0 + 100.0 + 100.0 + 100.0 + 100.0 = 500.00"), "得分计算应正确");
    }

    @Test
    void testFinalResults_NoFinalsPhase() throws IOException {
        String testJson = """
                {
                  "DisciplineName": "Men 10m Platform",
                  "Heats": [
                    {
                      "PhaseName": "Preliminary",
                      "Results": [
                        {
                          "FullName": "TEST Athlete",
                          "Rank": 1,
                          "TotalPoints": "500.00",
                          "Dives": [{"DivePoints": "100.00"}]
                        }
                      ]
                    }
                  ]
                }
                """;
        String fileName = createTestFileInClasspath("test_no_finals.json", testJson);

        String result = coreModule.finalResults(fileName);
        assertTrue(result.contains("Rank:*"), "无决赛时排名应为 *");
        assertTrue(result.contains("Score: * "), "无决赛得分时应为 *");
    }

    // ==================== 测试详细成绩提取 ====================
    @Test
    void testDetailedResults_AllPhases() throws IOException {
        String testJson = """
                {
                  "DisciplineName": "Men 10m Platform",
                  "Heats": [
                    {
                      "PhaseName": "Preliminary",
                      "Results": [{"FullName": "TEST Athlete", "Rank": 2, "TotalPoints": "180.00", "Dives": [{"DivePoints": "80.00"},{"DivePoints": "100.00"}]}]
                    },
                    {
                      "PhaseName": "Semifinals",
                      "Results": [{"FullName": "TEST Athlete", "Rank": 3, "TotalPoints": "450.00", "Dives": [{"DivePoints": "90.00"},{"DivePoints": "90.00"},{"DivePoints": "90.00"},{"DivePoints": "90.00"},{"DivePoints": "90.00"}]}]
                    },
                    {
                      "PhaseName": "Finals",
                      "Results": [{"FullName": "TEST Athlete", "Rank": 1, "TotalPoints": "100.00", "Dives": [{"DivePoints": "100.00"}]}]
                    }
                  ]
                }
                """;
        String fileName = createTestFileInClasspath("test_detailed.json", testJson);

        String result = coreModule.detailedResults(fileName);
        System.out.print(result);
        assertTrue(result.contains("Rank:2 | 3 | 1"), "各阶段排名应正确拼接");
        assertTrue(result.contains("Preliminary Score:80.0 + 100.0 = 180.00"), "初赛得分应正确");
        assertTrue(result.contains("Semifinal Score:90.0 + 90.0 + 90.0 + 90.0 + 90.0 = 450.00"), "半决赛得分应正确");
        assertTrue(result.contains("Final Score:100.0 = 100.00"), "决赛得分应正确");
    }

    // ==================== 测试异常处理 ====================
    @Test
    void testExtractCompetitionResults_FileNotFound() {
        // 传递一个不存在的文件名
        String result = coreModule.finalResults("non_existent_file.json");
        assertTrue(result.isEmpty(), "文件不存在时应返回空结果");
    }

    @Test
    void testExtractCompetitionResults_InvalidJson() throws IOException {
        String fileName = createTestFileInClasspath("invalid_json.json", "this is not a valid json string");

        String result = coreModule.finalResults(fileName);
        assertTrue(result.isEmpty(), "无效 JSON 应返回空结果");
    }

    // ==================== 测试空数据场景 ====================
    @Test
    void testDetailedResults_EmptyResults() throws IOException {
        String testJson = """
                {
                  "DisciplineName": "Men 10m Platform",
                  "Heats": [
                    {
                      "PhaseName": "Finals",
                      "Results": []
                    }
                  ]
                }
                """;
        String fileName = createTestFileInClasspath("empty_results.json", testJson);

        String result = coreModule.detailedResults(fileName);
        assertTrue(result.isEmpty(), "无选手成绩时应返回空结果");
    }
}