package com.dwa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
//定义一个爬虫公共类
public class Crawler {
    public static void main(String[] args) {
        // 1. 8个项目的URL存进数组
        String[] eventUrls = {
            "https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82", // women 3m springboard
            "https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1", // women 10m platform
            "https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d", // women 3m synchronised
            "https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b", // women 10m synchronised
            "https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7", // men 3m springboard
            "https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48", // men 10m platform
            "https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c", // men 3m synchronised
            "https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5"  // men 10m synchronised
        };
        ObjectMapper mapper = new ObjectMapper();//实例化
        ArrayNode allPlayers = mapper.createArrayNode();
        ArrayNode allResults = mapper.createArrayNode();
        HttpClient client = HttpClient.newHttpClient();
        // 2. 循环抓取每一个URL
        for (String url : eventUrls) {
            try {
                System.out.println("正在抓取: " + url);
                //构建器模式
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))//设置请求的地址
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")//添加请求头
                        .GET()//设置请求方法为GET
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());//同步请求，将字节流转化成字符串
                if (response.statusCode() != 200) {//200状态码代表成功，非则失败
                    System.err.println("抓取失败，状态码: " + response.statusCode());//System.err用红色字体打印错误日志
                    continue;
                }

                JsonNode root = mapper.readTree(response.body());//将文本转成一个树模型
                // 3. 转换项目名称的格式
                String rawName = root.path("DisciplineName").asText();//用path不能用get，get会返回null
                String eventName = rawName.toLowerCase().replace("'s", "").replace("'", "").trim();
                // 4. 找到Finals轮次
                JsonNode finalsResults = null;
                for (JsonNode heat : root.path("Heats")) {
                    if ("Finals".equals(heat.path("Name").asText())) {
                        finalsResults = heat.path("Results");
                        break;
                    }
                }
                if (finalsResults == null) {
                    System.err.println("未找到 Finals 数据: " + url);
                    continue;
                }

                // 5. 遍历该项目的所有参赛者
                for (JsonNode r : finalsResults) {
                    String fullName = r.path("FullName").asText();
                    String lastName = r.path("LastName").asText();
                    int rank = r.path("Rank").asInt();
                    String nat = r.path("NAT").asText();
                    int gender = r.path("Gender").asInt(); // 1 为女子，2 为男子

                    // 6. 判断是单人还是双人项目
                    JsonNode competitors = r.path("Competitors");
                    boolean isSynchronised = competitors.isArray() && competitors.size() > 0;//先确保它是真数组，再确保数组里有人

                    if (isSynchronised) {
                        // 双人项目：for-each提取两个选手信息，分别加入players列表
                        for (JsonNode comp : competitors) {
                            ObjectNode playerNode = mapper.createObjectNode();//用Jackson的ObjectNode创建一个空的JSON对象来记录选手
                            playerNode.put("fullName", comp.path("FullName").asText());//put就是往JSON对象里塞键值对
                            playerNode.put("gender", gender == 1 ? "Female" : "Male");
                            playerNode.put("country", comp.path("NAT").asText());
                            playerNode.put("lastName", comp.path("LastName").asText());
                            allPlayers.add(playerNode);
                        }
                    } else {
                        // 单人项目：直接提取选手信息
                        ObjectNode playerNode = mapper.createObjectNode();
                        playerNode.put("fullName", fullName);
                        playerNode.put("gender", gender == 1 ? "Female" : "Male");
                        playerNode.put("country", nat);
                        playerNode.put("lastName", lastName);
                        allPlayers.add(playerNode);
                    }
                    // 7. 无论单人还是双人，都往results列表加一条记录
                    ObjectNode resultNode = mapper.createObjectNode();//创建一个空的JSON对象来记录选手成绩
                    resultNode.put("event", eventName);
                    resultNode.put("rank", rank);
                    resultNode.put("diverName", fullName);
                    
                    ArrayNode scoresArray = mapper.createArrayNode();//创建一个空的JSON数组来记录单跳成绩
                    for (JsonNode dive : r.path("Dives")) {
                        scoresArray.add(Double.parseDouble(dive.path("DivePoints").asText()));
                    }
                    resultNode.set("scores", scoresArray);//用set将该数组封装进resultNode的scores字段
                    resultNode.put("totalScore", Double.parseDouble(r.path("TotalPoints").asText()));
                    allResults.add(resultNode);
                }
                System.out.println("成功抓取: " + eventName + "，记录数: " + finalsResults.size());
            } catch (Exception e) {
                System.err.println("抓取 " + url + " 发生异常: " + e.getMessage());//反映具体URL的报错，不影响其他URL爬取
            }
        }
        // 8. 写入data.json
        try {
            ObjectNode finalData = mapper.createObjectNode();
            finalData.set("players", allPlayers);
            finalData.set("results", allResults);
            String finalJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalData);//writerWithDefaultPrettyPrinter使用默认的美化打印器 使SON文件可读
            Files.writeString(Paths.get("data.json"), finalJson);//直接把字符串写入文件
            System.out.println("========= 全部抓取完成，data.json已更新=========");
        } catch (Exception e) {
            System.err.println("写入 data.json 失败: " + e.getMessage());
        }
    }
}