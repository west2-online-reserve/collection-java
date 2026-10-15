import core.CoreModule;
import utils.Utility;

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
                System.err.println("错误: 输入文件不存在或无法读取: " + args[0]);
                return 1;
            }

            // 3. 输出文件校验（目录不存在 / 不可写时在此失败）
            try {
                Utility.setOutput(args[1]);
            } catch (IOException e) {
                System.err.println("错误: 输出文件不可写: " + args[1]);
                return 1;
            }

            // 4. 逐行处理命令
            CoreModule coreModule = new CoreModule();
            String line;
            while ((line = Utility.readLine()) != null) {
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
            return 0;
        } catch (IOException | RuntimeException e) {
            // 数据损坏、缺少字段等“明确失败” 返回非零退出码
            System.err.println("处理失败: " + e.getMessage());
            return 1;
        } finally {
            try {
                Utility.close();
            } catch (IOException ignored) {
            }
        }
    }
}
