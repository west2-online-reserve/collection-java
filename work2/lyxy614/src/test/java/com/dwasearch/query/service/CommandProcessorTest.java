package com.dwasearch.query.service;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.TestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {
    private static CoreModule coreModule;

    @BeforeAll
    //初始化合法数据
    static void initCoreModule() throws IOException{
        DataSnapshot dataSnapshot = TestData.loadDataSnapshot("data.json");
        coreModule = new CoreModule(dataSnapshot);
    }

    private String executeAndGetOutput(CoreModule coreModule, List<String> commands) throws IOException {
        StringWriter sw = new StringWriter();
        try (BufferedWriter bw = new BufferedWriter(sw)) {
            CommandProcessor processor = new CommandProcessor(coreModule, bw);
            processor.process(commands);
        }
        return sw.toString();
    }

    @Test
    @DisplayName("单行、多行正常的不同命令输出")
    void process_normal_multiLines() throws IOException {
        List<String> commandList = List.of(
                "players",
                "result women 3m springboard",
                "result men 10m platform",
                "result men 10m synchronised"
        );
        List<String> outputList = new ArrayList<>();

        //下面逐个检查每一条命令的输出是否正确
        //与标准数据样例比较
        String standardOutput1 = TestData.loadResourceAsString("standard_players.txt");
        String actualOutput1 = executeAndGetOutput(coreModule, List.of(commandList.getFirst()));
        assertEquals(standardOutput1, actualOutput1, "单条命令：第一条命令没有按照期望输出");
        outputList.add(actualOutput1);

        String standardOutput2 = TestData.loadResourceAsString("standard_Women's 3m Springboard.txt");
        String actualOutput2 = executeAndGetOutput(coreModule, List.of(commandList.get(1)));
        assertEquals(standardOutput2, actualOutput2, "单条命令：第二条命令没有按照期望输出");
        outputList.add(actualOutput2);

        String standardOutput3 = TestData.loadResourceAsString("standard_Men's 10m Platform.txt");
        String actualOutput3 = executeAndGetOutput(coreModule, List.of(commandList.get(2)));
        assertEquals(standardOutput3, actualOutput3, "单条命令：第三条命令没有按照期望输出");
        outputList.add(actualOutput3);

        String standardOutput4 = TestData.loadResourceAsString("standard_Men's 10m Synchronised.txt");
        String actualOutput4 = executeAndGetOutput(coreModule, List.of(commandList.get(3)));
        assertEquals(standardOutput4, actualOutput4, "单条命令：第四条命令没有按照期望输出");
        outputList.add(actualOutput4);

        assertEquals(commandList.size(), outputList.size(), "多条合法命令：存在命令输出缺失");

        //合并上述逐条命令输出的结果，并将合并后的内容与多条命令一起执行后输出的结果比较
        String total = String.join("", outputList);

        String actualTotal = executeAndGetOutput(coreModule, commandList);
        assertEquals(total, actualTotal, "多条命令一起输出的结果与单独输出后再拼接的不一致");

        //检查相邻输出结果是否有"-----Full Name:..."的情况
        assertFalse(actualTotal.contains("-----Full Name:"), "相邻输出结果存在串行情况");
        assertFalse(actualTotal.contains("-----Error"));
        assertFalse(actualTotal.contains("-----N/A"));
        assertTrue(actualTotal.endsWith("-----\n") || actualTotal.endsWith("-----\r\n"));
    }

    @Test
    @DisplayName("一条非法命令后接一条合法命令")
    void process_illegal_link_legal() throws IOException {
        List<String> commandList = List.of(
                "player",
                "players",
                "result sss",
                "result women 3m springboard",
                "resultmen 10m platform"
        );
        List<String> outputList = new ArrayList<>();

        String standardOutput1 = "Error\n-----\n";
        String actualOutput1 = executeAndGetOutput(coreModule, List.of(commandList.getFirst()));
        assertEquals(standardOutput1, actualOutput1, "第一条非法命令（未知名令）没有按照期望输出");
        outputList.add(actualOutput1);

        //合法命令不再做断言，因为已经测试过单条合法命令的输出
        String actualOutput2 = executeAndGetOutput(coreModule, List.of(commandList.get(1)));
        outputList.add(actualOutput2);

        String standardOutput3 = "N/A\n-----\n";
        String actualOutput3 = executeAndGetOutput(coreModule, List.of(commandList.get(2)));
        assertEquals(standardOutput3, actualOutput3, "第二条非法命令（项目名称缺失错误）没有按照期望输出");
        outputList.add(actualOutput3);

        //合法命令不再做断言，因为已经测试过单条合法命令的输出
        String actualOutput4 = executeAndGetOutput(coreModule, List.of(commandList.get(3)));
        outputList.add(actualOutput4);

        String standardOutput5 = "Error\n-----\n";
        String actualOutput5 = executeAndGetOutput(coreModule, List.of(commandList.get(4)));
        assertEquals(standardOutput5, actualOutput5);
        outputList.add(actualOutput5);

        assertEquals(commandList.size(), outputList.size(), "一条非法接一条合法：存在命令输出缺失");
        String total = String.join("", outputList);
        String actualTotal = executeAndGetOutput(coreModule, commandList);
        assertEquals(total, actualTotal, "多条命令一起输出的结果与单独输出后再拼接的不一致");
    }

    @Test
    @DisplayName("重复命令")
    void process_repeated_commands() throws IOException {
        List<String> commandList = List.of(
                "players",
                "players",
                "result men 10m platform",
                "result men 10m platform",
                "result sss",
                "result sss",
                "resultmen 10m platform",
                "resultmen 10m platform"
        );

        String standardRepeatedOutput = TestData.loadResourceAsString("standard_repeatedOutput.txt");
        String actualRepeatedOutput = executeAndGetOutput(coreModule, commandList);
        assertEquals(standardRepeatedOutput, actualRepeatedOutput);
    }

    @Test
    @DisplayName("空行输出约定：忽略空行")
    void process_ignore_blank() throws IOException{
        List<String> commandList = List.of(
                "player",
                "",
                " ",
                "player"
        );

        String actualOutput2 = executeAndGetOutput(coreModule, List.of(commandList.get(1)));
        assertEquals("", actualOutput2);

        String actualOutput3 = executeAndGetOutput(coreModule, List.of(commandList.get(2)));
        assertEquals("", actualOutput3);

        String expectedOutput = """
                Error
                -----
                Error
                -----
                """;
        String actualOutput = executeAndGetOutput(coreModule, commandList);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("多余空格约定")
    //players后可加任意数量空格，但不能有其他额外字符
    //result与项目之间可多空格，项目名称内部和之后均不能含有多余空格
    void process_blanks() throws IOException{
        List<String> commandList = List.of(
                "players  ",
                "players extra",
                "result  men 10m platform",
                "result men  10m platform",
                "result men 10m platform "
        );

        String actualOutput = executeAndGetOutput(coreModule, commandList);
        String expectedOutput = TestData.loadResourceAsString("standard_players.txt")
                + "Error\n-----\n"
                + TestData.loadResourceAsString("standard_Men's 10m Platform.txt")
                + "N/A\n-----\n" + "N/A\n-----\n";
        assertEquals(expectedOutput, actualOutput);
    }
}