package com.west2online.dwasearch;

import com.west2online.dwasearch.core.CommandProcessor;
import com.west2online.dwasearch.core.DataException;
import com.west2online.dwasearch.core.DataLoader;
import com.west2online.dwasearch.model.CompetitionData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 程序入口。
 *
 * 用法：java -jar DWASearch.jar <指令文件> <输出文件>
 *
 * 流程：读数据快照 -> 逐行解析指令 -> 把结果写入输出文件。
 * 文件 / 数据故障时，向标准错误输出说明，并以非零退出码结束。
 */
public class DWASearch {

    public static void main(String[] args) {
        // 1. 参数数量检查
        if (args.length != 2) {
            System.err.println("用法: java -jar DWASearch.jar <指令文件> <输出文件>");
            System.exit(1);
        }
        String inputPath = args[0];
        String outputPath = args[1];

        // 2. 读数据快照（失败则说明数据问题）
        CompetitionData data;
        try {
            data = new DataLoader().loadFromClasspath();
        } catch (DataException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        CommandProcessor processor = new CommandProcessor(data);

        // 3. 打开输入文件（失败则说清楚是哪个文件）
        BufferedReader reader;
        try {
            reader = openInput(inputPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        // 4. 打开输出文件（失败则说清楚是哪个文件，并关掉已打开的输入流）
        BufferedWriter writer;
        try {
            writer = openOutput(outputPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            try {
                reader.close();
            } catch (IOException ignored) {
                // 主问题已在上面报告，这里忽略关闭失败
            }
            System.exit(1);
            return;
        }

        // 5. 逐行处理（写入中途失败要提示输出可能不完整）
        try (reader; writer) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(processor.process(line));
            }
        } catch (IOException e) {
            System.err.println("写入失败，输出可能不完整: " + e.getMessage());
            System.exit(1);
        }
    }

    /** 打开输入文件，说明清楚是哪个文件出了问题。 */
    private static BufferedReader openInput(String path) throws IOException {
        Path p = Path.of(path);
        if (!Files.exists(p)) {
            throw new IOException("输入文件不存在: " + path);
        }
        if (!Files.isReadable(p)) {
            throw new IOException("输入文件无法读取: " + path);
        }
        return Files.newBufferedReader(p, StandardCharsets.UTF_8);
    }

    /** 打开输出文件，失败时说明是哪个文件。 */
    private static BufferedWriter openOutput(String path) throws IOException {
        try {
            return Files.newBufferedWriter(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("输出文件无法写入: " + path, e);
        }
    }
}
