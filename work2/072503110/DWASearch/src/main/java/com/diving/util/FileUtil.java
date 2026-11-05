package com.diving.util;

import com.diving.exception.DataAccessException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {

    public static String readFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataAccessException("读取文件失败: " + filePath, e);
        }
    }

    /** 新增：按行读取文件内容 */
    public static List<String> readAllLines(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataAccessException("按行读取文件失败: " + filePath, e);
        }
    }

    public static void writeFile(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataAccessException("写入文件失败: " + filePath, e);
        }
    }

    public static boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}