import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CoreModule {
    private List<Player>  players = new ArrayList<>();
    private List<Result> competitionResults = new ArrayList<>();

    //类加载时预处理运动员数据
    public CoreModule() {
        extractPlayers();
    }

    //运动员信息
    public String playerInfo() {
        StringBuilder info = new StringBuilder(6000);
        for (Player player : players) {
            info.append("Full Name:").append(player.getFullName()).append("\n");
            info.append("Gender:").append(player.getGender()).append("\n");
            info.append("Country:").append(player.getCountry()).append("\n");
            info.append("-----").append("\n");
        }
        return info.toString();
    }

    //决赛信息
    public String finalResults(String dataPath) {
        extractCompetitionResults(dataPath);
        StringBuilder info = new StringBuilder(3000);
        for (Result result : competitionResults) {
            String finalRank = result.getFinalRank() == -1 ? "*" : String.valueOf(result.getFinalRank());
            info.append("Full Name:").append(result.getFullName()).append("\n");
            info.append("Rank:").append(finalRank).append("\n");
            info.append("Score:").append(createScoresString(result.finalScores , result.finalTotalScore)).append("\n");
            info.append("-----").append("\n");
        }
        return info.toString();
    }

    //详细信息
    public String detailedResults(String dataPath) {
        extractCompetitionResults(dataPath);
        StringBuilder info = new StringBuilder(8000);
        for (Result result : competitionResults) {
            info.append("Full Name:").append(result.getFullName()).append("\n");
            info.append("Rank:").append(createRankString(result)).append("\n");
            info.append("Preliminary Score:").append(createScoresString(result.preliminaryScores , result.preliminaryTotalScore)).append("\n");
            info.append("Semifinal Score:").append(createScoresString(result.semifinalScores , result.semifinalTotalScore)).append("\n");
            info.append("Final Score:").append(createScoresString(result.finalScores , result.finalTotalScore)).append("\n");
            info.append("-----").append("\n");
        }
        return info.toString();
    }

    //提取运动员信息
    private void extractPlayers() {
        String jsonFilePath = "athletes.json";
        Gson gson = new Gson();

        try (InputStream is = CoreModule.class.getClassLoader().getResourceAsStream(jsonFilePath);
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            // 定义 JSON 顶层结构的类型（List<Country>）
            Type countryListType = new TypeToken<List<Country>>() {}.getType();
            List<Country> countries = gson.fromJson(reader, countryListType);

            for (Country country : countries) {
                String nationality = country.getCountryName(); // 国籍（国家名称）

                // 遍历该国的所有运动员
                for (Participations participations : country.getParticipations()) {
                    // 处理性别：0→Male，1→Female
                    String gender = participations.getGender() == 0 ? "Male" : "Female";

                    // 处理全名：LastName + FirstName（如 "HART Alexander"）
                    String fullName = participations.getLastName() + " " + participations.getFirstName();

                    // 创建Player对象并添加到players列表
                    Player player = new Player(fullName, gender, nationality);
                    players.add(player);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //提取比赛成绩（提取运动员的比赛成绩（含初赛/半决赛/决赛），存入 competitionResults 列表）
    private void extractCompetitionResults(String dataPath) {
        competitionResults.clear();//每次调用前清空，避免上一次残留数据的影响
        Gson gson = new Gson();

        try (InputStream is = CoreModule.class.getClassLoader().getResourceAsStream(dataPath);
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            // 定义JSON顶层结构的类型（Discipline）
            Type disciplineType = new TypeToken<Discipline>() {}.getType();
            Discipline discipline = gson.fromJson(reader, disciplineType);
            String disciplineName = discipline.getDisciplineName(); // 比赛项目名称

            // 遍历所有轮次，收集每个选手的全轮次成绩
            Map<String, Result> nameToResult = new HashMap<>(); // 选手姓名→Result对象映射

            for (Heat heat : discipline.getHeats()) {
                String phaseName = heat.getPhaseName(); // 轮次名称（Preliminary/Semifinals/Finals）

                // 遍历该轮次所有选手成绩
                for (ResultData resultData : heat.getResults()) {
                    // 处理选手姓名（双人项目按姓氏排序，统一格式）
                    String fullName = sortDoublesName(resultData.getFullName());
                    // 获取选手该轮次的总分
                    double totalScores = Double.parseDouble(resultData.getTotalPoints());

                    // 若该选手尚未创建Result对象，初始化（默认排名-1，得分null）
                    if (!nameToResult.containsKey(fullName)) {
                        Result result = new Result(fullName, -1, -1, -1,
                                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                                0.0 , 0.0 , 0.0 ,
                                disciplineName);
                        nameToResult.put(fullName, result);
                    }

                    // 获取当前选手的Result对象，更新对应轮次的排名和得分
                    Result currentResult = nameToResult.get(fullName);
                    List<Double> diveScores = parseDiveScores(resultData.getDives());

                    switch (phaseName) {
                        case "Preliminary":
                            currentResult.setPreliminaryRank(resultData.getRank());
                            currentResult.setPreliminaryScores(diveScores);
                            currentResult.setPreliminaryTotalScore(totalScores);
                            break;
                        case "Semifinals":
                            currentResult.setSemifinalRank(resultData.getRank());
                            currentResult.setSemifinalScores(diveScores);
                            currentResult.setSemifinalTotalScore(totalScores);
                            break;
                        case "Finals":
                            currentResult.setFinalRank(resultData.getRank());
                            currentResult.setFinalScores(diveScores);
                            currentResult.setFinalTotalScore(totalScores);
                            break;
                    }
                }
            }

            // 将所有选手的Result对象存入列表（去重后完整数据）
            competitionResults.addAll(nameToResult.values());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No such file or directory");
            competitionResults.clear(); // 异常时清空列表
        }
    }

    //辅助方法：将分数拼接成要求的字符串
    private String createScoresString(List<Double> scores,double totalScore) {
        StringBuilder scoresString = new StringBuilder();
        if(scores.isEmpty()){
            scoresString.append(" * ");
        } else {
            boolean isFirst = true;
            for (Double score : scores) {
                if(isFirst){
                    scoresString.append(scores.get(0));
                    isFirst = false;
                } else {
                    scoresString.append(" + ").append(score);
                }
            }
            scoresString.append(" = ").append(String.format("%.2f", totalScore));
        }
        return scoresString.toString();
    }

    //辅助方法：拼接排名字符串
    private String createRankString(Result result) {
        StringBuilder rankString = new StringBuilder();
        if (result.getPreliminaryRank() == -1) rankString.append("*");
        else rankString.append(result.getPreliminaryRank());
        rankString.append(" | ");

        if (result.getSemifinalRank() == -1) rankString.append("*");
        else rankString.append(result.getSemifinalRank());
        rankString.append(" | ");

        if (result.getFinalRank() == -1) rankString.append("*");
        else rankString.append(result.getFinalRank());

        return rankString.toString();
    }

    //辅助方法：解析单个轮次的跳水得分，转为Double列表
    List<Double> parseDiveScores(List<Dive> dives) {
        if (dives == null || dives.isEmpty()) {
            return null;
        }
        List<Double> scores = new ArrayList<>();
        for (Dive dive : dives) {
            try {
                scores.add(Double.parseDouble(dive.getDivePoints()));
            } catch (NumberFormatException e) {
                scores.add(0.0); // 异常得分默认设为0.0
            }
        }
        return scores;
    }

    // 辅助方法：双人项目姓名排序（按姓氏字母顺序）
    String sortDoublesName(String name) {
        if (name != null && name.contains(" & ")) {
            String[] parts = name.split(" & ");
            // 按姓氏（第一个单词）字母顺序排序
            Arrays.sort(parts, Comparator.comparing(part -> {
                String[] nameParts = part.split(" ");
                return nameParts.length > 0 ? nameParts[0] : part;
            }));
            return parts[0] + " & " + parts[1];
        }
        return name == null ? "" : name;
    }

    //player表示一个运动员
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

    //Result表示一个运动员在比赛中的成绩
    public static class Result {
        private final String fullName;
        private int preliminaryRank;
        private int semifinalRank;
        private int finalRank;
        private List<Double> preliminaryScores;
        private List<Double> semifinalScores;
        private List<Double> finalScores;
        private double preliminaryTotalScore;
        private double semifinalTotalScore;
        private double finalTotalScore;
        private final String disciplineName;

        public Result(String fullName, int preliminaryRank, int semifinalRank, int finalRank,
                      List<Double> preliminaryScores, List<Double> semifinalScores,
                      List<Double> finalScores, double preliminaryTotalScore ,
                      double semifinalTotalScore , double finalTotalScore ,String disciplineName) {
            this.fullName = fullName;
            this.preliminaryRank = preliminaryRank;
            this.semifinalRank = semifinalRank;
            this.finalRank = finalRank;
            this.preliminaryScores = preliminaryScores;
            this.semifinalScores = semifinalScores;
            this.finalScores = finalScores;
            this.preliminaryTotalScore = preliminaryTotalScore;
            this.semifinalTotalScore = semifinalTotalScore;
            this.finalTotalScore = finalTotalScore;
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

        public double getPreliminaryTotalScore() {
            return preliminaryTotalScore;
        }

        public void setPreliminaryTotalScore(double preliminaryTotalScore) {
            this.preliminaryTotalScore = preliminaryTotalScore;
        }

        public double getSemifinalTotalScore() {
            return semifinalTotalScore;
        }

        public void setSemifinalTotalScore(double semifinalTotalScore) {
            this.semifinalTotalScore = semifinalTotalScore;
        }

        public double getFinalTotalScore() {
            return finalTotalScore;
        }

        public void setFinalTotalScore(double finalTotalScore) {
            this.finalTotalScore = finalTotalScore;
        }
    }

    // JSON映射类：Participations
    public class Participations {
        // 映射JSON中的Gender（0=男，1=女）
        @SerializedName("Gender")
        private int gender;

        // 映射JSON中的PreferredLastName（姓）
        @SerializedName("PreferredLastName")
        private String lastName;

        // 映射JSON中的PreferredFirstName（名）
        @SerializedName("PreferredFirstName")
        private String firstName;

        public int getGender() {
            return gender;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }
    }

    // JSON映射类：Country
    public class Country {
        // 映射JSON中的CountryName
        @SerializedName("CountryName")
        private String countryName;

        // 映射JSON中的Participations（运动员列表）
        @SerializedName("Participations")
        private List<Participations> participations;

        public String getCountryName() {
            return countryName;
        }

        public List<Participations> getParticipations() {
            return participations;
        }
    }

    // JSON映射类：DisciplineName
    public static class Discipline {
        @SerializedName("DisciplineName")
        private String disciplineName;

        @SerializedName("Heats")
        private List<Heat> heats;

        public String getDisciplineName() { return disciplineName; }
        public List<Heat> getHeats() { return heats; }
    }

    // JSON映射类：Heats
    public static class Heat {
        @SerializedName("PhaseName")
        private String phaseName;

        @SerializedName("Results")
        private List<ResultData> results;

        public String getPhaseName() { return phaseName; }
        public List<ResultData> getResults() { return results; }
    }

    // JSON映射类：Result
    public static class ResultData {
        @SerializedName("FullName")
        private String fullName;

        @SerializedName("Rank")
        private int rank;

        @SerializedName("Dives")
        private List<Dive> dives;

        @SerializedName("TotalPoints")
        private String totalPoints;

        public String getFullName() { return fullName; }
        public int getRank() { return rank; }
        public List<Dive> getDives() { return dives; }
        public String getTotalPoints() { return totalPoints; }
    }

    // JSON映射类：Dive
    public static class Dive {
        @SerializedName("DivePoints")
        private String divePoints;

        public String getDivePoints() { return divePoints; }
    }
}
