import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// 入口：两个参数走文件，--interactive 走标准输入输出，其余参数一律报用法并以 2 退出。
public final class DWASearch {

    private static final String INTERACTIVE_FLAG = "--interactive";

    private static final String USAGE_MESSAGE =
            "用法: java DWASearch <input.txt> <output.txt>\n"
                    + "      java DWASearch " + INTERACTIVE_FLAG + "    从标准输入读命令，结果打到控制台";

    private DWASearch() {
    }

    public static void main(String[] args) {
        int exitCode = run(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    // 参数决定走哪条路。不调 System.exit，方便测试直接断言返回值。
    static int run(String[] args) {
        try {
            if (args.length == 2) {
                return runWithFiles(Path.of(args[0]), Path.of(args[1]));
            }
            if (args.length == 1 && INTERACTIVE_FLAG.equals(args[0])) {
                return runWithStandardStreams();
            }
            System.err.println(USAGE_MESSAGE);
            return 2;
        } catch (IllegalStateException | UncheckedIOException e) {
            // 数据文件缺失、损坏这类问题在这里收口，只打原因
            System.err.println("数据有问题，查询无法继续: " + e.getMessage());
            return 1;
        }
    }

    // 输入文件读不到、输出文件写不了，都算 IO 失败，返回 1
    static int runWithFiles(Path inputPath, Path outputPath) {
        // 先确认数据读得出来，数据有问题时不去碰输出文件（避免把上一次的结果清空）
        DataStore.getCache();
        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(
                        Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8), true)) {
            new CommandProcessor(writer).run(reader);
            writer.flush();
            // PrintWriter 会把写失败吞掉，只能靠 checkError 发现
            if (writer.checkError()) {
                System.err.println("写入输出文件失败，输出可能不完整: " + outputPath.toAbsolutePath());
                return 1;
            }
            return 0;
        } catch (IOException e) {
            printIoError(e);
            return 1;
        }
    }

    // 交互模式：从键盘读，往控制台写
    static int runWithStandardStreams() {
        try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in, StandardCharsets.UTF_8));
                PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true)) {
            new CommandProcessor(writer).run(reader);
            return 0;
        } catch (IOException e) {
            printIoError(e);
            return 1;
        }
    }

    // 只打印一句原因，栈留给调试用
    private static void printIoError(IOException e) {
        System.err.println("读取输入或写入输出失败: " + e.getMessage());
    }
}
