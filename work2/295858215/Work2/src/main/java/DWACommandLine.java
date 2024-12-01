import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 命令行控制端类：DWACommandLine
 * 用于处理命令行输入并调用核心功能模块。
 */
public class DWACommandLine {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar DWASearch.jar <input.txt> <output.txt>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            // 使用 ObjectMapper 实例
            ObjectMapper mapper = new ObjectMapper();

            // 读取输入指令文件内容
            BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
            StringBuilder outputBuilder = new StringBuilder();

            // 加载选手信息的 JSON 文件
            InputStream athletesStream = DWACommandLine.class.getClassLoader().getResourceAsStream("athletes.json");
            if (athletesStream == null) {
                System.out.println("Error: Unable to find athletes.json resource file.");
                return;
            }
            JsonNode athletesData = mapper.readTree(athletesStream);

            // 初始化核心模块
            CoreModule core = new CoreModule(athletesData);

            // 逐行读取输入指令并输出相应的结果
            String command;
            while ((command = reader.readLine()) != null) {
                command = command.trim();
                String output;

                if ("players".equalsIgnoreCase(command)) {
                    // 输出所有选手信息
                    output = core.displayAllPlayersInfo();
                } else if (command.startsWith("result ")) {
                    // 动态加载对应比赛项目的 JSON 文件
                    String discipline = command.substring("result ".length()).trim();

                    // 先处理包含 "detail" 的情况
                    boolean isDetailed = false;
                    if (discipline.endsWith("detail")) {
                        isDetailed = true;
                        discipline = discipline.replace("detail", "").trim();
                    }

                    // 映射到对应的 JSON 文件名
                    String jsonFileName = mapDisciplineToJsonFile(discipline);

                    if (jsonFileName == null) {
                        output = "N/A_1\n-----\n"; // 指令中的比赛项目不匹配，返回 N/A_1
                    } else {
                        // 读取相应的比赛 JSON 文件
                        InputStream contestStream = DWACommandLine.class.getClassLoader().getResourceAsStream(jsonFileName);
                        if (contestStream == null) {
                            output = "N/A_2\n-----\n"; // 文件不存在，返回 N/A_2
                        } else {
                            JsonNode contestData = mapper.readTree(contestStream);
                            core.loadContestData(contestData);

                            // 根据是否包含 "detail" 输出不同的结果
                            if (isDetailed) {
                                output = core.displayDetailedResultsForEvent(discipline);
                            } else {
                                output = core.displayResultsForEvent(discipline);
                            }
                        }
                    }
                } else {
                    // 无法识别的指令
                    output = "Error\n-----\n";
                }

                // 将结果添加到输出带构建器中
                outputBuilder.append(output);
            }

            // 将所有结果写入输出文件
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(outputBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将比赛项目名称映射到相应的 JSON 文件
     *
     * @param discipline 比赛项目名称
     * @return 相应 JSON 文件名，如果无法匹配则返回 null
     */
    private static String mapDisciplineToJsonFile(String discipline) {
        switch (discipline.toLowerCase()) {
            case "women 1m springboard":
                return "Women 1m Springboard.json";
            case "women 3m springboard":
                return "Women 3m Springboard.json";
            case "women 10m platform":
                return "Women 10m Platform.json";
            case "women 3m synchronised":
                return "Women 3m Synchronised.json";
            case "women 10m synchronised":
                return "Women 10m Synchronised.json";
            case "men 1m springboard":
                return "Men 1m Springboard.json";
            case "men 3m springboard":
                return "Men 3m Springboard.json";
            case "men 10m platform":
                return "Men 10m Platform.json";
            case "men 3m synchronised":
                return "Men 3m Synchronised.json";
            case "men 10m synchronised":
                return "Men 10m Synchronised.json";
            default:
                return null;
        }
    }
}
