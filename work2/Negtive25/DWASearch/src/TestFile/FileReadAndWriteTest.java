package com.src.Lib;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class FileReadAndWriteTest {

    private static final String outputFile = "outputFile.txt"; // 输出文件路径

    @Test
    public void testWriteFileAndReadFile() throws IOException {
        // 测试正常读取文件的情况
        FileWriter writer = new FileWriter(outputFile);
        writer.write("Hello, World!\n");
        writer.close();

        String content = FileReadAndWrite.readFile(outputFile);
        assertEquals("Hello, World!\n", content); // 验证读取内容是否正确
    }

    @Test
    public void testReadFileButFileNotExist() throws IOException {
        // 测试文件不存在的情况
        String content = FileReadAndWrite.readFile("nonexistent.txt");
        assertEquals("", content); // 验证返回内容为空
    }

    @Test
    public void testWriteToFileNullData() throws IOException {
        // 测试写入的数据为null的情况
        FileReadAndWrite.writeToFile(null, outputFile);

        // 验证文件为空
        assertTrue(new File(outputFile).exists()); // 验证文件已经创建
    }
}
