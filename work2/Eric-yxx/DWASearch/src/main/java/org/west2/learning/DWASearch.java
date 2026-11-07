package org.west2.learning;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DWASearch {
    private DWASearch() {}

    public static void main(String[] args) {
        System.exit(run(args, System.err));
    }

    public static int run(String[] args, PrintStream errors) {
        return run(args, errors, Path.of("data.json"));
    }

    static int run(String[] args, PrintStream errors, Path dataPath) {
        if (args.length != 2) {
            errors.println("用法: java -jar DWASearch.jar <input.txt> <output.txt>");
            return 2;
        }
        Path input = Path.of(args[0]);
        Path output = Path.of(args[1]);
        try {
            List<String> commands = Files.readAllLines(input, StandardCharsets.UTF_8);
            ContestData data = DataLoader.read(dataPath);
            String text = new QueryEngine(data).execute(commands);
            Files.writeString(output, text, StandardCharsets.UTF_8);
            return 0;
        } catch (IOException | RuntimeException exception) {
            errors.println("查询失败: " + exception.getMessage());
            errors.println("涉及文件: 输入=" + input + "，数据=" + dataPath + "，输出=" + output);
            errors.println("如果写入中途失败，输出文件可能不完整。");
            return 1;
        }
    }
}
