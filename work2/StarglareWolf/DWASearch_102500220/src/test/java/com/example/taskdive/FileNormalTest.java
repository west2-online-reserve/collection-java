package com.example.taskdive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileNormalTest {
    @Test
    public void FileProcessTest(@TempDir Path tempDir)throws Exception {
        // 建立临时文件
        Path input = tempDir.resolve("input.txt");
        Path output = tempDir.resolve("output.txt");

        Files.writeString(input, "players\nwomen3mspringboard\nmen3msprboar\nwomen10msynchronise\nw\n");

        DWASearch.main(new String[]{input.toString(), output.toString()});

        String result = Files.readString(output);
        // 命令验证
        assertTrue(result.contains("Full Name: "), "没有打印名字");
        assertTrue(result.contains("------------DisciplineName: "),"没有打印赛事");
        assertTrue(result.contains("Rank: "),"没有打印排名");
        assertTrue(result.contains("Did you mean -- "));
        assertTrue(result.contains("DivePoints: "));
        assertTrue(result.contains("###########"));
        assertTrue(result.contains("Phase: "));
        // 赛事不存在测试
        assertTrue(result.contains("Error"));
    }
}