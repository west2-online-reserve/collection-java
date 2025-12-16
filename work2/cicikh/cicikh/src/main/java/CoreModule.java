import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CoreModule {

    // 存放所有选手的信息
    private List<Player> allPlayers;

    // 存放所有比赛信息
    private Map<String, Event> allEvents;

    public CoreModule() {
        allPlayers = new ArrayList<>();
        allEvents = new HashMap<>();
        fetchAllData();
    }

    // 获取所有需要的数据
    private void fetchAllData() {
        loadMockData();
    }

    private void loadMockData() {

        // 加载模拟选手数据

        allPlayers.add(new Player("HART Alexander", "Men", "USA"));   // 美国男子选手
        allPlayers.add(new Player("LOTFI Dariush", "Men", "IRI"));   // 伊朗男子选手
        allPlayers.add(new Player("DICK Elaena", "Women", "USA"));   // 美国女子选手
        allPlayers.add(new Player("MULLER Jette", "Women", "GER"));  // 德国女子选手
        allPlayers.add(new Player("ROLLINSON Amy", "Women", "CAN")); // 加拿大女子选手
        allPlayers.add(new Player("SANTIAGO Dominique", "Women", "PUR")); // 波多黎各女子选手
        allPlayers.add(new Player("FUNG Katelyn", "Women", "CAN"));  // 加拿大女子选手
        allPlayers.add(new Player("MYALIN Igor", "Men", "RUS"));     // 俄罗斯男子选手

        // 加载模拟比赛项目和结果数据

        // 1. 男子10米跳台比赛结果
        Event men10m = new Event("men-10m");
        List<Result> men10mResults = new ArrayList<>();
        men10mResults.add(new Result("HART Alexander", "1", Arrays.asList(95.0, 85.0, 90.0, 88.0, 92.0), 450.0));
        men10mResults.add(new Result("LOTFI Dariush", "2", Arrays.asList(88.0, 82.0, 86.0, 84.0, 87.0), 427.0));
        men10mResults.add(new Result("MYALIN Igor", "3", Arrays.asList(85.0, 80.0, 83.0, 81.0, 84.0), 413.0));
        men10m.setFinalResults(men10mResults);
        allEvents.put("men-10m", men10m);

        // 2. 女子10米跳台比赛结果
        Event women10m = new Event("women-10m");
        List<Result> women10mResults = new ArrayList<>();
        women10mResults.add(new Result("MULLER Jette", "1", Arrays.asList(85.0, 82.0, 88.0, 86.0, 89.0), 430.0));
        women10mResults.add(new Result("ROLLINSON Amy", "2", Arrays.asList(83.0, 80.0, 85.0, 82.0, 86.0), 416.0));
        women10mResults.add(new Result("SANTIAGO Dominique", "3", Arrays.asList(80.0, 78.0, 82.0, 81.0, 83.0), 404.0));
        women10mResults.add(new Result("FUNG Katelyn", "4", Arrays.asList(78.0, 75.0, 80.0, 79.0, 81.0), 393.0));
        women10mResults.add(new Result("DICK Elaena", "5", Arrays.asList(76.0, 73.0, 78.0, 77.0, 79.0), 383.0));
        women10m.setFinalResults(women10mResults);
        allEvents.put("women-10m", women10m);
    }

    // 发送HTTP GET请求并获取响应内容
    private String sendHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)){
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    }

    // 解析选手信息HTML页面
    private void parseAthletesHtml(String html) {
        int startIndex = 0;
        while ((startIndex = html.indexOf("class=\"athlete-item\"", startIndex)) != -1) {
            // 解析选手全名
            int nameStart = html.indexOf("data-full-name=\"", startIndex) + 16;
            int nameEnd = html.indexOf("\"", nameStart);
            String fullName = html.substring(nameStart, nameEnd);

            // 解析性别
            int genderStart = html.indexOf("data-gender=\"", startIndex) + 13;
            int genderEnd = html.indexOf("\"", genderStart);
            String gender = html.substring(genderStart, genderEnd);

            // 解析国籍
            int countryStart = html.indexOf("data-country=\"", startIndex) + 15;
            int countryEnd = html.indexOf("\"", countryStart);
            String country = html.substring(countryStart, countryEnd);

            // 创建选手对象并添加到列表
            Player player = new Player(fullName, gender, country);
            allPlayers.add(player);

            // 更新起始索引，继续查找下一个选手信息
            startIndex = nameEnd;
        }

        Collections.sort(allPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                // 1. 首先按国籍升序排序
                int countryCompare = p1.getCountry().compareTo(p2.getCountry());
                if (countryCompare != 0) {
                    return countryCompare;
                }

                // 2. 若国籍相同，则按姓氏升序排序
                String[] name1 = p1.getFullName().split(" ");
                String[] name2 = p2.getFullName().split(" ");
                String lastName1 = name1[name1.length - 1];
                String lastName2 = name1[name2.length - 1];
                return  lastName1.compareTo(lastName2);
            }
        });
    }

    // 解析比赛项目的HTML结果页面
    private Event parseEventResultsHtml(String eventName, String html) {
        Event event = new Event(eventName);

        // 解析决赛结果
        List<Result> finalResults = parseResultsSection(html, "final");
        event.setFinalResults(finalResults);

        // 解析半决赛结果
        List<Result> semifinalResults = parseResultsSection(html, "semifinal");
        event.setSemifinalResults(semifinalResults);

        // 解析初赛结果
        List<Result> preliminaryResults = parseResultsSection(html, "preliminary");
        event.setPreliminaryResults(preliminaryResults);

        return event;
    }

    private List<Result> parseResultsSection(String html, String stage) {
        List<Result> results = new ArrayList<>();
        // 根据stage参数查找对应的HTML部分，然后解析结果
        return results;
    }

    // 将所有选手信息输出到指定文件
    public void displayAllPlayersInfo(String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath, true)){
            // 创建副本，避免修改原始数据
            List<Player> sortedPlayers = new ArrayList<>(allPlayers);

            sortedPlayers.sort((p1, p2) -> {
                // 1. 首先按国籍升序排序
                int countryCompare = p1.getCountry().compareTo(p2.getCountry());
                if (countryCompare != 0) {
                    return countryCompare;
                }
                // 2. 若国籍相同，则按姓氏升序排序
                String[] nameParts1 = p1.getFullName().split(" ");
                String[] nameParts2 = p2.getFullName().split(" ");
                String lastName1 = nameParts1[nameParts1.length - 1];
                String lastName2 = nameParts2[nameParts2.length - 1];
                return lastName1.compareTo(lastName2);
            });

            // 遍历排序后的选手列表，按格式写入文件
            for (Player player : sortedPlayers) {
                writer.write("Full Name:" + player.getFullName() + "\n");
                writer.write("Gender:" + player.getGender() + "\n");
                writer.write("Country:" + player.getCountry() + "\n");
                writer.write("-----\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将指定比赛项目的决赛结果输出到文件
    public boolean displayFinalResults(String eventName, String outputPath) {
        Event event = allEvents.get(eventName);
        if (event == null) {
            return false;
        }

        List<Result> finalResults = event.getFinalResults();
        if (finalResults == null || finalResults.isEmpty()) {
            return false;
        }
        try (FileWriter writer = new FileWriter(outputPath, true)){
            for (Result result : finalResults) {
                writer.write("Full Name:" + result.getPlayerName() + "\n");
                writer.write("Rank:" + result.getRank() + "\n");
                writer.write("Score:" + result.getScoreString() + "\n");
                writer.write("-----\n");
            }
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 将指定比赛项目的详细结果（包括所有阶段）输出到文件
    public boolean displayDetailedResults(String eventName, String outputPath) {
        Event event = allEvents.get(eventName);
        if (event == null) {
            return false;
        }

        Map<String, CombinedResult> combinedResultsMap = new HashMap<>();

        List<Result> preliminaryResults = event.getPreliminaryResults();
        if (preliminaryResults != null) {
            for (Result result : preliminaryResults) {
                CombinedResult combined = new CombinedResult(result.getPlayerName());
                combined.setPreliminaryResult(result);
                combinedResultsMap.put(result.getPlayerName(), combined);
            }
        }

        // 处理半决赛结果
        List<Result> semifinalResults = event.getSemifinalResults();
        if (semifinalResults != null) {
            for (Result result : semifinalResults) {
                // 使用getOrDefault确保即使没有初赛结果也能正确创建对象
                CombinedResult combined = combinedResultsMap.getOrDefault(result.getPlayerName(),
                        new CombinedResult(result.getPlayerName()));
                combined.setSemifinalResult(result);
                combinedResultsMap.put(result.getPlayerName(), combined);
            }
        }

        // 处理决赛结果
        List<Result> finalResults = event.getFinalResults();
        if (finalResults != null) {
            for (Result result : finalResults) {
                // 使用getOrDefault确保即使没有前面阶段的结果也能正确创建对象
                CombinedResult combined = combinedResultsMap.getOrDefault(result.getPlayerName(),
                        new CombinedResult(result.getPlayerName()));
                combined.setFinalResult(result);
                combinedResultsMap.put(result.getPlayerName(), combined);
            }
        }

        // 3. 转换为列表并按照第一次比赛的排名排序
        List<CombinedResult> combinedResults = new ArrayList<>(combinedResultsMap.values());
        Collections.sort(combinedResults, new Comparator<CombinedResult>() {
            @Override
            public int compare(CombinedResult c1, CombinedResult c2) {
                // 【关键】按照第一次比赛的排名排序
                // 优先级：初赛 -> 半决赛 -> 决赛
                if (c1.getPreliminaryResult() != null && c2.getPreliminaryResult() != null) {
                    // 初赛排名比较
                    return Integer.parseInt(c1.getPreliminaryResult().getRank()) -
                            Integer.parseInt(c2.getPreliminaryResult().getRank());
                } else if (c1.getSemifinalResult() != null && c2.getSemifinalResult() != null) {
                    // 半决赛排名比较
                    return Integer.parseInt(c1.getSemifinalResult().getRank()) -
                            Integer.parseInt(c2.getSemifinalResult().getRank());
                } else if (c1.getFinalResult() != null && c2.getFinalResult() != null) {
                    // 决赛排名比较
                    return Integer.parseInt(c1.getFinalResult().getRank()) -
                            Integer.parseInt(c2.getFinalResult().getRank());
                }
                return 0;  // 其他情况默认不排序
            }
        });

        // 4. 输出详细结果到文件
        try (FileWriter writer = new FileWriter(outputPath, true)) {
            for (CombinedResult combined : combinedResults) {
                writer.write("Full Name:" + combined.getPlayerName() + "\n");
                writer.write("Rank:" + combined.getRankString() + "\n");
                writer.write("Preliminary Score:" + combined.getPreliminaryScoreString() + "\n");
                writer.write("Semifinal Score:" + combined.getSemifinalScoreString() + "\n");
                writer.write("Final Score:" + combined.getFinalScoreString() + "\n");
                writer.write("-----\n");
            }
            return true;  // 输出成功
        } catch (IOException e) {
            // 异常处理：打印异常信息到控制台
            e.printStackTrace();
            return false;  // 输出失败
        }
    }

    // Player 类 —— 表示选手信息的内部数据模型
    private static class Player {
        // 封装选手的基本信息：姓名，性别，国籍
        private String fullName;
        private String gender;
        private String country;

        public Player(String fullName, String gender, String country) {
            this.fullName = fullName;
            this.gender = gender;
            this.country = country;
        }

        public String getFullName() {
            return fullName;
        }

        public String getGender() {
            return gender;
        }

        public String getCountry() {
            return country;
        }
    }

    // Event类 —— 比赛项目（包含多个阶段结果）
    private static class Event {
        private String name;
        private List<Result> preliminaryResults;
        private List<Result> semifinalResults;
        private List<Result> finalResults;

        public Event(String name) { this.name = name; }

        public String getName() { return name; }

        public List<Result> getPreliminaryResults() { return preliminaryResults; }

        public List<Result> getSemifinalResults() { return semifinalResults; }

        public List<Result> getFinalResults() { return finalResults; }

        // 设置决赛结果
        public void setFinalResults(List<Result> finalResults) { this.finalResults = finalResults; }

        public void setPreliminaryResults(List<Result> preliminaryResults) { this.preliminaryResults = preliminaryResults; }

        public void setSemifinalResults(List<Result> semifinalResults) { this.semifinalResults = semifinalResults; }
    }

    // Result类 —— 表示单阶段比赛结果
    private static class Result {
        // 封装选手在某个比赛阶段的成绩信息（包括排名，各伦分数，总分）
        private String playerName;
        private String rank;
        private List<Double> scores;
        private double totalScore;

        public Result(String playerName, String rank, List<Double> scores, double totalScore) {
            this.playerName = playerName;
            this.rank = rank;
            this.scores = scores;
            this.totalScore = totalScore;
        }

        // Getter方法
        public String getPlayerName() { return playerName; }

        public String getRank() { return rank;}

        public List<Double> getScores() { return scores; }

        public double getTotalScore() { return totalScore; }

        // 格式化分数字符串
        public String getScoreString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < scores.size(); i++) {
                if (i > 0) {
                    sb.append(" + "); // 为非第一个字符添加分隔符
                }
                sb.append(String.format("%.2f", scores.get(i)));
            }
            sb.append(" = ").append(String.format("%.2f", totalScore));
            return sb.toString();
        }
    }

    // CombineResult类 —— 所有比赛阶段综合结果
    private static class CombinedResult {
        private String playerName;
        private Result preliminaryResult;
        private Result semifinalResult;
        private Result finalResult;

        public CombinedResult(String name) { this.playerName = name; }

        public String getPlayerName() { return playerName; }

        public Result getPreliminaryResult() { return preliminaryResult; }

        public void setPreliminaryResult(Result preliminaryResult) { this.preliminaryResult = preliminaryResult; }

        public Result getSemifinalResult() { return semifinalResult; }

        public void setSemifinalResult(Result semifinalResult) { this.semifinalResult = semifinalResult; }

        public Result getFinalResult() { return finalResult; }

        public void setFinalResult(Result finalResult) { this.finalResult = finalResult; }

        public String getRankString() {
            String preliminaryRank = preliminaryResult != null ? preliminaryResult.getRank() : "*";
            String semifinalRank = semifinalResult != null ? semifinalResult.getRank() : "*";
            String finalRank = finalResult != null ? finalResult.getRank() : "*";
            return preliminaryRank + " | " + semifinalRank + " | " + finalRank;
        }

        // 获取初赛分数字符串，不存在的成绩用"*"表示
        public String getPreliminaryScoreString() {
            return preliminaryResult != null ? preliminaryResult.getScoreString() : "*";
        }

        // 获取半决赛分数字符串，不存在的成绩用"*"表示
        public String getSemifinalScoreString() {
            return semifinalResult != null ? semifinalResult.getScoreString() : "*";
        }

        // 获取决赛分数字符串，不存在的成绩用"*"表示
        public String getFinalScoreString() {
            return finalResult != null ? finalResult.getScoreString() : "*";
        }

    }
}
