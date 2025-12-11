package com.jay.work2;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lib {

    //全局常量
    public static final List<String> VALID_EVENTS = Arrays.asList(
            "women 1m springboard",
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 1m springboard",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );
    public static final String RESULT_JSON_DIR = "data/";

    private static final Gson gson = new Gson();

    public static class Player {
        private String fullName; // 全名
        private String gender;   // 性别
        private String country;  // 国籍

        // 构造方法
        public Player(String fullName, String gender, String country) {
            this.fullName = fullName;
            this.gender = gender;
            this.country = country;
        }

        // Getter
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

    public static class Country {
        // @SerializedName：JSON字段名和Java属性名不一致
        //直接采用nat中国籍，故删除CountryName
        @SerializedName("Participations")
        private List<Participation> participations; // 该国家的选手参与列表

        // Getter方法

        public List<Participation> getParticipations() {
            return participations;
        }
    }

    public static class Participation {
        @SerializedName("PreferredLastName")
        private String lastName; // 选手姓氏

        @SerializedName("PreferredFirstName")
        private String firstName; // 选手名字

        @SerializedName("Gender")
        private int gender; // 性别（0=男性，1=女性）

        @SerializedName("NAT")
        private String nat; // 国籍

        // Getter方法
        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public int getGender() {
            return gender;
        }

        public String getNat() {
            return nat;
        }
    }

    // 所有比赛结果
    public static class EventResult {
        @SerializedName("Heats")
        private List<Heat> Heats;

        // Getter

        public List<Heat> getHeats() {
            return Heats;
        }
    }

    public static class Heat {
        @SerializedName("Results")
        private List<PlayerResult> Results;

        public List<PlayerResult> getResults() {
            return Results;
        }
    }

    public static class PlayerResult {
        @SerializedName("Rank")
        private int Rank;

        @SerializedName("TotalPoints")
        private double TotalPoints;

        @SerializedName("LastName")
        private String LastName;

        @SerializedName("FirstName")
        private String FirstName;

        @SerializedName("NAT")
        private String NAT;

        @SerializedName("Competitors")
        private List<Competitor> Competitors;

        @SerializedName("Dives")
        private List<DiveDetail> Dives;

        public int getRank() { return Rank; }
        public double getTotalPoints() { return TotalPoints; }

        // 新增 Getter
        public String getLastName() { return LastName; }
        public String getFirstName() { return FirstName; }
        public String getNAT() { return NAT; }

        public List<Competitor> getCompetitors() { return Competitors; }
        public List<DiveDetail> getDives() { return Dives; }
    }


    public static class Competitor {
        @SerializedName("FirstName")
        private String FirstName;

        @SerializedName("LastName")
        private String LastName;

        @SerializedName("NAT")
        private String NAT;

        public String getFirstName() { return FirstName; }
        public String getLastName() { return LastName; }
        public String getNAT() { return NAT; }
    }

    public static class DiveDetail {
        private int DiveOrder;
        private String DiveNo;
        private double DD;
        private double DivePoints;

        // Getter
        public int getDiveOrder() {
            return DiveOrder;
        }
        public String getDiveNo() {
            return DiveNo;
        }
        public double getDD() {
            return DD;
        }
        public double getDivePoints() {
            return DivePoints;
        }
    }



    // 通用工具方法
    public static boolean isAllLowerCase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }


    //读取文件
    public static String readFile(String filePath) throws IOException {
        // try-with-resources：自动关闭流
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder(); // 拼接所有行内容
            String line; // 存储每行内容
            while ((line = br.readLine()) != null) { // 逐行读取，直到文件末尾
                sb.append(line).append("\n");
            }
            // 去掉最后多余的换行符
            return sb.toString().trim();
        }


    }


    // 写入文件（UTF-8）
    public static void writeFile(String filePath, String content) throws IOException {

        String cleanContent = content.replaceAll("\\n+", "\n").trim();
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(cleanContent);
        }
    }


    //解析json中player（athletes）的信息
    public static List<Lib.Player> loadPlayersFromJson(String jsonFilePath) throws IOException {

        String jsonContent = readFile(jsonFilePath);

        List<Lib.Country> countryList = gson.fromJson(
                jsonContent,
                new TypeToken<List<Country>>() {}.getType()
        );

        // 遍历所有国家和选手，提取核心信息封装成List<Player>遍历每个 Country 里的选手，
        // 转换成简化的Player（姓名 / 性别 / 国籍）
        List<Lib.Player> playerList = new ArrayList<>();
        if (countryList == null) {return playerList;} // 兜底：JSON为空返回空列表

        // 遍历每个国家
        for (Lib.Country country : countryList) {
            List<Lib.Participation> participations = country.getParticipations();
            if (participations == null) {
                continue; // 跳过没有选手的国家
            }

            // 遍历每个国家的选手
            for (Lib.Participation participation : participations) {
                // 姓氏 + 名字
                String fullName = participation.getLastName() + " " + participation.getFirstName();
                // 0→Male，1→Female
                String gender = participation.getGender() == 0 ? "Male" : "Female";
                // 国籍：NAT
                String countryName = participation.getNat();
                // 创建Player对象
                playerList.add(new Lib.Player(fullName, gender, countryName));
            }
        }

        return playerList;
    }


    /**
     * 处理result指令
     */
    public static String handleResultCommand(String command) {
        //去掉指令首尾的空格
        String rawCmd = command.trim();
        //把所有“一个或多个空格”替换成“1个空格
        String standardCmd = rawCmd.replaceAll("\\s+", " ");
        //若和标准化指令不一样
        if (!rawCmd.equals(standardCmd)) {
            return "N/A";
        }

        String[] parts = standardCmd.split(" ");
        String targetEvent = "";
        //detail检验
        boolean isDetail = false;

        //如result men 1m springboard detail

        if (parts.length >= 4) {
            if (parts.length >= 5 && "detail".equals(parts[parts.length - 1])) {
                isDetail = true;
                StringBuilder eventSb = new StringBuilder();
                for (int i = 1; i < parts.length - 1; i++) {
                    eventSb.append(parts[i]).append(" ");
                }
                targetEvent = eventSb.toString().trim();
                //指令是4个部分（不带detail）
            } else if (parts.length >= 4 && !"detail".equals(parts[parts.length - 1])) {
                StringBuilder eventSb = new StringBuilder();
                for (int i = 1; i < parts.length; i++) {
                    eventSb.append(parts[i]).append(" ");
                }
                targetEvent = eventSb.toString().trim();
            }
        }

        //非法
        if (!VALID_EVENTS.contains(targetEvent)) {
            return "N/A";
        }

        // 忽略大小写查找 JSON 文件
        String targetFile = targetEvent + ".json";
        File dir = new File(RESULT_JSON_DIR);
        File matchedFile = null;

        if (dir.exists() && dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (f.getName().equalsIgnoreCase(targetFile)) {
                    matchedFile = f;
                    break;
                }
            }
        }

        if (matchedFile == null) {
            return "N/A";
        }

        String jsonFilePath = matchedFile.getPath();

        EventResult eventResult;
        try {
            String jsonContent = readFile(jsonFilePath);
            eventResult = gson.fromJson(jsonContent, Lib.EventResult.class);
            if (eventResult == null || eventResult.getHeats() == null || eventResult.getHeats().isEmpty()) {
                return "N/A";
            }
        } catch (FileNotFoundException | JsonSyntaxException e) {
            return "N/A";
        } catch (IOException e) {
            return "N/A";
        }
        //拼接比赛结果
        StringBuilder resultSb = new StringBuilder();
        Heat firstHeat = eventResult.getHeats().get(0);
        if (firstHeat == null || firstHeat.getResults() == null || firstHeat.getResults().isEmpty()) {
            return "N/A";
        }
        List<PlayerResult> playerResults = firstHeat.getResults();
        playerResults.sort((pr1, pr2) -> Double.compare(pr2.getTotalPoints(), pr1.getTotalPoints()));

        for (PlayerResult pr : playerResults) {

            if ((pr.getCompetitors() == null || pr.getCompetitors().isEmpty())
                    && pr.getFirstName() == null) {
                continue;
            }

            String name;
            String nat;

            if (pr.getCompetitors() == null || pr.getCompetitors().isEmpty()) {
                name = pr.getLastName() + " " + pr.getFirstName();
                nat = pr.getNAT();
            } else {
                StringBuilder fullName = new StringBuilder();
                StringBuilder country = new StringBuilder();
                for (Competitor c : pr.getCompetitors()) {
                    fullName.append(c.getLastName()).append(" ").append(c.getFirstName()).append(" / ");
                    country.append(c.getNAT()).append(" / ");
                }
                name = fullName.substring(0, fullName.length() - 3);
                nat = country.substring(0, country.length() - 3);
            }

            resultSb.append("Full Name:").append(name).append("\n");
            resultSb.append("Rank:").append(pr.getRank()).append("\n");
            resultSb.append("Country:").append(nat).append("\n");
            resultSb.append("Total Points:").append(String.format("%.2f", pr.getTotalPoints())).append("\n");

            if (isDetail) {
                resultSb.append("----- Dive Details -----\n");
                List<DiveDetail> dives = pr.getDives();
                if (dives != null && !dives.isEmpty()) {
                    for (DiveDetail dive : dives) {
                        resultSb.append("Dive ").append(dive.getDiveOrder()).append("\n");
                        resultSb.append("  Action:").append(dive.getDiveNo() == null ? "" : dive.getDiveNo()).append("\n");
                        resultSb.append("  Difficulty:").append(dive.getDD()).append("\n");
                        resultSb.append("  Dive Points:").append(String.format("%.2f", dive.getDivePoints())).append("\n");
                    }
                }
            }

            resultSb.append("\n");
        }

        String result = resultSb.toString().trim();
        return result.isEmpty() ? result : result + "\n";
    }

}
