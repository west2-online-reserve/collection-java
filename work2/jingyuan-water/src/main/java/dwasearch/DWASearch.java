package dwasearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 程序入口。
 *
 * <p>流程：校验参数 -> 加载 data.json -> 逐行读取指令 -> 写出结果文件。
 * <p>用法：{@code java -jar DWASearch.jar <指令文件> <输出文件>}
 */
public class DWASearch {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("用法: java -jar DWASearch.jar <指令文件> <输出文件>");
            System.err.println("示例: java -jar DWASearch.jar input.txt output.txt");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];

        CompetitionData data;
        try {
            data = DataLoader.load();
        } catch (Exception e) {
            System.err.println("数据文件 data.json 读取失败，程序无法继续：" + e.getMessage());
            System.exit(1);
            return;   // 告知编译器 data 已赋值：System.exit 不会返回，但编译器不知道
        }

        CommandRunner runner = new CommandRunner(data);

        Path in = Paths.get(inputPath);
        Path out = Paths.get(outputPath);

        try {
            process(in, out, runner);
        } catch (IOException e) {
            System.err.println("读写文件失败：输入=" + inputPath + "，输出=" + outputPath
                    + "，原因：" + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("程序出错：" + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * 读取输入文件，逐行交给 {@link CommandRunner} 处理，结果写入输出文件。
     *
     * <p>单独抽出：main 出错时调用 {@code System.exit} 会连测试的 JVM 一起终止；
     * 本方法只抛 {@link IOException}，可由测试直接调用。
     */
    public static void process(Path in, Path out, CommandRunner runner) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(in, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(runner.runLine(line));
            }
        }
    }
}
