import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class CoreModule {
    private static String outputFilePath=null;

    //players，输出所有选手信息
    public static void displayAllPlayersInfo(String filePath) throws IOException {
        List<Athlete> athletes = parseAthleteData(filePath);
        writeOutputFile(outputFilePath, formatAthletes(athletes));
    }

    //result 项目 detail，输出项目结果
    public static void displayResultsForEachEvent(String filePath, boolean detail) throws IOException {
        Map<String, DiverData> diverDatas = parseDivingData(filePath);
        if (detail) {
            writeOutputFile(outputFilePath, formatDivingDetailData(diverDatas));
        } else {
            writeOutputFile(outputFilePath, formatDivingData(diverDatas));
        }
    }

    //错误信息的filePath读入
    public static void displayError() throws IOException {
        StringBuilder output = new StringBuilder();
        output.append("Error\n-----\n");
        writeOutputFile(outputFilePath, output.toString());
    }

    //无法识别的信息读入
    public static void displayNA() throws IOException {
        StringBuilder output = new StringBuilder();
        output.append("N/A\n-----\n");
        writeOutputFile(outputFilePath, output.toString());
    }


    //运动员信息的处理

    //解析运动员信息
    public static List<Athlete> parseAthleteData(String filePath) throws IOException {
        List<Athlete> athletes = new ArrayList<>();
        String currentCountry = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Athlete currentAthlete = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("CountryName:")) {
                    // 处理国家名称
                    currentCountry = line.substring(12).trim();
                } else if (line.startsWith("Gender:")) {
                    // 开始新的选手
                    currentAthlete = new Athlete();
                    currentAthlete.setGender(line.substring(7).trim());
                    currentAthlete.setCountry(currentCountry);
                } else if (!line.isEmpty() && currentAthlete != null) {
                    // 设置选手姓名并加入列表
                    currentAthlete.setName(line);
                    athletes.add(currentAthlete);
                    currentAthlete = null; // 清除当前选手，准备处理下一个
                }
            }
        }
        athletes.sort(Comparator.comparing(Athlete::getCountry)
                .thenComparing(Athlete::getName));

        return athletes;
    }

    // 格式化运动员列表
    public static String formatAthletes(List<Athlete> athletes) {
        StringBuilder output = new StringBuilder();

        for (Athlete athlete : athletes) {
            output.append("Full Name:").append(athlete.getName()).append("\n")
                    .append("Gender:").append(athlete.getGender()).append("\n")
                    .append("Country:").append(athlete.getCountry()).append("\n")
                    .append("-----\n");
        }

        return output.toString();
    }

    // 运动员数据类
    static class Athlete {
        private String name;
        private String gender;
        private String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }


    //比赛结果的处理

    //解析数据
    public static Map<String, DiverData> parseDivingData(String filePath) throws IOException {
        Map<String, DiverData> diverDataMap = new LinkedHashMap<>();
        String currentStage = null;  // 当前阶段 (Preliminary, Semifinal, Final)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.endsWith(":")) {
                    // 阶段标志 (Preliminary, Semifinal, Final)
                    currentStage = line.substring(0, line.length() - 1);
                } else if (line.startsWith("TotalPoints:")) {
                    // 如果是 TotalPoints 数据，解析分数
                    String scores;
                    if (filePath.indexOf("women") == -1) {
                        scores = parseScores(reader, line, true);
                    } else {
                        scores = parseScores(reader, line, false);
                    }

                    // 继续读取 Rank 和 FullName
                    String rankLine = reader.readLine().trim(); // 读取 "Rank:..."
                    if (!rankLine.startsWith("Rank:")) {
                        throw new IOException("Invalid format: Expected Rank line but got: " + rankLine);
                    }
                    String rank = rankLine.substring(5).trim();

                    String nameLine = reader.readLine().trim(); // 读取 "FullName:..."
                    if (!nameLine.startsWith("FullName:")) {
                        throw new IOException("Invalid format: Expected FullName line but got: " + nameLine);
                    }
                    String name = nameLine.substring(9);
                    if (name.contains(" & ")) {
                        String[] parts = name.split(" & ");
                        if (parts[0].compareTo(parts[1]) > 0) {
                            name = parts[1] + " & " + parts[0];
                        }
                    }
                    // 获取或创建选手数据
                    DiverData diverData = diverDataMap.getOrDefault(name, new DiverData(name));
                    if ("Preliminary".equals(currentStage)) {
                        diverData.setPreliminaryRank(rank);
                        diverData.setPreliminaryScore(scores);
                    } else if ("Semifinal".equals(currentStage)) {
                        diverData.setSemifinalRank(rank);
                        diverData.setSemifinalScore(scores);
                    } else if ("Final".equals(currentStage)) {
                        diverData.setFinalRank(rank);
                        diverData.setFinalScore(scores);
                    }
                    diverDataMap.put(name, diverData);
                }
            }
        }

        return diverDataMap;
    }

    //解析成绩
    public static String parseScores(BufferedReader reader, String firstLine, boolean gender) throws IOException {
        DecimalFormat df = new DecimalFormat("#.00");
        StringBuilder scores = new StringBuilder();
        double SScore = 0;//当前的值
        double CScore = 0;//上一个值
        int n;
        if (gender) {
            n = 6;
        } else {
            n = 5;
        }
        // 第一行已经是 TotalPoints 数据
        String line = firstLine;
        while ((n--) != 0) {
            String currentScore = line.substring(12).trim(); // 提取 TotalPoints 值
            if (scores.length() > 0) {
                scores.append(" + ");
            }
            CScore = Double.parseDouble(currentScore);
            scores.append(df.format(CScore - SScore));
            SScore = CScore;
            if(n!=0) line = reader.readLine(); // 继续读取下一行
        }

        if (SScore != 0) {
            scores.append(" = ").append(df.format(SScore)); // 使用最后一个分数作为总分
        }

        return scores.toString();
    }

    //格式化detail的比赛结果
    public static String formatDivingDetailData(Map<String, DiverData> diverDataMap) {
        StringBuilder output = new StringBuilder();
        for (DiverData diver : diverDataMap.values()) {
            output.append("Full Name:").append(diver.getName()).append("\n")
                    .append("Rank:").append(diver.getPreliminaryRank()).append(" | ")
                    .append(diver.getSemifinalRank()).append(" | ").append(diver.getFinalRank()).append("\n")
                    .append("Preliminary Score:").append(diver.getPreliminaryScore()).append("\n")
                    .append("Semifinal Score:").append(diver.getSemifinalScore()).append("\n")
                    .append("Final Score:").append(diver.getFinalScore()).append("\n")
                    .append("-----\n");

        }
        return output.toString();
    }

    //格式化比赛结果
    public static String formatDivingData(Map<String, DiverData> diverDataMap) {
        StringBuilder output = new StringBuilder();
        for (DiverData diver : diverDataMap.values()) {
            output.append("Full Name:").append(diver.getName()).append("\n")
                    .append("Rank:").append(diver.getFinalRank()).append("\n")
                    .append("Score:").append(diver.getFinalScore()).append("\n")
                    .append("-----\n");
        }
        return output.toString();
    }

    //输出函数，运动员信息与比赛结果均由此输出至output.txt
    public static void writeOutputFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
        }
    }

    //比赛数据类
    static class DiverData {
        private final String name;
        private String preliminaryRank = "*";
        private String semifinalRank = "*";
        private String finalRank = "*";
        private String preliminaryScore = "*";
        private String semifinalScore = "*";
        private String finalScore = "*";

        public DiverData(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getPreliminaryRank() {
            return preliminaryRank;
        }

        public void setPreliminaryRank(String rank) {
            this.preliminaryRank = rank;
        }

        public String getSemifinalRank() {
            return semifinalRank;
        }

        public void setSemifinalRank(String rank) {
            this.semifinalRank = rank;
        }

        public String getFinalRank() {
            return finalRank;
        }

        public void setFinalRank(String rank) {
            this.finalRank = rank;
        }

        public String getPreliminaryScore() {
            return preliminaryScore;
        }

        public void setPreliminaryScore(String score) {
            this.preliminaryScore = score;
        }

        public String getSemifinalScore() {
            return semifinalScore;
        }

        public void setSemifinalScore(String score) {
            this.semifinalScore = score;
        }

        public String getFinalScore() {
            return finalScore;
        }

        public void setFinalScore(String score) {
            this.finalScore = score;
        }
    }

    public static void setOutputFilePath(String output){
        outputFilePath=output;
    }
}
