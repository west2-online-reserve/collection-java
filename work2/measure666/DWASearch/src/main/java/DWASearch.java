import core.CoreModule;
import utils.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASearch {
    public static final Pattern REGEX_PLAYERS = Pattern.compile("^players$");
    public static final Pattern REGEX_CONTEST = Pattern.compile("^result (men 3m springboard|men 3m synchronised|men 10m platform|men 10m synchronised|women 3m springboard|women 3m synchronised|women 10m platform|women 10m synchronised)$");
    public static final Pattern REGEX_RESULT = Pattern.compile("^result( |$)");

    public static void main(String[] args) {
        System.exit(run(args));
    }

    public static int run(String[] args) {
        return run(args, path -> {
            Utility.setOutput(path);
            return Utility.bw;
        });
    }

    @FunctionalInterface
    interface OutputFactory {
        BufferedWriter open(String path) throws IOException;
    }

    // 测试可传入会发生写入或关闭故障的输出流，与命令行入口共用处理逻辑。
    static int run(String[] args, OutputFactory outputFactory) {
        // 1. 参数数量校验
        if (args.length != 2) {
            System.err.println("用法: java -jar DWASearch.jar <输入文件> <输出文件>");
            return 1;
        }

        try {
            // 2. 输入文件校验
            try {
                Utility.setInput(args[0]);
            } catch (FileNotFoundException e) {
                System.err.println("错误: 输入文件不存在或无法读取: " + args[0] + ": " + e.getMessage());
                return 1;
            }

            try (BufferedReader reader = Utility.br) {
                // 3. 输出文件校验（失败时也会关闭已经打开的输入流）
                BufferedWriter output;
                try {
                    output = outputFactory.open(args[1]);
                } catch (IOException e) {
                    System.err.println("错误: 输出文件不可写: " + args[1] + ": " + e.getMessage());
                    return 1;
                }

                try (BufferedWriter writer = output) {
                    Utility.bw = writer;
                    // 4. 逐行处理命令
                    CoreModule coreModule = new CoreModule();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Matcher playerMatcher = REGEX_PLAYERS.matcher(line);
                        Matcher resultMatcher = REGEX_RESULT.matcher(line);
                        Matcher contestMatcher = REGEX_CONTEST.matcher(line);

                        if (playerMatcher.find()) {
                            coreModule.displayAllPlayersInfo();
                            continue;
                        }
                        if (resultMatcher.find()) {
                            if (contestMatcher.find()) {
                                coreModule.displayResults(contestMatcher.group(1));
                            } else {
                                Utility.write("N/A\n" + "-----");
                            }
                        } else {
                            Utility.write("Error\n" + "-----");
                        }
                    }
                }
            }
            // 处理和关闭资源都成功后，才返回成功。
            return 0;
        } catch (IOException | RuntimeException e) {
            System.err.println("处理失败，输出可能不完整: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("原因: " + e.getCause().getMessage());
            }
            for (Throwable closeFailure : e.getSuppressed()) {
                System.err.println("关闭资源失败: " + closeFailure.getMessage());
            }
            return 1;
        } finally {
            Utility.br = null;
            Utility.bw = null;
        }
    }
}
