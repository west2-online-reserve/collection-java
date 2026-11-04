package com.dwasearch.query;

import com.dwasearch.model.DataSnapshot;
import com.dwasearch.query.exception.InvalidDataException;
import com.dwasearch.query.service.CommandProcessor;
import com.dwasearch.query.service.CoreModule;
import com.dwasearch.query.util.DWAData;
import com.dwasearch.query.util.SnapshotValidator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DWASearch {
    // 主入口只负责接参数和退出
    public static void main(String[] args) {
        final Path dataPath = Paths.get("data.json");
        System.exit(new DWASearch().execute(args, dataPath));
    }

    // 核心执行逻辑，方便测试
    public int execute(String[] args, Path dataPath) {

        // 1.检查参数
        try {
            checkArgs(args);
        } catch (IllegalArgumentException e) {
            System.err.println("命令行参数错误：" + e.getMessage());
            return 1;
        }

        // 2.读取数据
        DataSnapshot dataSnapshot;
        try {
            dataSnapshot = new DWAData().readData(dataPath);
        } catch (IOException e) {
            System.err.println("无法读取数据快照文件：" + e.getMessage());
            return 2;
        }

        // 3.校验数据
        SnapshotValidator snapshotValidator = new SnapshotValidator();
        try {
            snapshotValidator.validate(dataSnapshot);
        } catch (InvalidDataException e) {
            System.err.println("data.json数据不完整：" + e.getMessage());
            return 2;
        }

        // 4.读取 input.txt
        List<String> input;
        try {
            input = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("无法读取输入文件：" + e.getMessage());
            return 1;
        }

        // 5.写出到 output.txt
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(args[1]), StandardCharsets.UTF_8)) {
            CommandProcessor commandProcessor = new CommandProcessor(new CoreModule(dataSnapshot), bw);
            commandProcessor.process(input);
        } catch (IOException e) {
            System.err.println(args[1] + " 不可写，输出可能不完整：" + e.getMessage());
            return 1;
        }

        return 0;
    }

    void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("命令行参数数量错误，应为2");
        }
    }
}
