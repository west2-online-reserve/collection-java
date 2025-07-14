package com.dwa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CoreModuleImpl implements CoreModule {

    // 数据文件路径
    private static final String DATA_PATH = "/Users/cjf/IdeaProjects/DWASearch/src/main/resources/data/";

    // 合法的比赛名称集合（小写且带空格）
    private static final Set<String> VALID_CONTEST_NAMES = new HashSet<>(Arrays.asList(
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
    ));

    // 输入名称对应文件名映射
    private static final Map<String, String> CONTEST_NAME_TO_FILE = new HashMap<>();
    static {
        CONTEST_NAME_TO_FILE.put("women 1m springboard", "women 1m springboard.json");
        CONTEST_NAME_TO_FILE.put("women 3m springboard", "women 3m springboard.json");
        CONTEST_NAME_TO_FILE.put("women 10m platform", "women_10m_platform.json");
        CONTEST_NAME_TO_FILE.put("women 3m synchronised", "women_3m_synchronised.json");
        CONTEST_NAME_TO_FILE.put("women 10m synchronised", "women_10m_synchronised.json");
        CONTEST_NAME_TO_FILE.put("men 1m springboard", "men_1m_springboard.json");
        CONTEST_NAME_TO_FILE.put("men 3m springboard", "men_3m_springboard.json");
        CONTEST_NAME_TO_FILE.put("men 10m platform", "men_10m_platform.json");
        CONTEST_NAME_TO_FILE.put("men 3m synchronised", "men_3m_synchronised.json");
        CONTEST_NAME_TO_FILE.put("men 10m synchronised", "men_10m_synchronised.json");
    }

    private List<Player> players;
    private Map<String, List<ContestDetailed>> contestMap;
    private ObjectMapper mapper;

    // 构造方法
    public CoreModuleImpl() {
        mapper = new ObjectMapper();
        contestMap = new HashMap<>();
        try {
            loadPlayers();
            loadAllContests();
        } catch (IOException e) {
            System.err.println("加载数据失败：" + e.getMessage());
            players = new ArrayList<>();
            contestMap.clear();
        }
    }

    // 读取运动员json文件，并转换为List<Player>
    private void loadPlayers() throws IOException {
        File file = new File(DATA_PATH + "athletes.json");
        if (!file.exists()) {
            System.err.println("选手数据文件不存在: " + file.getAbsolutePath());
            players = new ArrayList<>();
            return;
        }
        players = mapper.readValue(file, new TypeReference<List<Player>>() {});
    }

    // 循环合法比赛找对应文件
    private void loadAllContests() throws IOException {
        for (String contestName : VALID_CONTEST_NAMES) {
            String fileName = CONTEST_NAME_TO_FILE.get(contestName);
            if (fileName == null) continue;
            File file = new File(DATA_PATH + fileName);
            if (!file.exists()) {
                contestMap.put(contestName, Collections.emptyList());
                continue;
            }
            List<ContestDetailed> list = mapper.readValue(file, new TypeReference<List<ContestDetailed>>() {});
            contestMap.put(contestName, list);
        }
    }

    @Override
    public void displayAllPlayersInfo() {
        throw new UnsupportedOperationException("请使用带IOHelper参数的方法");
    }

    @Override
    public void displayResults(String contestName) {
        throw new UnsupportedOperationException("请使用带IOHelper参数的方法");
    }

    @Override
    public void displayDetailedResults(String contestName) {
        throw new UnsupportedOperationException("请使用带IOHelper参数的方法");
    }

    public boolean isValidContestName(String name) {
        return name != null && VALID_CONTEST_NAMES.contains(name);
    }

    // 显示所有运动员信息
    public void displayAllPlayersInfo(IOHelper ioHelper) throws IOException {
        if (players == null || players.isEmpty()) {
            ioHelper.writeLine("N/A");
            ioHelper.writeLine("-----");
            return;
        }

        // 若数据没排序，按Country升序、LastName升序排序
        players.sort(Comparator
                .comparing(Player::getCountryName)
                .thenComparing(Player::getPreferredLastName));

        for (Player p : players) {
            String fullName = p.getPreferredLastName() + " " + p.getPreferredFirstName();
            ioHelper.writeLine("Full Name:" + fullName);
            ioHelper.writeLine("Gender:" + (p.getGender() == 0 ? "Male" : "Female"));
            ioHelper.writeLine("Country:" + p.getCountryName());
            ioHelper.writeLine("-----");
        }
    }

    // 显示决赛结果
    @Override
    public void displayResults(String contestName, IOHelper ioHelper) {
        try {
            if (!isValidContestName(contestName)) {
                ioHelper.writeLine("N/A");
                ioHelper.writeLine("-----");
                return;
            }
            List<ContestDetailed> details = contestMap.get(contestName);
            if (details == null || details.isEmpty()) {
                ioHelper.writeLine("N/A");
                ioHelper.writeLine("-----");
                return;
            }

            for (ContestDetailed detail : details) {
                Contest finalC = detail.getContests()[2]; // 决赛
                if (finalC == null) continue;
                ioHelper.writeLine("Full Name:" + finalC.getFullName());
                ioHelper.writeLine("Rank:" + finalC.getRank());
                ioHelper.writeLine("Score:" + formatScore(finalC.getDives(), finalC.getTotalPoints()));
                ioHelper.writeLine("-----");
            }
        } catch (IOException e) {
            try {
                ioHelper.writeLine("Error");
                ioHelper.writeLine("-----");
            } catch (IOException ignored) {}
        }
    }

    // 显示详细结果（初赛，半决赛，决赛）
    public void displayDetailedResults(String contestName, IOHelper ioHelper) throws IOException {
        if (!isValidContestName(contestName)) {
            ioHelper.writeLine("N/A");
            ioHelper.writeLine("-----");
            return;
        }
        List<ContestDetailed> details = contestMap.get(contestName);
        if (details == null || details.isEmpty()) {
            ioHelper.writeLine("N/A");
            ioHelper.writeLine("-----");
            return;
        }

        for (ContestDetailed detail : details) {
            Contest prelim = detail.getContests()[0];
            Contest semi = detail.getContests()[1];
            Contest finalC = detail.getContests()[2];

            String fullName = firstNonEmptyFullName(prelim, semi, finalC);
            ioHelper.writeLine("Full Name:" + fullName);

            String rankStr = formatRank(prelim) + " | " + formatRank(semi) + " | " + formatRank(finalC);
            ioHelper.writeLine("Rank:" + rankStr);

            ioHelper.writeLine("Preliminary Score:" + formatScoreOrStar(prelim));
            ioHelper.writeLine("Semifinal Score:" + formatScoreOrStar(semi));
            ioHelper.writeLine("Final Score:" + formatScoreOrStar(finalC));
            ioHelper.writeLine("-----");
        }
    }

    // 工具方法
    private String firstNonEmptyFullName(Contest... contests) {
        for (Contest c : contests) {
            if (c != null && c.getFullName() != null && !c.getFullName().trim().isEmpty()) {
                return c.getFullName();
            }
        }
        return "*";
    }

    private String formatRank(Contest c) {
        if (c == null || c.getRank() <= 0) {
            return "*";
        }
        return String.valueOf(c.getRank());
    }

    private String formatScoreOrStar(Contest c) {
        if (c == null || c.getDives() == null || c.getDives().isEmpty() || c.getTotalPoints() == null) {
            return "*";
        }
        return formatScore(c.getDives(), c.getTotalPoints());
    }

    private String formatScore(List<String> dives, String totalPoints) {
        return String.join(" + ", dives) + " = " + totalPoints;
    }
}