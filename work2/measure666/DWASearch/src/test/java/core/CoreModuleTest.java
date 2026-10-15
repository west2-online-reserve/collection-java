package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utils.Utility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CoreModuleTest {
    private final CoreModule coreModule = new CoreModule();
    // 归一化换行，避免平台 CRLF 影响比较
    private static String normalize(String s) {
        return s.replace("\r\n", "\n").replace("\r", "\n");
    }

    // 把 coreModule 的输出重定向到临时文件再读回，供断言
    private String runOutput(Runnable action, Path dir) throws Exception {
        Path in = dir.resolve("in.txt");
        Path out = dir.resolve("out.txt");
        Files.writeString(in, "");
        Utility.setInput(in.toString());
        Utility.setOutput(out.toString());
        action.run();
        Utility.close();
        return normalize(Files.readString(out));
    }

    // ---------- 选手查询：抽样核对 + 完整性 + 排序 ----------

    @Test
    void displayAthletes_firstAthleteMatchesSnapshot(@TempDir Path dir) throws Exception {
        String out = runOutput(coreModule::displayAllPlayersInfo, dir);
        String expectedFirst = """
                Full Name:COLE Ellie
                Gender:Female
                Country:Australia
                -----
                """;
        assertTrue(out.startsWith(expectedFirst),
                "排序后第一名应为 Australia 的 COLE Ellie，实际: " + out.substring(0, Math.min(out.length(), 120)));
    }

    @Test
    void displayAthletes_lastAthleteMatchesSnapshot(@TempDir Path dir) throws Exception {
        String out = runOutput(coreModule::displayAllPlayersInfo, dir);
        String expectedLast = """
                Full Name:VERZYL Sophia
                Gender:Female
                Country:United States of America
                -----
                """;
        assertTrue(out.endsWith(expectedLast), "排序后最后一名应为 United States of America 的 VERZYL Sophia");
    }

    @Test
    void displayAthletes_outputsEveryAthleteExactlyOnce(@TempDir Path dir) throws Exception {
        String out = runOutput(coreModule::displayAllPlayersInfo, dir);
        int expectedCount = countParticipations();                 // 从 athletes.json 数出 89
        assertEquals(expectedCount, countOccurrences(out, "Full Name:"),
                "输出选手数应与 athletes.json 中 Participations 总数一致");
        assertEquals(expectedCount, countOccurrences(out, "\n-----"),
                "每个选手块都应以 ----- 分隔");
    }

    @Test
    void parseAthletes_sortsByCountryThenLastName() throws IOException {
        // JSON 原始顺序打乱：Zambia 在前、Australia 在后；同国中 KOLE 在前、ABLE 在后
        String json = "["
                + "{\"CountryName\":\"Zambia\",\"Participations\":[{\"Gender\":1,\"PreferredLastName\":\"ZED\",\"PreferredFirstName\":\"Z\"}]},"
                + "{\"CountryName\":\"Australia\",\"Participations\":["
                + "{\"Gender\":0,\"PreferredLastName\":\"KOLE\",\"PreferredFirstName\":\"B\"},"
                + "{\"Gender\":1,\"PreferredLastName\":\"ABLE\",\"PreferredFirstName\":\"A\"}]}"
                + "]";
        List<String> lines = coreModule.parseAthletes(in(json));
        assertEquals(List.of(
                        "Full Name:ABLE A\nGender:Female\nCountry:Australia\n-----",
                        "Full Name:KOLE B\nGender:Male\nCountry:Australia\n-----",
                        "Full Name:ZED Z\nGender:Female\nCountry:Zambia\n-----"), lines,
                "应按国籍升序、姓氏升序排序，不依赖 JSON 原始顺序");
    }

    // ---------- 成绩查询：完整快照（抽样核对格式） ----------

    @Test
    void displayResults_men10mSynchro_matchesSnapshot(@TempDir Path dir) throws Exception {
        String out = runOutput(() -> coreModule.displayResults("men 10m synchronised"), dir);
        String expected = """
                Full Name:ZHAO Renjie & YANG Zhihao
                Rank:1
                Score:57.00 + 49.80 + 87.36 + 91.80 + 66.60 + 96.12 = 448.68
                -----
                Full Name:HRYTSENKO Mark & SEREDA Oleksii
                Rank:2
                Score:49.20 + 48.00 + 75.84 + 79.92 + 71.40 + 79.92 = 404.28
                -----
                Full Name:BERLIN REYES Kevin & WILLARS VALDEZ Randal
                Rank:3
                Score:50.40 + 47.40 + 76.50 + 81.00 + 71.04 + 76.68 = 403.02
                -----
                Full Name:MCCABE Euan & CUTMORE Ben
                Rank:4
                Score:45.60 + 48.00 + 73.44 + 74.52 + 68.82 + 66.96 = 377.34
                -----
                Full Name:CULLEN Matt & TESSIER Benjamin
                Rank:5
                Score:51.00 + 48.60 + 71.40 + 84.24 + 51.06 + 69.12 = 375.42
                -----
                Full Name:GIOVANNINI Riccardo & CONTE Simone
                Rank:6
                Score:49.20 + 47.40 + 72.00 + 73.26 + 68.40 + 60.48 = 370.74
                -----
                Full Name:MERCIECA Jonah & ROUSSEAU Cassiel
                Rank:7
                Score:47.40 + 47.40 + 71.04 + 75.84 + 65.28 + 61.05 = 368.01
                -----
                Full Name:SHLEIKHER Nikita & TERNOVOI Ruslan
                Rank:8
                Score:48.60 + 50.40 + 57.12 + 51.84 + 69.93 + 76.68 = 354.57
                -----
                """;
        assertEquals(expected, out, "成绩的顺序与格式应与本地 JSON 快照一致");
    }

    // ---------- 成绩查询：顺序/总分与 JSON 一致（JSON 驱动，覆盖多个项目） ----------

    @Test
    void displayResults_orderAndScoresFollowJson(@TempDir Path dir) throws Exception {
        for (String name : List.of("women 10m synchronised", "men 3m springboard", "men 10m platform")) {
            String out = runOutput(() -> coreModule.displayResults(name), dir);
            String expected = buildExpectedResults(name);
            assertFalse(expected.isEmpty(), "项目 " + name + " 应有决赛成绩");
            assertEquals(expected, out, "项目 " + name + " 的输出顺序与成绩应与 JSON 快照一致");
        }
    }

    @Test
    void parseResults_sortsByRank() throws IOException {
        // Results 原始顺序打乱：Rank 2 在前、Rank 1 在后
        String json = "{\"Heats\":[{\"Name\":\"Finals\",\"Results\":["
                + "{\"TotalPoints\":\"200.00\",\"Rank\":2,\"FullName\":\"B / B\",\"Dives\":[{\"DivePoints\":\"100.00\"},{\"DivePoints\":\"100.00\"}]},"
                + "{\"TotalPoints\":\"100.00\",\"Rank\":1,\"FullName\":\"A\",\"Dives\":[{\"DivePoints\":\"100.00\"}]}"
                + "]}]}";
        List<String> lines = coreModule.parseResults(in(json));
        assertEquals(List.of(
                        "Full Name:A\nRank:1\nScore:100.00 = 100.00\n-----",
                        "Full Name:B & B\nRank:2\nScore:100.00 + 100.00 = 200.00\n-----"), lines,
                "应按排名升序输出");
    }

    // ---------- 数据读取：明确失败 ----------

    @Test
    void displayResults_missingResource_throws() {
        assertThrows(RuntimeException.class, () -> coreModule.displayResults("no such contest"));
    }

    @Test
    void parseAthletes_corruptJson_throws() {
        assertThrows(IOException.class,
                () -> coreModule.parseAthletes(new ByteArrayInputStream("{ not json".getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    void parseAthletes_topLevelNotArray_throws() {
        assertThrows(IOException.class, () -> coreModule.parseAthletes(in("{\"x\":1}")));
    }

    @Test
    void parseAthletes_missingCountryName_throws() {
        String json = "[{\"Participations\":[{\"Gender\":1,\"PreferredLastName\":\"A\",\"PreferredFirstName\":\"B\"}]}]";
        assertThrows(IOException.class, () -> coreModule.parseAthletes(in(json)));
    }

    @Test
    void parseResults_missingTotalPoints_throws() {
        String json = "{\"Heats\":[{\"Name\":\"Finals\",\"Results\":[{\"Rank\":1,\"FullName\":\"A\",\"Dives\":[{\"DivePoints\":\"1.0\"}]}]}]}";
        assertThrows(IOException.class, () -> coreModule.parseResults(in(json)));
    }

    @Test
    void parseResults_missingDives_throws() {
        String json = "{\"Heats\":[{\"Name\":\"Finals\",\"Results\":[{\"TotalPoints\":\"1.0\",\"Rank\":1,\"FullName\":\"A\"}]}]}";
        assertThrows(IOException.class, () -> coreModule.parseResults(in(json)));
    }

    @Test
    void parseResults_noFinalsHeat_returnsEmpty() throws IOException {
        String json = "{\"Heats\":[{\"Name\":\"Preliminaries\",\"Results\":[{\"TotalPoints\":\"1.0\",\"Rank\":1,\"FullName\":\"A\",\"Dives\":[{\"DivePoints\":\"1.0\"}]}]}]}";
        assertTrue(coreModule.parseResults(in(json)).isEmpty(), "无 Finals 场次时不应输出任何成绩");
    }

    // ---------- 工具方法 ----------

    private static InputStream in(String s) {
        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

    private static int countOccurrences(String s, String sub) {
        int n = 0, idx = 0;
        while ((idx = s.indexOf(sub, idx)) != -1) {
            n++;
            idx += sub.length();
        }
        return n;
    }

    private static int countParticipations() throws IOException {
        JsonNode nations = new ObjectMapper().readTree(readResource("athletes.json"));
        int n = 0;
        for (JsonNode nation : nations) {
            n += nation.get("Participations").size();
        }
        return n;
    }

    private static InputStream readResource(String name) {
        return CoreModuleTest.class.getClassLoader().getResourceAsStream(name);
    }

    private static String buildExpectedResults(String contestName) throws IOException {
        JsonNode root = new ObjectMapper().readTree(readResource(contestName + ".json"));
        List<JsonNode> results = new ArrayList<>();
        for (JsonNode heat : root.get("Heats")) {
            if (!"Finals".equals(heat.get("Name").asText())) {
                continue;
            }
            for (JsonNode r : heat.get("Results")) {
                results.add(r);
            }
        }
        results.sort(Comparator.comparingInt(r -> r.get("Rank").asInt()));
        List<String> lines = new ArrayList<>();
        for (JsonNode r : results) {
            List<String> dives = new ArrayList<>();
            for (JsonNode d : r.get("Dives")) {
                dives.add(d.get("DivePoints").asText());
            }
            lines.add("Full Name:" + r.get("FullName").asText().replace('/', '&'));
            lines.add("Rank:" + r.get("Rank").asInt());
            lines.add("Score:" + String.join(" + ", dives) + " = " + r.get("TotalPoints").asText());
            lines.add("-----");
        }
        return String.join("\n", lines) + "\n";
    }
}
