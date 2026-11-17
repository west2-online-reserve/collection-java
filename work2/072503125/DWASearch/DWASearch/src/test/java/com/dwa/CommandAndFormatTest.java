package com.dwa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandAndFormatTest {

    @TempDir
    Path tempDir;

    private final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    private final String classpath = System.getProperty("java.class.path");

    private int runMainProcess(String inputPath, String outputPath) throws Exception {
        List<String> command = new ArrayList<>();
        command.add(javaBin);
        command.add("-cp");
        command.add(classpath);
        command.add("com.dwa.Main");
        command.add(inputPath);
        command.add(outputPath);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(".")); 
        pb.redirectErrorStream(true); 
        Process process = pb.start();
        return process.waitFor();
    }
//测试：命令行参数测试及边界情况
        @Test
    public void testComplexInputBoundaries() throws Exception {
        Path input = tempDir.resolve("in.txt");
        Path output = tempDir.resolve("out.txt");

        String content = "  players  \n" +                    // 1. 前后多余空格
                         "\n" +                               // 2. 空行
                         "PLAYERS\n" +                        // 3. 大小写错误 (应输出 Error)
                         "unknown_command\n" +                // 4. 未知命令 (应输出 Error)
                         "result unknown_event\n" +           // 5. 非法项目名 (应输出 N/A)
                         "result women 3m springboard\n" +    // 6. 合法命令
                         "result women 3m springboard\n";     // 7. 重复命令

        Files.writeString(input, content);

        int exitCode = runMainProcess(input.toString(), output.toString());
        
        assertEquals(0, exitCode, "正常执行流程退出码应为0");

        String outContent = Files.readString(output);
        assertTrue(outContent.contains("Error"), "未知指令/大小写错误应输出 Error");
        assertTrue(outContent.contains("N/A"), "非法项目名应输出 N/A");
        assertTrue(outContent.contains("-----"), "缺失分隔线");
    }
}