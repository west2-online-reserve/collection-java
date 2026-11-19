import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonFetcher {
    // 保存文件名 -> 接口 URL
    private static final Map<String, String> ENDPOINTS = new LinkedHashMap<>();
    private static final int MAX_RETRIES = 3;                    // 失败最多重试 3 次
    private static final long DELAY_BETWEEN_REQUESTS_MS = 800;   // 请求间隔，控制频率

    static {
        ENDPOINTS.put("athletes.json",              "https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=");
        ENDPOINTS.put("men 3m springboard.json",    "https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7");
        ENDPOINTS.put("men 3m synchronised.json",   "https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c");
        ENDPOINTS.put("men 10m platform.json",      "https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48");
        ENDPOINTS.put("men 10m synchronised.json",  "https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5");
        ENDPOINTS.put("women 3m springboard.json",  "https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82");
        ENDPOINTS.put("women 3m synchronised.json", "https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d");
        ENDPOINTS.put("women 10m platform.json",    "https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1");
        ENDPOINTS.put("women 10m synchronised.json","https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b");
    }



    public static void main(String[] args) throws Exception {
        Path outDir = Path.of("src/data");
        Files.createDirectories(outDir);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        for (Map.Entry<String, String> e : ENDPOINTS.entrySet()) {
            String fileName = e.getKey();
            URI uri = URI.create(e.getValue());
            HttpResponse<String> resp = getWithRetry(client, uri);

            if (resp == null || resp.statusCode() != 200) {
                // 失败要说明原因，并返回非零退出码
                System.err.println("抓取失败: " + fileName + " -> " + uri);
                System.exit(1);
            }

            // 把原始响应原样写盘
            Files.writeString(outDir.resolve(fileName), resp.body(), StandardCharsets.UTF_8);
            System.out.println("已保存 " + fileName + " (" + resp.body().length() + " 字符)");

            // 控制请求频率，避免高频轮询
            Thread.sleep(DELAY_BETWEEN_REQUESTS_MS);
        }
        System.out.println("抓取完成，共 " + ENDPOINTS.size() + " 个文件。");
    }

    private static HttpResponse<String> getWithRetry(HttpClient client, URI uri) throws Exception {
        HttpRequest req = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() == 200) {
                    return resp;
                }
                System.err.println("HTTP " + resp.statusCode() + "（第 " + attempt + " 次）: " + uri);
            } catch (Exception ex) {
                System.err.println("请求异常（第 " + attempt + " 次）: " + ex.getMessage());
            }
            // 指数退避：第 1 次等 1s，第 2 次 2s，第 3 次 4s
            if (attempt < MAX_RETRIES) {
                Thread.sleep(1000L * (1L << (attempt - 1)));
            }
        }
        return null;
    }
}
