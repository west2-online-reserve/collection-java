import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoreModule {
    private final List<Player> players = new ArrayList<>(); // 存储所有运动员信息
    private List<Result> results = new ArrayList<>(); // 存储比赛结果

    // 构造函数，初始化模块时加载运动员信息
    public CoreModule(JsonNode athletesData) {
        loadPlayers(athletesData);
    }

    // 从 JSON 数据中加载运动员信息
    private void loadPlayers(JsonNode athletesData) {
        for (JsonNode countryData : athletesData) {
            // 获取国家名称
            String country = countryData.has("CountryName") && !countryData.get("CountryName").isNull() ? countryData.get("CountryName").asText() : "Unknown Country";
            JsonNode participations = countryData.get("Participations");
            for (JsonNode participant : participations) {
                // 获取运动员的姓、名和性别信息
                String lastName = participant.has("PreferredLastName") && !participant.get("PreferredLastName").isNull() ? participant.get("PreferredLastName").asText() : "Unknown LastName";
                String firstName = participant.has("PreferredFirstName") && !participant.get("PreferredFirstName").isNull() ? participant.get("PreferredFirstName").asText() : "Unknown FirstName";
                String gender = participant.has("Gender") && !participant.get("Gender").isNull() && participant.get("Gender").asInt() == 0 ? "Male" : "Female";
                players.add(new Player(firstName + " " + lastName, gender, country)); // 创建 Player 对象并添加到列表中
            }
        }
    }

    // 从 JSON 数据中加载比赛信息
    public void loadContestData(JsonNode contestData) {
        results = new ArrayList<>(); // 清空之前的比赛数据
        String disciplineName = contestData.has("DisciplineName") && !contestData.get("DisciplineName").isNull()
                ? contestData.get("DisciplineName").asText()
                : "Unknown Discipline";

        // 确保 Heats 节点存在且不是空的
        JsonNode heatsNode = contestData.get("Heats");
        if (heatsNode == null || !heatsNode.isArray()) {
            // 如果 Heats 节点为 null 或者不是一个数组，则直接返回，避免 NullPointerException
            return;
        }

        // 迭代每个阶段 (heat)
        for (JsonNode heat : heatsNode) {
            String heatName = heat.has("Name") && !heat.get("Name").isNull() ? heat.get("Name").asText() : "";

            for (JsonNode result : heat.get("Results")) {
                // 获取运动员全名和排名信息
                String fullName = result.has("FullName") && !result.get("FullName").isNull()
                        ? result.get("FullName").asText()
                        : "Unknown Athlete";
                int rank = result.has("Rank") && !result.get("Rank").isNull() ? result.get("Rank").asInt() : -1;

                // 加载每个运动员的得分信息
                List<Double> scores = loadScores(result.get("Dives"));

                // 找到已有的 Result 对象或创建新的 Result 对象
                Result existingResult = findResultByFullName(fullName);
                if (existingResult == null) {
                    existingResult = new Result(fullName, -1, -1, -1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), disciplineName);
                    results.add(existingResult);
                }

                // 根据阶段（预赛、半决赛、决赛）更新相应的排名和得分
                if (heatName.equalsIgnoreCase("Preliminary")) {
                    existingResult.setPreliminaryRank(rank);
                    existingResult.setPreliminaryScores(scores);
                } else if (heatName.equalsIgnoreCase("Semifinal")) {
                    existingResult.setSemifinalRank(rank);
                    existingResult.setSemifinalScores(scores);
                } else if (heatName.equalsIgnoreCase("Final")) {
                    existingResult.setFinalRank(rank);
                    existingResult.setFinalScores(scores);
                }
            }
        }
    }

    // 根据全名查找对应的 Result 对象
    private Result findResultByFullName(String fullName) {
        for (Result result : results) {
            if (result.getFullName().equalsIgnoreCase(fullName)) {
                return result;
            }
        }
        return null;
    }

    // 从 JSON 节点加载得分信息
    private List<Double> loadScores(JsonNode divesNode) {
        List<Double> scores = new ArrayList<>();
        if (divesNode != null) {
            for (JsonNode dive : divesNode) {
                if (dive.has("DivePoints") && !dive.get("DivePoints").isNull()) {
                    scores.add(dive.get("DivePoints").asDouble());
                }
            }
        }
        return scores;
    }

    // 显示所有运动员的信息
    public String displayAllPlayersInfo() {
        players.sort(Comparator.comparing(Player::getCountry).thenComparing(Player::getFullName)); // 先按国家排序，再按全名排序
        StringBuilder output = new StringBuilder();
        for (Player player : players) {
            output.append("Full Name:").append(player.getFullName()).append("\n");
            output.append("Gender:").append(player.getGender()).append("\n");
            output.append("Country:").append(player.getCountry()).append("\n");
            output.append("-----\n");
        }
        return output.toString();
    }

    // 显示指定比赛项目的决赛结果
    public String displayResultsForEvent(String discipline) {
        List<Result> eventResults = new ArrayList<>();
        for (Result result : results) {
            if (result.getDisciplineName().equalsIgnoreCase(discipline) && result.getFinalRank() != -1) {
                eventResults.add(result);
            }
        }

        if (eventResults.isEmpty()) {
            return "N/A_3\n-----\n"; // 如果没有找到结果，返回 N/A_3
        }

        eventResults.sort(Comparator.comparingInt(Result::getFinalRank)); // 按最终排名排序
        StringBuilder output = new StringBuilder();
        for (Result result : eventResults) {
            output.append("Full Name:").append(result.getFullName()).append("\n");
            output.append("Rank:").append(result.getFinalRank()).append("\n");
            output.append("Score:").append(formatScores(result.getFinalScores())).append("\n");
            output.append("-----\n");
        }
        return output.toString();
    }

    // 显示指定比赛项目的详细结果（包括预赛、半决赛、决赛）
    public String displayDetailedResultsForEvent(String discipline) {
        List<Result> eventResults = new ArrayList<>();
        for (Result result : results) {
            if (result.getDisciplineName().equalsIgnoreCase(discipline)) {
                eventResults.add(result);
            }
        }

        if (eventResults.isEmpty()) {
            return "N/A_4\n-----\n"; // 如果没有找到结果，返回 N/A_4
        }

        StringBuilder output = new StringBuilder();
        for (Result result : eventResults) {
            output.append("Full Name:").append(result.getFullName()).append("\n");
            output.append("Rank:").append(formatRank(result.getPreliminaryRank())).append(" | ")
                    .append(formatRank(result.getSemifinalRank())).append(" | ").append(formatRank(result.getFinalRank())).append("\n");
            output.append("Preliminary Score:").append(formatScores(result.getPreliminaryScores())).append("\n");
            output.append("Semifinal Score:").append(formatScores(result.getSemifinalScores())).append("\n");
            output.append("Final Score:").append(formatScores(result.getFinalScores())).append("\n");
            output.append("-----\n");
        }

        return output.toString();
    }

    // 格式化排名，如果排名为 -1，则返回 "*"
    private String formatRank(int rank) {
        return rank == -1 ? "*" : String.valueOf(rank);
    }

    // 格式化得分，输出每轮得分并计算总分
    private String formatScores(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return "*";
        }

        StringBuilder formattedScore = new StringBuilder();
        double total = 0.0;
        for (int i = 0; i < scores.size(); i++) {
            formattedScore.append(String.format("%.2f", scores.get(i)));
            total += scores.get(i);
            if (i < scores.size() - 1) {
                formattedScore.append(" + ");
            }
        }
        formattedScore.append(" = ").append(String.format("%.2f", total));
        return formattedScore.toString();
    }

    // Player 类，代表一个运动员
    private static class Player {
        private final String fullName;
        private final String gender;
        private final String country;

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

    // Result 类，代表一个运动员在比赛中的成绩
    public static class Result {
        private final String fullName;
        private int preliminaryRank;
        private int semifinalRank;
        private int finalRank;
        private List<Double> preliminaryScores;
        private List<Double> semifinalScores;
        private List<Double> finalScores;
        private final String disciplineName;

        public Result(String fullName, int preliminaryRank, int semifinalRank, int finalRank,
                      List<Double> preliminaryScores, List<Double> semifinalScores,
                      List<Double> finalScores, String disciplineName) {
            this.fullName = fullName;
            this.preliminaryRank = preliminaryRank;
            this.semifinalRank = semifinalRank;
            this.finalRank = finalRank;
            this.preliminaryScores = preliminaryScores;
            this.semifinalScores = semifinalScores;
            this.finalScores = finalScores;
            this.disciplineName = disciplineName;
        }

        public String getFullName() {
            return fullName;
        }

        public int getPreliminaryRank() {
            return preliminaryRank;
        }

        public void setPreliminaryRank(int preliminaryRank) {
            this.preliminaryRank = preliminaryRank;
        }

        public int getSemifinalRank() {
            return semifinalRank;
        }

        public void setSemifinalRank(int semifinalRank) {
            this.semifinalRank = semifinalRank;
        }

        public int getFinalRank() {
            return finalRank;
        }

        public void setFinalRank(int finalRank) {
            this.finalRank = finalRank;
        }

        public List<Double> getPreliminaryScores() {
            return preliminaryScores;
        }

        public void setPreliminaryScores(List<Double> preliminaryScores) {
            this.preliminaryScores = preliminaryScores;
        }

        public List<Double> getSemifinalScores() {
            return semifinalScores;
        }

        public void setSemifinalScores(List<Double> semifinalScores) {
            this.semifinalScores = semifinalScores;
        }

        public List<Double> getFinalScores() {
            return finalScores;
        }

        public void setFinalScores(List<Double> finalScores) {
            this.finalScores = finalScores;
        }

        public String getDisciplineName() {
            return disciplineName;
        }
    }
}
