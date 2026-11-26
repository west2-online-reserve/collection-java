package com.dwa;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorldAquaticsClient {

    // 比赛的 Competition ID
    private static final String COMPETITION_ID = "5019";

    // World Aquatics API 地址
    private static final String BASE_URL =
            "https://api.worldaquatics.com/fina";

    // HTTP 客户端
    private final HttpClient client = HttpClient.newHttpClient();

    // 发送 GET 请求并获取 JSON
    public String getJson(String url)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "DWASearch/1.0")
                .header("Accept", "application/json")
                .header("Origin", "https://www.worldaquatics.com")
                .header("Referer", "https://www.worldaquatics.com")
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "请求失败，HTTP 状态码：" + response.statusCode()
            );
        }

        return response.body();
    }

    // 将 JSON 保存到本地文件
    public void saveJson(String json, String filePath)
            throws IOException {

        Path path = Paths.get(filePath);

        // 如果目录不存在，就创建目录
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        Files.writeString(
                path,
                json,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        System.out.println("保存成功：" + path);
    }

    // 下载运动员名单
    public void downloadPlayers()
            throws IOException, InterruptedException {

        String url = BASE_URL
                + "/competitions/"
                + COMPETITION_ID
                + "/athletes?gender=&countryId=";

        String json = getJson(url);

        saveJson(json, "data/players.json");
    }

    // 下载比赛项目列表
    public void downloadEventList()
            throws IOException, InterruptedException {

        String url = BASE_URL
                + "/competitions/"
                + COMPETITION_ID
                + "/events";

        String json = getJson(url);

        saveJson(json, "data/event_list.json");
    }
    // 下载八个项目的比赛数据
    public void downloadResults()
            throws IOException, InterruptedException {

        String[][] events = {
                {"men_3m_springboard",
                        "4c18d241-ad14-436a-bd38-25dfb47f1cb7"},

                {"men_3m_synchronised",
                        "08d17e1c-94c2-4c56-bf2b-603eeba24f8c"},

                {"men_10m_platform",
                        "450a9e84-5db0-40c4-a45c-4c3bb2392f48"},

                {"men_10m_synchronised",
                        "f86bd80d-a342-40f7-a521-90a50ad9d5c5"},

                {"women_3m_springboard",
                        "2b08da89-8232-4ffc-8df3-95865ba41b82"},

                {"women_3m_synchronised",
                        "61b483ec-4022-4555-9960-fba9cd1d702d"},

                {"women_10m_platform",
                        "581a26af-8e97-427c-9adb-5fdd8f4fc9c1"},

                {"women_10m_synchronised",
                        "c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b"}
        };

        for (String[] event : events) {

            String eventName = event[0];
            String eventId = event[1];

            String url = BASE_URL + "/events/" + eventId;

            System.out.println("正在下载：" + eventName);

            String json = getJson(url);

            saveJson(
                    json,
                    "data/results/" + eventName + ".json"
            );
        }
    }

    public static void main(String[] args) {

        WorldAquaticsClient downloader =
                new WorldAquaticsClient();

        try {
            downloader.downloadPlayers();
            downloader.downloadEventList();
            downloader.downloadResults();

            System.out.println("全部数据下载完成");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}