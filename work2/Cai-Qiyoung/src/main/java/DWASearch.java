import java.io.*;
import java.nio.charset.StandardCharsets;

public class DWASearch {
    public static void main(String[] args) {
        // 初始化核心工具类
        CoreModule coreModule = new CoreModule();

        // 校验命令行参数
        if (args.length != 2) {
            System.err.println("用法：java -jar DWASearch.jar <输入文件路径> <输出文件路径>");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];

        // 声明流变量（try外部声明，便于finally关闭）
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            // 创建输入流
            FileInputStream input = new FileInputStream(inputPath);
            reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

            // 创建输出流
            FileOutputStream output = new FileOutputStream(outputPath);
            writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));

            // 1. 检查输入文件是否为空（读取第一行前判断是否有内容）
            String firstLine = reader.readLine();
            if (firstLine == null) {
                // 文件为空，输出Error
                writer.write("Error\n-----\n");
                return; // 直接结束，无需继续处理
            }

            // 处理第一行命令（因为已经读取了firstLine）
            processCommand(firstLine, coreModule, writer);

            // 2. 处理剩余命令
            String command;
            while ((command = reader.readLine()) != null) {
                processCommand(command, coreModule, writer);
            }

        } catch (IOException e) {
            // 处理文件操作异常（如文件不存在、权限问题等）
            System.err.println("文件操作错误：" + e.getMessage());
        } finally {
            // 确保流资源关闭
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 提取命令处理逻辑为工具方法
    private static void processCommand(String command, CoreModule coreModule, BufferedWriter writer) throws IOException {
        command = command.trim(); // 去除首尾空格
        if (command.isEmpty()) {
            // 空行也视为无效命令，输出Error
            writer.write("Error\n-----\n");
            return;
        }
        //分割命令
        String[] parts = command.split(" ");

        //处理命令
        if(!(parts[0].equals("players") || parts[0].equals("result"))) {
            // 其他未识别命令，输出Error
            writer.write("Error\n-----\n");
            return;
        }

        switch (parts[0]) {
            case "players":
                if(parts.length == 1) {
                    String playerInfo = coreModule.playerInfo(); // 获取选手信息
                    writer.write(playerInfo); // 写入输出文件
                } else {
                    // 其他未识别命令，输出Error
                    writer.write("Error\n-----\n");
                }
                break;
            case "result":
                if(parts.length == 5) {
                    if(!(parts[parts.length - 1].equals("detail"))) {
                        writer.write("N/A\n-----\n");
                        break;
                    }
                    //取出中间项目名称,去掉首尾的空格
                    String projectName = command.substring(6 , command.length()-6).trim();
                    //匹配对应的数据文件
                    String dataFileName = mapCommandToData(projectName);
                    if(dataFileName == null) {
                        writer.write("N/A\n-----\n");
                        break;
                    } else {
                        String detailedResults = coreModule.detailedResults(dataFileName);
                        writer.write(detailedResults);
                    }
                } else if (parts.length == 4) {
                    //取出项目名称,去掉首尾的空格
                    String projectName = command.substring(6 , command.length()).trim();
                    //匹配对应的数据文件
                    String dataFileName = mapCommandToData(projectName);
                    if(dataFileName == null) {
                        writer.write("N/A\n-----\n");
                        break;
                    } else {
                        String finalResults = coreModule.finalResults(dataFileName);
                        writer.write(finalResults);
                    }
                } else {
                    writer.write("N/A\n-----\n");
                }
        }

    }

    //将command对应到相应的Json文件名
    private static String mapCommandToData(String discipline) {
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