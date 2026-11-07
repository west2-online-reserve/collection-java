package scraper.get;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class WebScraper {

    public static HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    // 获取原始json数据
    public String get_json(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:159.0) Gecko/20100101 Firefox/159.0")
                .header("Referer", "https://www.worldaquatics.com/")
                .header("Origin", "https://www.worldaquatics.com/")
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
