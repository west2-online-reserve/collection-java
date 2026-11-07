import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Crawler {

    private static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/155.0.0.0 Safari/537.36 Edg/155.0.0.0";
    public static void main(String[] args) throws IOException, InterruptedException {
        Crawler c = new Crawler();
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        // 只抓两个列表接口；单项目详情需要 eventId，在下面按 targets 逐个拼 URL
        String[] urls = {"https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=",
                "https://api.worldaquatics.com/fina/competitions/5019/events"};

        String[] files = { "athletes.json", "events.json" };

        for (int i = 0;i < urls.length;i++){
            c.saveResponse(urls[i], client, files[i]);
            Thread.sleep(500);
        }

        // 读取目录
        String events = Files.readString(Path.of("events.json"), StandardCharsets.UTF_8);
        String[][] targets = {
                {"Women","002"}, {"Women","003"}, {"Women","202"}, {"Women","203"},
                {"Men","002"},   {"Men","003"},   {"Men","202"},   {"Men","203"}
        };

        for (String[] t : targets){
            String id = c.findID(events, t[0], t[1]);
            if (id == null) {
                System.out.println("未找到" + t[0] + " " + t[1]);
                continue;
            }
            // 拿到完整的url
            String url = "https://api.worldaquatics.com/fina/events/" + id;
            c.saveResponse(url, client, "event_" + t[0].toLowerCase() + "_"
                    + t[1] + ".json");
        }
    }


    public String findID(String events, String gender, String code){
        int from = 0;
        while (true){
            int g = events.indexOf("\"Gender\":\"" + gender + "\"", from);
            if (g < 0 ) return null; // 没有了

            int c = events.lastIndexOf("\"Code\":\"", g);   // 往前找code
            if (c >= 0){
                String found = events.substring(c + 8, c + 11); // 取三位code
                if (found.equals(code)){
                    int i = events.lastIndexOf("\"Id\":\"", c);
                    if (i >= 0)
                        return events.substring(i + 6, i + 6 + 36);
                }
            }
            from = g + 1;   // 跳过当前
        }
    }

    public void saveResponse(String url, HttpClient client, String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("user-agent", UA)
                .header("accept", "application/json, text/plain, */*")
                .header("referer", "https://www.worldaquatics.com/")
                .GET()
                .build();

        // 用byte接受，再手动转为string
        HttpResponse<byte[]> response = client.
                send(request, HttpResponse.BodyHandlers.ofByteArray());

        byte[] body1 = response.body();
        System.out.println("状态码：" + response.statusCode());
        System.out.println("字节数: " + body1.length);
        // 转为string
        String text1 = new String(body1, StandardCharsets.UTF_8);
        System.out.println("Content-Type: " + response.headers().firstValue("content-type").orElse(""));
        int code1 = response.statusCode();  // 状态码
        if (code1 == 200){
            Files.writeString(Path.of(json), text1, StandardCharsets.UTF_8);
            System.out.printf("已写入%s（%d）\n\n",json ,body1.length);
        }
        else {
            System.out.printf("错误代码为：%d\n\n", code1);
        }
    }
}


