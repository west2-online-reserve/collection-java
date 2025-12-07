import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    // 发送HTTP GET请求并获取相应内容
    public static String sendHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // User-Agent: 标识客户端类型和版本
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        // Accept: 声明客户端可接受的响应内容类型
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        // Accept-Language: 声明客户端可接受的语言
        httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        // Referer: 标识请求的来源页面
        httpGet.addHeader("Referer", url);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new IOException("Http request failed with status code: " + statusCode);
            }
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } finally {
            httpClient.close();
        }
    }

    // 根据标签名和属性从HTML中提取特定内容
    public static String extractContentFromHtml(String html, String tag, String attribute, String value) {
        String regex = "<" + tag + "[^>]*" + attribute + "=[\\\"']" + value + "[\\\"'][^>]*>(.*?)</" + tag + ">";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // 从HTML中提取器所有匹配正则表达式的内容
    public static List<String> extractAllMatches(String html, String regex) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            matches.add(matcher.group(1));
        }
        return matches;
    }

    // 清理HtML内容，移除所有HTML标签并规范化文本格式
    public static String cleanHtmlTags(String html) {
        if (html == null) {
            return null;
        }

        // 移除所有HTML标签：<[^>]*> 匹配<开头，>结尾的所有内容
        String text = html.replaceAll("<[^>]*>", "");
        // 移除多余的空格和换行符：\s+ 匹配一个或多个空白字符
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }

    // 将字符串内容保存到指定文件
    public static void saveToFile(String content, String filePath) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8)){
            writer.write(content);
        }
    }

    // 从指定文件读取内容
    public static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))){
            String line;
            // 逐行读取文件内容
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // 对URL编码的字符串进行编码
    public static String urlDecode(String encodedString) {
        try {
            return URLDecoder.decode(encodedString, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // 将分数列表格式化为易读的字符串
    public static String formatScoreString(List<Double> scores, double totalScore) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.size(); i++) {
            if (i > 0) {
                sb.append(" + "); // 为非第一个分数添加分隔符
            }
            // 保留两位小数，提高可读性
            sb.append(String.format("%.2f", scores.get(i)));
        }
        sb.append(" = ").append(String.format("%.2f", totalScore));
        return sb.toString();
    }

    // 将分数字符串解析为Double类型的列表
    public static List<Double> parseScores(String scoreString) {
        List<Double> scores = new ArrayList<>();
        String[] parts = scoreString.split("\\s+");
        for (String part : parts) {
            try {
                scores.add(Double.parseDouble(part));
            } catch (NumberFormatException e) {
                // 忽略无法解析的部分
            }
        }
        return scores;
    }

    // 计算分数列表的总分
    public static double calculateTotalScore(List<Double> scores) {
        double total = 0.0;
        for (Double score : scores) {
            total += score;
        }
        return total;
    }

    // 验证比赛项目名称是否合法
    public static boolean isValidEventName(String eventName) {
        List<String> validEvents = Arrays.asList(
                "women 1m springboard",     // 女子1米跳板
                "women 3m springboard",     // 女子3米跳板
                "women 10m platform",       // 女子10米跳台
                "women 3m synchronised",    // 女子双人3米跳板
                "women 10m synchronised",   // 女子双人10米跳台
                "men 1m springboard",       // 男子1米跳板
                "men 3m springboard",       // 男子3米跳板
                "men 10m platform",         // 男子10米跳台
                "men 3m synchronised",      // 男子双人3米跳板
                "men 10m synchronised"      // 男子双人10米跳台
        );
        return validEvents.contains(eventName);
    }
}
