package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.*;
import com.dwasearch.query.TestData;
import com.dwasearch.query.util.DWAData;
import com.dwasearch.query.util.OutputFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {
    CoreModule coreModule;
    private static final List<String> disciplineNameList = List.of(
            "Women's 3m Springboard",   //单人项目
            "Men's 10m Synchronised",   //双人项目
            "Mixed 3m & 10m Team"       //多人项目
    );

    //outputPlayers和outputResults均按照输出格式、数据一致性、输出顺序的顺序来测试
    @Test
    @DisplayName("读取到完整数据应按照规定的格式输出到文本文件")
    //核对全部players输出的格式
    void outputPlayers_normal_format() throws IOException {
        //读取测试资源中的数据快照副本
        DataSnapshot dataSnapshot = TestData.loadDataSnapshot("data.json");
        coreModule = new CoreModule(dataSnapshot);
        //输出到临时文件再读取输出
        Path testPath = Files.createTempFile("formatOutput_players", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(testPath, StandardCharsets.UTF_8)) {
            coreModule.outputPlayers(writer);
        }

        List<String> lines = Files.readAllLines(testPath, StandardCharsets.UTF_8);
        assertFalse(lines.isEmpty());

        //逐行检查格式
        for (int i = 0; i < lines.size(); i += 4) {
            assertTrue(lines.get(i).startsWith("Full Name:"),
                    "第 " + (i + 1) + " 行应以 Full Name: 开头，实际: " + lines.get(i));
            assertTrue(lines.get(i + 1).startsWith("Gender:"),
                    "第 " + (i + 2) + " 行应以 Gender: 开头，实际: " + lines.get(i + 1));
            assertTrue(lines.get(i + 2).startsWith("Country:"),
                    "第 " + (i + 3) + " 行应以 Country: 开头，实际: " + lines.get(i + 2));
            assertEquals("-----", lines.get(i + 3),
                    "第 " + (i + 4) + " 行应为分隔线，实际: " + lines.get(i + 3));
        }

        // 4. 最后一行是分隔线
        assertEquals("-----", lines.getLast(),
                "最后一行应为分隔线");

        // 5. 字段名后面有内容，没有多余空格（样例要求冒号后紧跟值）
        String firstFullName = lines.get(0);
        assertTrue(firstFullName.length() > "Full Name:".length(),
                "Full Name 字段不应为空");
        assertFalse(firstFullName.contains(": "),
                "冒号后不应有空格");

        Files.deleteIfExists(testPath);
    }

    @Test
    @DisplayName("读取数据快照后应输出与数据快照一致的数据")
    //抽样核对数据是否与快照一致
    void outputPlayers_same_dataSnapshot() throws IOException {
        //读取原本的数据快照
        DataSnapshot standardDataSnapshot = new DWAData().readData(Paths.get("data.json"));
        coreModule = new CoreModule(standardDataSnapshot);
        //输出到临时文件再读取输出
        Path testPath = Files.createTempFile("sameOutput", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(testPath, StandardCharsets.UTF_8)) {
            coreModule.outputPlayers(writer);
        }
        String content = Files.readString(testPath, StandardCharsets.UTF_8);
        assertFalse(content.isEmpty());

        //抽样比对
        Player sample1 = standardDataSnapshot.getCountries().get(0).getPlayers().get(1);
        Player sample2 = standardDataSnapshot.getCountries().get(1).getPlayers().get(2);
        Player sample3 = standardDataSnapshot.getCountries().get(2).getPlayers().get(3);

        assertTrue(content.contains(OutputFormatter.formatPlayer(sample1)));
        assertTrue(content.contains(OutputFormatter.formatPlayer(sample2)));
        assertTrue(content.contains(OutputFormatter.formatPlayer(sample3)));

        Files.deleteIfExists(testPath);
    }

    @Test
    @DisplayName("读取到乱序数据应按照规定的顺序输出到文本文件")
    void outputPlayers_right_order() throws IOException {
        //读取测试资源中的数据快照副本
        DataSnapshot dataSnapshot = TestData.loadDataSnapshot("data.json");
        coreModule = new CoreModule(dataSnapshot);
        //读取测试资源中期望顺序的players输出
        String standard_players = TestData.loadResourceAsString("standard_players.txt");

        //打乱国家顺序
        List<Country> countries = dataSnapshot.getCountries();
        Collections.shuffle(countries);

        //输出到临时文件再读取输出
        Path testPath = Files.createTempFile("rightOrder", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(testPath, StandardCharsets.UTF_8)) {
            coreModule.outputPlayers(writer);
        }
        String content = Files.readString(testPath, StandardCharsets.UTF_8);
        assertFalse(content.isEmpty());

        assertEquals(content, standard_players);
    }

    //各抽一个单人项目、双人项目和多人项目来测试
    @Test
    @DisplayName("读取到完整数据应按照规定的格式输出到文本文件")
    void outputResults_normal_format() throws IOException {
        DataSnapshot dataSnapshot = TestData.loadDataSnapshot("data.json");
        coreModule = new CoreModule(dataSnapshot);

        //用StringWriter来测试
        for (String disciplineName : disciplineNameList) {
            StringWriter sw = new StringWriter();
            try (BufferedWriter writer = new BufferedWriter(sw)) {
                coreModule.outputResults(disciplineName, writer);
            }
            String content = sw.toString();
            assertFalse(content.isEmpty());
            List<String> contentList = content.lines().toList();

            for (int i = 0; i < contentList.size(); i += 4) {
                assertTrue(contentList.get(i).startsWith("Full Name:"));
                assertTrue(contentList.get(i + 1).startsWith("Rank:"));
                assertTrue(contentList.get(i + 2).startsWith("Score:"));
                assertTrue(contentList.get(i + 2).contains(" = ") && contentList.get(i + 2).contains(" + "));
                assertEquals("-----", contentList.get(i + 3));
            }
        }
    }

    @Test
    @DisplayName("读取数据快照后应输出与数据快照一致的数据")
    void outputResults_same_dataSnapshot() throws IOException {
        //读取原本的数据快照
        DataSnapshot dataSnapshot = new DWAData().readData(Paths.get("data.json"));
        coreModule = new CoreModule(dataSnapshot);

        for (String disciplineName : disciplineNameList) {
            StringWriter sw = new StringWriter();
            try (BufferedWriter writer = new BufferedWriter(sw)) {
                coreModule.outputResults(disciplineName, writer);
            }
            String content = sw.toString();
            assertFalse(content.isEmpty());

            String standard_results = TestData.loadResourceAsString("standard_" + disciplineName + ".txt");
            assertEquals(standard_results, content);
        }
    }

    //不测试outputResults的升序输出，因为数据快照已经明确按照Rank升序排好序了

    //简单测试一下错误信息的连续输出
    @Test
    @DisplayName("错误信息应按照规定格式输出")
    void outputWrongMessages_format() throws IOException {
        coreModule = new CoreModule(new DataSnapshot());

        StringWriter sw = new StringWriter();
        try (BufferedWriter writer = new BufferedWriter(sw)) {
            coreModule.outputWrongMessages("Error", writer);
            coreModule.outputWrongMessages("Error", writer);
            coreModule.outputWrongMessages("N/A", writer);
        }
        String content = sw.toString();
        assertFalse(content.isEmpty());
        List<String> contentList = content.lines().toList();

        assertEquals("Error", contentList.getFirst());
        assertEquals("-----", contentList.get(1));
        assertEquals("Error", contentList.get(2));
        assertEquals("-----", contentList.get(3));
        assertEquals("N/A", contentList.get(4));
        assertEquals("-----", contentList.get(5));
    }
}