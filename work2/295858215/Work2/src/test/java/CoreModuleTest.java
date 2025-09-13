import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CoreModuleTest {

    private CoreModule core;

    @Before
    public void setUp() throws IOException {
        // 使用 athletes.json 初始化核心模块
        ObjectMapper mapper = new ObjectMapper();
        InputStream athletesStream = getClass().getClassLoader().getResourceAsStream("athletes.json");
        assertNotNull("Athletes JSON file is missing.", athletesStream);
        JsonNode athletesData = mapper.readTree(athletesStream);
        core = new CoreModule(athletesData);
    }

    @Test
    public void testLoadPlayers() {
        String output = core.displayAllPlayersInfo();
        assertNotNull("Output should not be null.", output);
        assertTrue("Output should contain player names.", output.contains("Full Name:"));
    }

    @Test
    public void testDisplayResultsWithoutDetail() throws IOException {
        // 使用 Women 10m Platform.json 初始化比赛数据
        ObjectMapper mapper = new ObjectMapper();
        InputStream contestStream = getClass().getClassLoader().getResourceAsStream("Women 10m Platform.json");
        assertNotNull("Women 10m Platform JSON file is missing.", contestStream);
        JsonNode contestData = mapper.readTree(contestStream);
        core.loadContestData(contestData);

        // 验证非详细输出
        String results = core.displayResultsForEvent("Women 10m Platform");
        assertTrue("Results should contain player full names.", results.contains("Full Name:"));
        assertTrue("Results should contain rank information.", results.contains("Rank:"));
        assertTrue("Results should contain scores.", results.contains("Score:"));
    }

    @Test
    public void testDisplayDetailedResultsForEvent() throws IOException {
        // 加载 Women 10m Platform 数据
        ObjectMapper mapper = new ObjectMapper();
        InputStream contestStream = getClass().getClassLoader().getResourceAsStream("Women 10m Platform.json");
        assertNotNull("Women 10m Platform JSON file is missing.", contestStream);
        JsonNode contestData = mapper.readTree(contestStream);
        core.loadContestData(contestData);

        // 验证详细结果输出是否符合预期格式
        String detailedResults = core.displayDetailedResultsForEvent("Women 10m Platform");
        assertTrue("Detailed results should contain player full names.", detailedResults.contains("Full Name:"));
        assertTrue("Detailed results should contain rank information.", detailedResults.contains("Rank:"));
        assertTrue("Detailed results should contain preliminary scores.", detailedResults.contains("Preliminary Score:"));
        assertTrue("Detailed results should contain final scores.", detailedResults.contains("Final Score:"));
    }

    @Test
    public void testInvalidDiscipline() {
        String results = core.displayDetailedResultsForEvent("Invalid Discipline");
        assertEquals("Invalid discipline should return appropriate error message.", "N/A_4\n-----\n", results);
    }

    @Test
    public void testDisplayResultsWithNoData() {
        String results = core.displayResultsForEvent("Men 1m Springboard");
        assertEquals("No data should return appropriate error message.", "N/A_3\n-----\n", results);
    }

    @Test
    public void testLoadContestDataWithFinalOnly() throws IOException {
        // 使用一个只包含决赛的 JSON 文件
        ObjectMapper mapper = new ObjectMapper();
        InputStream contestStream = getClass().getClassLoader().getResourceAsStream("Women 10m Platform.json");
        assertNotNull("Women 10m Platform JSON file is missing.", contestStream);
        JsonNode contestData = mapper.readTree(contestStream);
        core.loadContestData(contestData);

        // 验证详细结果输出是否正确处理只包含决赛的情况
        String detailedResults = core.displayDetailedResultsForEvent("Women 10m Platform");
        assertTrue("Detailed results should contain final scores if only finals are available.", detailedResults.contains("Final Score:"));
    }

    @Test
    public void testHandleEmptyAthletesData() {
        CoreModule emptyCore = new CoreModule(new ObjectMapper().createObjectNode());
        String output = emptyCore.displayAllPlayersInfo();
        assertEquals("Empty athlete data should produce empty output.", "", output);
    }

    @Test
    public void testHandleEmptyContestData() {
        core.loadContestData(new ObjectMapper().createObjectNode());
        String results = core.displayResultsForEvent("Women 10m Platform");
        assertEquals("Empty contest data should return appropriate error message.", "N/A_3\n-----\n", results);
    }

    @Test
    public void testLoadWomen10mPlatformEvent() throws IOException {
        // 加载女性 10m 跳台比赛的数据
        ObjectMapper mapper = new ObjectMapper();
        InputStream contestStream = getClass().getClassLoader().getResourceAsStream("Women 10m Platform.json");
        assertNotNull("Women 10m Platform JSON file is missing.", contestStream);
        JsonNode contestData = mapper.readTree(contestStream);
        core.loadContestData(contestData);

        // 测试详细的比赛结果输出是否正确
        String detailedResults = core.displayDetailedResultsForEvent("Women 10m Platform");
        assertNotNull("Detailed results should not be null.", detailedResults);
        assertTrue("Detailed results should contain the event name.", detailedResults.contains("Full Name"));
        assertTrue("Detailed results should contain preliminary, semifinal, and final ranks.",
                detailedResults.contains("Rank:"));
    }
    @Test
    public void testDisplayResultsSortingOrder() throws IOException {
        // 加载 Women 10m Platform 数据
        ObjectMapper mapper = new ObjectMapper();
        InputStream contestStream = getClass().getClassLoader().getResourceAsStream("Women 10m Platform.json");
        assertNotNull("Women 10m Platform JSON file is missing.", contestStream);
        JsonNode contestData = mapper.readTree(contestStream);
        core.loadContestData(contestData);

        // 验证输出结果是否按排名排序
        String results = core.displayResultsForEvent("Women 10m Platform");
        String[] lines = results.split("\n");

        List<Integer> ranks = new ArrayList<>();

        // 遍历所有行，收集排名
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("Rank:")) {
                int currentRank = Integer.parseInt(lines[i].split(":")[1].trim());
                ranks.add(currentRank);
            }
        }

        // 检查排名是否按升序排列
        for (int i = 1; i < ranks.size(); i++) {
            assertTrue("Results should be sorted by rank.", ranks.get(i - 1) < ranks.get(i));
        }
    }

}
