package org.uzk20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.uzk20.core.CoreModule;
import org.uzk20.utils.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DWASearchTest {

    // 测试正常处理指令的场景
    @Test
    void testMain_Success(@TempDir Path tempDir) throws IOException {
        // 1. 准备临时输入文件和输出文件
        Path inputFile = tempDir.resolve("input.txt");
        Path outputFile = tempDir.resolve("output.txt");
        Files.writeString(inputFile, "players\nresults\n"); // 写入测试指令

        // 2. 构造main方法的参数（输入文件和输出文件路径）
        String[] args = {inputFile.toString(), outputFile.toString()};

        // 3. 执行main方法
        DWASearch.main(args);

        // 4. 验证输出文件是否正确生成
        assertTrue(Files.exists(outputFile), "输出文件未生成");
        String content = Files.readString(outputFile);
        assertFalse(content.isEmpty(), "输出文件内容为空");
    }

    // 测试参数不足的场景
    @Test
    void testMain_InsufficientArgs() {
        // 1. 构造参数不足的情况
        String[] args = {"input.txt"};

        // 2. 捕获System.err的输出
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errStream));

        // 3. 执行main方法（无需拦截System.exit，改用更简单的方式）
        DWASearch.main(args);

        // 4. 验证错误信息
        String errMsg = errStream.toString();
        assertTrue(errMsg.contains("请传入输入文件和输出文件路径"), "错误提示不正确");
    }
    // 测试文件读取失败的场景（如输入文件不存在）
    @Test
    void testMain_FileNotFound() {
        // 1. 传入不存在的输入文件
        String[] args = {"nonexistent.txt", "output.txt"};

        // 2. 捕获System.out的输出
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        // 3. 执行main方法
        DWASearch.main(args);

        // 4. 验证错误信息
        String outMsg = outStream.toString();
        assertTrue(outMsg.contains("失败"), "未捕获文件读取错误");
    }

    //此处参考了102500336的验证方式，通过关键词数量检索一口气验证多个测试用例
    @Test
    public void testMain1() throws IOException{
        String[] args = new String[2];
        args[0] = "src/test/resources/input.txt";
        args[1] = "src/test/resources/output.txt";
        DWASearch.main(args);

        // 读取输出文件内容
        String testContent = readFileToString(args[1]);

        // 验证输出内容
        assertEquals(3, countStringOccurrences(testContent, "Error"));
        assertEquals(6, countStringOccurrences(testContent, "N/A"));
        assertEquals(86, countStringOccurrences(testContent, "Country:"));
        assertTrue(countStringOccurrences(testContent, "Preliminary Score:") >= 14);
        assertTrue(testContent.contains("Semifinal Score:*"));
    }

    private String readFileToString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    private int countStringOccurrences(String text, String target) {
        if (text.isEmpty() || target.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }

    // 自定义SecurityManager用于捕获System.exit
    private class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkExit(int status) {
            throw new ExitException(status);
        }
    }

    private static class ExitException extends RuntimeException {
        public ExitException(int status) {
            super(String.valueOf(status));
        }
    }
}