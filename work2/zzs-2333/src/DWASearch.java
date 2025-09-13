import java.io.*;

/**
 * DWASearch 主程序类，负责从输入文件中读取命令并通过 Core 类处理，
 * 然后将结果输出到指定的文件。
 */
public class DWASearch {

    public static void main(String[] args) {
        // 检查是否提供了输入文件参数
        if (args.length < 1) {
            System.out.println("Usage: java DWASearch <input_file>");
            return;
        }

        String inputFileName = args[0];  // 输入文件名
        String outputFileName = "output.txt";  // 输出文件名

        // 通过 try-with-resources 语句自动关闭资源
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            // 逐行读取输入文件中的命令，并处理
            String line;
            while ((line = reader.readLine()) != null) {
                // 处理每一行命令，去掉前后空白字符并调用 Core 的 processCommand
                Core.processCommand(line.trim(), writer);
            }

            // 命令处理完成后输出提示信息
            System.out.println("Processing completed. Output written to " + outputFileName);

        } catch (IOException e) {
            // 捕获和处理文件操作中的异常
            System.err.println("Error occurred while processing the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

