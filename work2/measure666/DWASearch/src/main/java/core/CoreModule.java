package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Athlete;
import domain.Contest;
import utils.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CoreModule {

    private static final ObjectMapper mapper = new ObjectMapper();

    //解析 athlete.json 按国籍，姓名排序后返回
    List<String> parseAthletes(InputStream in) throws IOException {
        JsonNode nations = mapper.readTree(in);
        if (!nations.isArray()) {
            throw new IOException("athletes.json 顶层应为数组");
        }
        List<Athlete> athletes = new ArrayList<>();
        //遍历 国家
        for (JsonNode nation : nations) {
            //获得 国家名字
            String countryName = requireText(nation, "CountryName");
            JsonNode participations = nation.get("Participations");
            if (participations == null) {
                throw new IOException("缺少字段: Participations");
            }
            //遍历 参赛队员
            for (JsonNode p : participations) {
                athletes.add(new Athlete(
                        countryName,
                        requireInt(p, "Gender"),
                        requireText(p, "PreferredLastName"),
                        requireText(p, "PreferredFirstName")
                ));
            }
        }
        //按 国籍升序 姓氏升序 排序
        athletes.sort(Comparator
                .comparing(Athlete::getCountryName)
                .thenComparing(Athlete::getPreferredLastName)
        );
        List<String> lines = new ArrayList<>();
        for (Athlete athlete : athletes) {
            lines.add(athlete.toString());
        }
        return lines;
    }

    //输出所有选手信息
    public void displayAllPlayersInfo() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("athletes.json")) {
            if (in == null) {
                throw new IOException("资源缺失：athletes.json");
            }
            for (String line : parseAthletes(in)) {
                Utility.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取运动员数据失败", e);
        }
    }


    //解析 项目json 按排名升序 排序后返回
    List<String> parseResults(InputStream in) throws IOException {
        JsonNode root = mapper.readTree(in);
        JsonNode heats = root.get("Heats");
        if (heats == null) {
            throw new IOException("缺少字段: Heats");
        }
        List<Contest> contests = new ArrayList<>();
        //提取 Heats 列表
        for (JsonNode heat : heats) {
            if (!heat.get("Name").asText().equals("Finals")) {
                continue;
            }
            JsonNode results = heat.get("Results");
            if (results == null) {
                throw new IOException("缺少字段: Results");
            }
            //获取当前比赛结果
            for (JsonNode result : results) {
                Contest contest = new Contest(
                        requireText(result, "TotalPoints"),
                        requireInt(result, "Rank"),
                        // FullName (如果是双人跳水，json 中 FullName 为 "A / B" 格式，将其替换成 & 输出)
                        requireText(result, "FullName").replace('/', '&'),
                        new ArrayList<>()
                );
                JsonNode dives = result.get("Dives");
                if (dives == null) {
                    throw new IOException("缺少字段: Dives");
                }
                //获取 每一次跳水 的 score 添加到 contest 中的 dives 集合
                for (JsonNode dive : dives) {
                    contest.getDives().add(requireText(dive, "DivePoints"));
                }
                contests.add(contest);
            }
        }
        //按排名升序 排序后返回
        contests.sort(Comparator.comparingInt(Contest::getRank));
        List<String> lines = new ArrayList<>();
        for (Contest contest : contests) {
            lines.add(contest.toString());
        }
        return lines;
    }

    //输出每个项目决赛结果
    public void displayResults(String contestName) {
        //读取 json 文件并解析
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(contestName + ".json")) {
            if (in == null) {
                throw new IOException("资源缺失: " + contestName + ".json");
            }
            for (String line : parseResults(in)) {
                Utility.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("读取比赛数据失败", e);
        }
    }

    private static String requireText(JsonNode node, String field) throws IOException {
        JsonNode v = node.get(field);
        if (v == null || v.isNull()) {
            throw new IOException("缺少字段: " + field);
        }
        return v.asText();
    }

    private static int requireInt(JsonNode node, String field) throws IOException {
        JsonNode v = node.get(field);
        if (v == null || v.isNull()) {
            throw new IOException("缺少字段: " + field);
        }
        return v.asInt();
    }
}
