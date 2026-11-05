package com.dwasearch;

import com.dwasearch.command.CommandProcessor;
import com.dwasearch.data.DataException;
import com.dwasearch.data.DataRepository;
import com.dwasearch.model.CompetitionData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DWASearch {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("用法: java -jar DWASearch.jar <input.txt> <output.txt>");
            System.exit(2);
            return;
        }

        Path dataPath = Path.of("data.json");
        Path inputPath = Path.of(args[0]);
        Path outputPath = Path.of(args[1]);

        try {
            run(dataPath, inputPath, outputPath);
        } catch (IOException | DataException e) {
            System.err.println("错误: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void run(Path dataPath, Path inputPath, Path outputPath)
            throws IOException, DataException {
        CompetitionData data = new DataRepository().load(dataPath);

        List<String> lines;
        try {
            lines = Files.readAllLines(inputPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("无法读取输入文件: " + inputPath, e);
        }

        CommandProcessor processor = new CommandProcessor();
        StringBuilder output = new StringBuilder();

        for (String line : lines) {
            output.append(processor.process(line, data));
        }

        try {
            Files.writeString(outputPath, output.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("无法写入输出文件，输出可能不完整: " + outputPath, e);
        }
    }
}