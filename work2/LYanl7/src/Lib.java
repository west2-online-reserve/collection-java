import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * 负责处理 input.txt 中的各种请求
 *
 * @author LYanl7
 * @since 2025-9-15
 */
public class Lib {


    public static final String PLAYERS_JSON_LOCATION_FOR_INPUT_STREAM = "data/Players.json"; // Players.json 文件位置
    public static final String FINALS_PHASE_NAME = "Finals"; // 决赛阶段名称
    public static final String PRELIMINARY_PHASE_NAME = "Preliminaries"; // 初赛阶段名称
    public static final String SEMIFINAL_PHASE_NAME = "Semifinals"; // 半决赛阶段名称



    /**
     * 处理 players 请求
     *
     * @param outputFile 输出文件名
     */
    public static void processPlayers(String outputFile) {
        // 先将 Json 读入并解析成对象
        InputStream inputStream = Lib.class.getClassLoader().getResourceAsStream(PLAYERS_JSON_LOCATION_FOR_INPUT_STREAM);
         if (inputStream == null) {
             System.out.println("未找到 Players.json 文件");
             return;
         }
        List<Player> players = parsePlayers(inputStream);
        // 拼接写入的数据
        String content = formatPlayers(players);
        // 写入 outputFile
        writeToFile(outputFile, content);
    }


    /**
     * 将 PlayersPlayers.json 解析成 Player 列表
     * @param inputStream Players.json 的输入流
     * @return  Player 列表
     */
    static List<Player> parsePlayers(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper();
        List<Player> players;
        try {
            players = mapper.readValue(
                    inputStream,
                    new TypeReference<>() {
                    }
            );
        } catch (Exception e) {
            System.out.println("读取 players 数据失败: " + e.getMessage());
            return List.of();
        }
        return players;
    }

    /**
     * 将 Player 列表格式化成指定字符串
     * @param players Player 列表
     * @return 格式化后的字符串
     */
    static String formatPlayers(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append("Full Name:").append(player.getFullName()).append("\n");
            sb.append("Gender:").append(player.getGender()).append("\n");
            sb.append("Country:").append(player.getCountry()).append("\n");
            sb.append("-----").append("\n");
        }
        return sb.toString();
    }

    /**
     * 将内容写入指定文件
     * @param outputFile 输出文件名
     * @param content 内容
     */
    static void writeToFile(String outputFile, String content) {
        try (FileWriter writer = new FileWriter(outputFile, true)) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.write(content);
        } catch (Exception e) {
            System.out.println("写入数据失败: " + e.getMessage());
        }
    }


    /**
     * 处理某个项目的决赛请求
     *
     * @param line 项目名
     * @param outputFile 输出文件名
     */
    public static void processGames(String line, String outputFile) {
        if (!check(line, outputFile)) {
            return;
        }
        String filename = "data/" + line + ".json"; // 比赛数据文件位置
        InputStream inputStream = Lib.class.getClassLoader().getResourceAsStream(filename);
        // 读取比赛数据，反序列化为 Result 对象
        List<Result> results = parseResults(inputStream);
        // 过滤出决赛阶段的数据
        filterFinalResults(results);
        // 拼接写入的数据
        String content = formatResults(results);
        // 写入 outputFile
        writeToFile(outputFile, content);
    }

    /**
     * 检查比赛名是否合法
     * @param line 比赛名
     */
    private static boolean check(String line, String outputFile) {
        if (DWASearch.VALID_GAMES.contains(line)) {
            return true;
        }
        String errorMsg = """
                    N/A
                    -----
                    """;
        writeToFile(outputFile, errorMsg);
        return false;
    }

    /**
     * 将比赛数据解析成 Result 列表
     *
     * @param inputStream 比赛数据的输入流
     * @return Result 列表
     */
    static List<Result> parseResults(InputStream inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Result> results;
        try {
            results = objectMapper.readValue(
                    inputStream,
                    new TypeReference<>() {
                    }
            );
        } catch (Exception e) {
            System.out.println("读取比赛数据失败: " + e.getMessage());
            return List.of();
        }
        return results;
    }

    /**
     * 过滤出决赛阶段的数据
     * @param results 比赛结果列表
     */
    static void filterFinalResults(List<Result> results) {
        results.removeIf(result -> !result.getPhaseName().equals(FINALS_PHASE_NAME));
    }


    /**
     * 将 Result 列表格式化成指定字符串
     * @param results Result 列表
     * @return 格式化后的字符串
     */
    static String formatResults(List<Result> results) {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append("Full Name:").append(result.getFullName()).append("\n");
            sb.append("Rank:").append(result.getRank()).append("\n");
            String scoreStr = String.join(" + ", result.getScores()) + " = " + result.getTotalScore();
            sb.append("Score:").append(scoreStr).append("\n");
            sb.append("-----").append("\n");
        }
        return sb.toString();
    }



    /**
     * 处理某个项目的所有比赛详情请求
     *
     * @param gameName 项目名
     * @param outputFile 输出文件名
     */
    public static void processDetail(String gameName, String outputFile) {
        if (!check(gameName, outputFile)) {
            return;
        }
        String filename = "data/" + gameName + ".json"; // 比赛数据文件位置
        InputStream inputStream = Lib.class.getClassLoader().getResourceAsStream(filename);
        // 读取比赛数据，反序列化为 Result 对象
        List<Result> results = parseResults(inputStream);
        List<ResultDetail> resultDetails = new ArrayList<>();
        // 填充 ResultDetail 列表
        fillDetail(resultDetails, results);
        // 按选手的第一场比赛的排名排序
        sortDetail(resultDetails);
        // 格式化输出内容
        String content = formatDetail(resultDetails);
        // 写入 outputFile
        writeToFile(outputFile, content);
    }

    /**
     * 按选手的第一场比赛的排名排序
     * @param resultDetails 结果详情列表
     */
    static void sortDetail(List<ResultDetail> resultDetails) {
        resultDetails.sort(Comparator.comparing(
                detail -> {
                    String rankStr;
                    if (detail.getPreliminaryRank() != null && !detail.getPreliminaryRank().isBlank()) {
                        rankStr = detail.getPreliminaryRank();
                    } else if (detail.getSemifinalRank() != null && !detail.getSemifinalRank().isBlank()) {
                        rankStr = detail.getSemifinalRank();
                    } else if (detail.getFinalRank() != null && !detail.getFinalRank().isBlank()) {
                        rankStr = detail.getFinalRank();
                    } else {
                        return Integer.MAX_VALUE;
                    }
                    return Integer.parseInt(rankStr);
                }
        ));
    }

    /**
     * 格式化输出内容
     * @param resultDetails 结果详情列表
     * @return 格式化后的字符串
     */
    static String formatDetail(List<ResultDetail> resultDetails) {
        StringBuilder sb = new StringBuilder();
        for (ResultDetail resultDetail : resultDetails) {
            sb.append("Full Name:").append(resultDetail.getFullName()).append("\n");
            String rankStr = String.format("%s | %s | %s",
                    safeString(resultDetail.getPreliminaryRank()),
                    safeString(resultDetail.getSemifinalRank()),
                    safeString(resultDetail.getFinalRank())
            );
            sb.append("Rank:").append(rankStr).append("\n");
            sb.append("Preliminary Score:").append(safeString(resultDetail.getPreliminaryScore())).append("\n");
            sb.append("Semifinal Score:").append(safeString(resultDetail.getSemifinalScore())).append("\n");
            sb.append("Final Score:").append(safeString(resultDetail.getFinalScore())).append("\n");
            sb.append("-----").append("\n");
        }
        return sb.toString();
    }

    private static String safeString(String s) {
        if (s == null || s.isBlank()) {
            return "*";
        }
        return s;
    }

    /**
     * 填充 ResultDetail 列表
     * @param resultDetails 结果详情列表
     * @param results 比赛结果列表
     */
    static void fillDetail(List<ResultDetail> resultDetails, List<Result> results) {
        Map<String, Integer> idToIndex = new HashMap<>();

        for (Result result : results) {
            String personId = result.getPersonId();
            if (!idToIndex.containsKey(personId)) {
                // 新选手， 创建新的 ResultDetail 并加入列表
                idToIndex.put(personId, resultDetails.size());
                ResultDetail resultDetail = new ResultDetail();
                resultDetail.setFullName(result.getFullName());
                String phaseName = result.getPhaseName();
                resultDetails.add(resultDetail);
                putPhaseRank(result, resultDetail, phaseName);
            } else {
                // 已存在的选手， 更新信息即可
                int index = idToIndex.get(personId);
                ResultDetail resultDetail = resultDetails.get(index);
                String phaseName = result.getPhaseName();
                putPhaseRank(result, resultDetail, phaseName);
            }
        }

    }

    /**
     * 根据比赛阶段名称，更新 ResultDetail 中对应的排名和分数
     * @param result 比赛结果
     * @param resultDetail 结果详情
     * @param phaseName 比赛阶段名称
     */
    private static void putPhaseRank(Result result, ResultDetail resultDetail, String phaseName) {
        switch (phaseName) {
            case PRELIMINARY_PHASE_NAME -> {
                resultDetail.setPreliminaryScore(
                        String.join(" + ", result.getScores()) + " = " + result.getTotalScore()
                );
                resultDetail.setPreliminaryRank(String.valueOf(result.getRank()));
        }
            case SEMIFINAL_PHASE_NAME -> {
                resultDetail.setSemifinalScore(
                        String.join(" + ", result.getScores()) + " = " + result.getTotalScore()
                );
                resultDetail.setSemifinalRank(String.valueOf(result.getRank()));
            }
            case FINALS_PHASE_NAME -> {
                resultDetail.setFinalScore(
                        String.join(" + ", result.getScores()) + " = " + result.getTotalScore()
                );
                resultDetail.setFinalRank(String.valueOf(result.getRank()));
            }
        }
    }

    /**
     * 处理错误请求
     * @param outputFile 输出文件名
     */
    public static void processError(String outputFile) {
        String content = """
                Error
                -----
                """;
        writeToFile(outputFile, content);
    }
}
