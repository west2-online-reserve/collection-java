package scraper.app;

import scraper.analysis.DataOrganizer;
import scraper.get.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {

    public static void main(String[] args) {
        /*
            1.获取所有原始数据
            2.将原始数据整理清洗形成我们想要的结构
         */

        String ATHLETES_URL = "https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=";
        String EVENT_URL = "https://api.worldaquatics.com/fina/competitions/5019/events";

        // 运动员的全部信息
        WebScraper scraper = new WebScraper();
        String athletes_json = scraper.get_json(ATHLETES_URL);

        // 决赛的全部信息 8项比赛
        ArrayList<String> competition_json_list = new ArrayList<>();
        get_all_competitions_json(EVENT_URL,competition_json_list);

        String dataJson = DataOrganizer.organize(
                athletes_json,
                competition_json_list
        );

        Path filePath = Paths.get("data.json");
        try {
            Files.writeString(filePath, dataJson, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        System.out.println("保存成功，路径：" + filePath.toAbsolutePath());

    }

    // 获得所有比赛的原始数据
    public static void get_all_competitions_json(String event_url , ArrayList<String> list) {
        String COMPETITION_BASE_URL = "https://api.worldaquatics.com/fina/events/";

        WebScraper scraper = new WebScraper();
        String event_json = scraper.get_json(event_url);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root;
        try {
            root = mapper.readTree(event_json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JsonNode disciplineList = root.path("Sports").get(0).path("DisciplineList");

        if (disciplineList.isArray()) {
            for (JsonNode item : disciplineList) {
                String id = item.path("Id").asText();
                String discipline = scraper.get_json(COMPETITION_BASE_URL + id);
                list.add(discipline);
            }
        }
    }
}
