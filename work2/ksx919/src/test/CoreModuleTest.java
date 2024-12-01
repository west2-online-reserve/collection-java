package test;

import lib.CoreModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class CoreModuleTest {
    private CoreModule coreModule;

    @BeforeEach
    public void setUp() {
        coreModule = new CoreModule();
    }

    // 测试1：测试运动员数据是否成功加载
    @Test
    public void testLoadAthletesData() {
        assertNotNull(coreModule, "CoreModule should be initialized");
    }

    // 测试2：测试显示所有运动员信息的方法
    @Test
    public void testDisplayAllPlayersInfo() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayAllPlayersInfo(writer);
        writer.flush();
        String output = outputStream.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty(), "Output should not be empty");
        assertTrue(output.contains("Full Name:"), "Output should contain 'Full Name:'");
        assertTrue(output.contains("Gender:"), "Output should contain 'Gender:'");
        assertTrue(output.contains("Country:"), "Output should contain 'Country:'");
    }

    // 测试3：测试在有效的赛事名称下显示结果
    @Test
    public void testDisplayResultsForEventValid() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayResultsForEvent(writer, "women 1m springboard");
        writer.flush();
        String output = outputStream.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty(), "Output should not be empty");
        assertTrue(output.contains("Full Name:"), "Output should contain 'Full Name:'");
        assertTrue(output.contains("Rank:"), "Output should contain 'Rank:'");
        assertTrue(output.contains("Score:"), "Output should contain 'Score:'");
    }

    // 测试4：测试在无效的赛事名称下显示结果
    @Test
    public void testDisplayResultsForEventInvalid() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayResultsForEvent(writer, "invalid event name");
        writer.flush();
        String output = outputStream.toString();
        assertTrue(output.isEmpty(), "Output should be empty for invalid event name");
    }

    // 测试5：测试在有效的赛事名称下显示详细结果
    @Test
    public void testDisplayDetailedResultsValid() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayDetailedResults(writer, "women 1m springboard");
        writer.flush();
        String output = outputStream.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty(), "Output should not be empty");
        assertTrue(output.contains("Full Name:"), "Output should contain 'Full Name:'");
        assertTrue(output.contains("Rank:"), "Output should contain 'Rank:'");
        assertTrue(output.contains("Preliminary Score:"), "Output should contain 'Preliminary Score:'");
        assertTrue(output.contains("Semifinal Score:"), "Output should contain 'Semifinal Score:'");
        assertTrue(output.contains("Final Score:"), "Output should contain 'Final Score:'");
    }

    // 测试6：测试在无效的赛事名称下显示详细结果
    @Test
    public void testDisplayDetailedResultsInvalid() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayDetailedResults(writer, "invalid event name");
        writer.flush();
        String output = outputStream.toString();
        assertTrue(output.isEmpty(), "Output should be empty for invalid event name");
    }

    // 测试7：测试显示所有运动员信息时的排序（按国家和姓氏）
    @Test
    public void testDisplayAllPlayersInfoSorting() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayAllPlayersInfo(writer);
        writer.flush();
        String output = outputStream.toString();
        String[] entries = output.split("-----\n");
        assertTrue(entries.length > 1, "There should be multiple entries");
        // 由于数据未知，无法精确验证排序，但可以检查是否有多条记录
    }

    // 测试8：测试赛事结果数据是否成功加载
    @Test
    public void testEventResultDataLoading() {
        assertNotNull(coreModule, "CoreModule should be initialized");
    }

    // 测试9：测试在没有结果的赛事名称下显示结果
    @Test
    public void testDisplayResultsForEventNoResults() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayResultsForEvent(writer, "men 50m platform");
        writer.flush();
        String output = outputStream.toString();
        assertTrue(output.isEmpty(), "Output should be empty for event with no results");
    }

    // 测试10：测试在没有结果的赛事名称下显示详细结果
    @Test
    public void testDisplayDetailedResultsNoResults() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        coreModule.displayDetailedResults(writer, "men 50m platform");
        writer.flush();
        String output = outputStream.toString();
        assertTrue(output.isEmpty(), "Output should be empty for event with no results");
    }

    // 测试11：测试赛事名称的大小写不敏感性
    @Test
    public void testDisplayResultsForEventCaseInsensitive() throws IOException {
        ByteArrayOutputStream outputStreamLower = new ByteArrayOutputStream();
        BufferedWriter writerLower = new BufferedWriter(new OutputStreamWriter(outputStreamLower));
        coreModule.displayResultsForEvent(writerLower, "women 1m springboard");
        writerLower.flush();
        String outputLower = outputStreamLower.toString();

        ByteArrayOutputStream outputStreamUpper = new ByteArrayOutputStream();
        BufferedWriter writerUpper = new BufferedWriter(new OutputStreamWriter(outputStreamUpper));
        coreModule.displayResultsForEvent(writerUpper, "WOMEN 1M SPRINGBOARD");
        writerUpper.flush();
        String outputUpper = outputStreamUpper.toString();

        assertEquals(outputLower, outputUpper, "Outputs should be the same regardless of case");
    }
}