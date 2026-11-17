package com.dwa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorExitAndDataTest {

    @TempDir
    Path tempDir;

    private final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
    private final String classpath = System.getProperty("java.class.path");

    private final File realJson = new File("data.json");
    private Path backupJson;

    
    @BeforeEach
    public void setUp() throws Exception {
        if (realJson.exists()) {
            backupJson = tempDir.resolve("data_backup.json");
            Files.copy(realJson.toPath(), backupJson);
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (backupJson != null && Files.exists(backupJson)) {
            Files.copy(backupJson, realJson.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private int runMainProcess(String... args) throws Exception {
        List<String> command = new ArrayList<>();
        command.add(javaBin);
        command.add("-cp");
        command.add(classpath);
        command.add("com.dwa.Main");
        for (String arg : args) command.add(arg);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(new File(".")); 
        pb.redirectErrorStream(true); 
        return pb.start().waitFor();
    }

    // 测试1. 程序入口错误：非零退出码
    @Test
    public void testMissingArgs_ExitCode() throws Exception {
        int code = runMainProcess("input.txt"); // 少一个参数
        assertNotEquals(0, code, "参数缺失应返回非零退出码");
    }

    //测试2. 输入文件错误：非零退出码
    @Test
    public void testInputFileNotExist_ExitCode() throws Exception {
        int code = runMainProcess("not_exist.txt", tempDir.resolve("out.txt").toString());
        assertNotEquals(0, code, "输入文件不存在应返回非零退出码");
    }

    //测试3.路径不可写：非零退出码
    @Test
    public void testUnwritableOutput_ExitCode() throws Exception {
        int code = runMainProcess("input.txt", "Z:\\invalid\\out.txt");
        assertNotEquals(0, code, "路径不可写应返回非零退出码");
    }

    //测试4. JSON损坏：非零退出码
    @Test
    public void testCorruptedJson_ExitCode() throws Exception {
        assertNotNull(backupJson, "本地缺少 data.json，无法测试");
        
        Files.writeString(realJson.toPath(), "{ damaged json ");
        
        int code = runMainProcess("input.txt", tempDir.resolve("out.txt").toString());
        assertNotEquals(0, code, "JSON损坏时，应明确失败返回非零退出码");
    }
    //测试5. JSON缺少必要字段：非零退出码
    @Test
    public void testMissingJsonFields_ExitCode() throws Exception {
        assertNotNull(backupJson, "本地缺少 data.json，无法测试");
        Files.writeString(realJson.toPath(), "{\"some_other_field\": []}");
        
        int code = runMainProcess("input.txt", tempDir.resolve("out.txt").toString());
        assertNotEquals(0, code, "JSON缺少必要字段时，应明确失败返回非零退出码");
    }
}