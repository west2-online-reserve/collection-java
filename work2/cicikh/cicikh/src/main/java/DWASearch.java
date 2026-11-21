import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DWASearch {
    private static final List<String> VALID_EVENTS = Arrays.asList(
            "women 1m springboard",    // 女子1米跳板
            "women 3m springboard",    // 女子3米跳板
            "women 10m platform",      // 女子10米跳台
            "women 3m synchronised",   // 女子3米双人跳板
            "women 10m synchronised",  // 女子10米双人跳台
            "men 1m springboard",      // 男子1米跳板
            "men 3m springboard",      // 男子3米跳板
            "men 10m platform",        // 男子10米跳台
            "men 3m synchronised",     // 男子3米双人跳板
            "men 10m synchronised"      // 男子10米双人跳台
    );

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DWASearch input.txt output.txt");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        // 验证输入文件
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.err.println("错误：输入文件不存在: " + inputPath);
            return;
        }

        // 确保输出目录存在
        File outputFile = new File(outputPath);
        File outputDir = outputFile.getParentFile();
        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
        }

        System.out.println("正在初始化核心模块并加载数据...");
        CoreModule coreModule = new CoreModule();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            // FileWriter仅用于初始化文件，实际写入由各命令处理方法完成

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                processCommand(line, coreModule, outputPath);
            }

            System.out.println("处理完成！结果已保存到: " + outputPath);
        } catch (IOException e) {
            System.err.println("文件操作异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processCommand(String command, CoreModule coreModule, String outputPath) {
        String[] parts = command.split("\\s+");

        if (parts[0].equals("players")) {
            // players命令：输出所有选手信息
            if (parts.length != 1) {
                // players命令不应该有额外参数
                writeError(outputPath);
            } else {
                coreModule.displayAllPlayersInfo(outputPath);
            }
        } else if (parts[0].equals("result")) {
            processResultCommand(parts, coreModule, outputPath);
        } else {
            writeError(outputPath);
        }
    }

    private static void processResultCommand(String[] parts, CoreModule coreModule, String outputPath) {
        // 检查命令格式：result命令至少需要包含比赛项目名称
        if (parts.length < 2) {
            writeError(outputPath);
            return;
        }

        String eventName;
        boolean hasDetail = false;

        // 简化处理逻辑
        if (parts.length == 2) {
            // 格式：result eventName
            eventName = parts[1];
        } else if (parts.length == 3 && "detail".equals(parts[2])) {
            // 格式：result eventName detail
            eventName = parts[1];
            hasDetail = true;
        } else if (parts.length >= 3) {
            // 处理带空格的比赛名称，如 "women 10m platform"
            // 重构比赛名称：parts[1]到倒数第二个（或倒数第二个之前如果是detail）
            StringBuilder eventNameBuilder = new StringBuilder();
            int endIndex = parts.length;

            // 检查最后一个是否是detail
            if ("detail".equals(parts[parts.length - 1])) {
                hasDetail = true;
                endIndex = parts.length - 1;
            }

            // 拼接比赛名称
            for (int i = 1; i < endIndex; i++) {
                if (i > 1) {
                    eventNameBuilder.append(" ");
                }
                eventNameBuilder.append(parts[i]);
            }
            eventName = eventNameBuilder.toString();
        } else {
            writeError(outputPath);
            return;
        }

        // 转换eventName格式：空格转连字符
        if (eventName.contains(" ")) {
            eventName = eventName.replaceAll("\\s+", "-").toLowerCase();
        }

        // 调用CoreModule执行查询操作
        boolean success;
        if (hasDetail) {
            success = coreModule.displayDetailedResults(eventName, outputPath);
        } else {
            success = coreModule.displayFinalResults(eventName, outputPath);
        }

        // 处理查询失败情况
        if (!success) {
            writeNA(outputPath);
        }
    }

    private static void writeError(String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath, true)) {
            writer.write("Error\n");
            writer.write("-----\n");
        } catch (IOException e) {
            System.err.println("写入错误信息失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeNA(String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath, true)) {
            writer.write("N/A\n");
            writer.write("-----\n");
        } catch (IOException e) {
            System.err.println("写入N/A信息失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}