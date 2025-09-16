import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

/**
 * 用于接受命令行参数，从 Core 模块调用合适的函数来处理
 *
 * @author LYanl7
 * @since 2025-9-15
 */
public class DWASearch {

    public static final String PLAYERS_STRING = "players";  // players 请求标志
    public static final String DETAIL_SUFFIX = " detail"; // detail 请求后缀
    public static final String RESULT_PREFIX = "result "; // Result 请求前缀

    public static final HashSet<String> VALID_GAMES = new HashSet<>() {{
        add("men 1m springboard");
        add("men 3m springboard");
        add("men 3m synchronised");
        add("men 10m platform");
        add("men 10m synchronised");
        add("women 1m springboard");
        add("women 3m springboard");
        add("women 3m synchronised");
        add("women 10m platform");
        add("women 10m synchronised");
    }};

    public static void main(String[] args) {
        try {
            run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void run(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("用法: java -jar DWASearch.jar <input.txt> <output.txt>");
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            // 逐行读取 input.txt
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals(PLAYERS_STRING)) {
                    /*
                      按格式输出 players 信息
                      Full Name:
                      Gender:
                      Country:
                      每输出一个选手信息，以5个'-'分隔
                      如:
                      Full Name:Ding Zhen
                      Gender:Male
                      Country:China
                      -----
                     */
                    Lib.processPlayers(outputFile);
                } else if (line.startsWith(RESULT_PREFIX)) {
                    String gameName = line.substring(RESULT_PREFIX.length()).trim();
                    /*
                       按格式输出该项目的详细信息
                          Full Name:
                          Rank:
                          Preliminary Score:
                          Semifinal Score:
                          Final Score:
                        例:
                          Full Name:Ding Zhen
                          Rank:1|1|4
                          Preliminary Score:1.0+1.0+1.0+1.0=4.0
                          Semifinal Score:1.0+1.0+1.0=3.0
                          Final Score:1.0+1.0+1.0+1.0+1.0+1.0+1.0=7.0
                     */
                    if (gameName.endsWith(DETAIL_SUFFIX)) {
                        gameName = gameName.substring(0, gameName.length() - DETAIL_SUFFIX.length());
                        Lib.processDetail(gameName, outputFile);
                    } else {
                        /*
                      按格式输出该项目的决赛信息
                        Full Name:
                        Rank:
                        Score:
                       例:
                        Full Name:Gu Ailing
                        Rank:1
                        Score:1.0+1.0+1.0+1.0+1.0+1.0+1.0=7.0
                     */
                        Lib.processGames(gameName, outputFile);
                    }
                } else {
                Lib.processError(outputFile);
                }
            }
        } catch (IOException e) {
            System.out.println("读取 input.txt 失败: " + e.getMessage());
            System.exit(1);
        }
    }


}
