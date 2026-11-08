// 核心业务模块 —— 整合 DataRepository、CommandParser、Formatter，
// 提供 processCommand(String line) 方法：解析单行命令并返回对应的格式化输出字符串；
// 以及 processFile(String inputPath, String outputPath) 方法：逐行读取输入文件、
// 依次处理每条命令、将结果连续写入输出文件（命令间无空行）。
// 这是整个程序的核心枢纽，所有业务逻辑集中于此，便于独立测试。
package com.dwa.core;

import com.dwa.model.Event;
import com.dwa.util.Formatter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CoreModule {
    private final DataRepository repository;

    public CoreModule(DataRepository repository) throws IOException {
        this.repository = repository;
    }

    /**
     * 处理单行命令，返回格式化输出字符串。
     * 非法命令返回 "Error\n-----\n"，未知项目返回 "N/A\n-----\n"。
     */
    public String processCommand(String line) {
        CommandParser.ParsedCommand cmd = CommandParser.parse(line);
        switch (cmd.getType()) {
            case PLAYERS:
                return Formatter.formatPlayers(repository.getAllPlayers());
            case RESULT:
                Event event = repository.findEventByCommand(cmd.getEventKey());
                if (event == null) return "N/A\n" + "-----\n";
                return Formatter.formatEventResult(event);
            case ERROR:
                return "Error\n" + "-----\n";
            case NA:
                return "N/A\n" + "-----\n";
            default:
                return "Error\n" + "-----\n";
        }
    }

    /**
     * 逐行读取输入文件，依次处理每条命令，将结果连续写入输出文件。
     * 命令之间不插入空行，输出编码统一 UTF-8，换行符 \n。
     */
    public void processFile(String inputPath, String outputPath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(inputPath), StandardCharsets.UTF_8);
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(outputPath), StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : lines) {
                writer.write(processCommand(line));
            }
        }
    }
}
