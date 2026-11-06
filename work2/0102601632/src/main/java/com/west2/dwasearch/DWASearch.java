package com.west2.dwasearch;

import com.west2.dwasearch.data.DataStore;
import com.west2.dwasearch.io.DataException;
import com.west2.dwasearch.io.DataReader;
import com.west2.dwasearch.io.OutputWriter;
import com.west2.dwasearch.query.CommandProcessor;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DWASearch {
    private static final String USAGE = "用法：java -jar DWASearch.jar <指令文件> <输出文件>";

    public static void main(String[] args) {
        int exitCode = run(args, System.err);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    public static int run(String[] args, PrintStream err) {
        if (args.length != 2) {
            err.println(USAGE);
            return 1;
        }

        Path inputFile = Paths.get(args[0]);
        Path outputFile = Paths.get(args[1]);

        if (!Files.exists(inputFile)) {
            err.println("输入文件不存在：" + inputFile.toAbsolutePath());
            return 1;
        }
        if (!Files.isRegularFile(inputFile)) {
            err.println("输入文件不是普通文件：" + inputFile.toAbsolutePath());
            return 1;
        }
        if (!Files.isReadable(inputFile)) {
            err.println("输入文件不可读：" + inputFile.toAbsolutePath());
            return 1;
        }

        List<String> commands;
        try {
            commands = Files.readAllLines(inputFile, StandardCharsets.UTF_8);
        } catch (MalformedInputException e) {
            err.println("输入文件不是 UTF-8 编码：" + inputFile.toAbsolutePath());
            return 1;
        } catch (IOException e) {
            err.println("读取输入文件失败：" + inputFile.toAbsolutePath() + "（" + e.getMessage() + "）");
            return 1;
        }

        DataStore dataStore;
        try {
            dataStore = new DataReader().loadFromClasspath();
        } catch (DataException e) {
            err.println("数据加载失败：" + e.getMessage());
            return 1;
        }

        List<String> lines = new CommandProcessor(dataStore).process(commands);

        try {
            OutputWriter.write(outputFile, lines);
        } catch (IOException e) {
            err.println("写入输出文件失败：" + outputFile.toAbsolutePath() + "（" + e.getMessage() + "）");
            err.println("输出文件可能不完整。");
            return 1;
        }
        return 0;
    }
}
