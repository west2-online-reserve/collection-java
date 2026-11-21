package com.dumfishing.dwasearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;

public class FetchData {

    private static final String BASE = "https://api.worldaquatics.com";

    private static final Map<String, String> DISCIPLINES = Map.of(
            "Women's 3m Springboard",   "2b08da89-8232-4ffc-8df3-95865ba41b82",
            "Women's 10m Platform",     "581a26af-8e97-427c-9adb-5fdd8f4fc9c1",
            "Women's 3m Synchronised",  "61b483ec-4022-4555-9960-fba9cd1d702d",
            "Women's 10m Synchronised", "c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b",
            "Men's 3m Springboard",     "4c18d241-ad14-436a-bd38-25dfb47f1cb7",
            "Men's 10m Platform",       "450a9e84-5db0-40c4-a45c-4c3bb2392f48",
            "Men's 3m Synchronised",    "08d17e1c-94c2-4c56-bf2b-603eeba24f8c",
            "Men's 10m Synchronised",   "f86bd80d-a342-40f7-a521-90a50ad9d5c5"
    );

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        fetch(client, "/fina/competitions/5019/athletes", "data/athletes.json");

        for (Map.Entry<String, String> e : DISCIPLINES.entrySet()) {
            fetch(client, "/fina/events/" + e.getValue(), "data/" + e.getKey() + ".json");
            Thread.sleep(500);
        }
        System.out.println("全部完成");
    }

    static void fetch(HttpClient client, String path, String outFile) throws Exception {
        // 1. 拼 URL
        String url = BASE + path;
        // 2. 建请求
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).header("User-Agent", "DWASearch-learning/1.0").GET().build();
        // 3. 发请求
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        // 4. 检查状态码，不是 200 就抛
        if (resp.statusCode() != 200) {
            throw new java.io.IOException("HTTP" + resp.statusCode() + "<-" + url);
        }
        // 5. 建目录 + 写文件
        Files.createDirectories(Path.of("data"));
        Files.writeString(Path.of(outFile), resp.body(), StandardCharsets.UTF_8);
        // 6. 打印确认
        System.out.println(outFile + " " + resp.body().getBytes(StandardCharsets.UTF_8).length + "bytes");
    }
}
