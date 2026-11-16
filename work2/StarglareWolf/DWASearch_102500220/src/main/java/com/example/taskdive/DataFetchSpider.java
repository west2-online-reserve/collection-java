package com.example.taskdive;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFetchSpider {
    public static void getData(){
        String[] urls = {
                "https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=",
                "https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82",
                "https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1",
                "https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d",
                "https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b",
                "https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7",
                "https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48",
                "https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c",
                "https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5",
                "https://api.worldaquatics.com/fina/events/f981457d-1f4a-4cc4-a53e-00f569878860"
        };


        String[] fileNames = {
                "src/main/resources/Athlete_Contest/Athlete/athletes.json",
                "src/main/resources/Athlete_Contest/Contest/Single/women3mSpringboard.json",
                "src/main/resources/Athlete_Contest/Contest/Single/women10mPlatform.json",
                "src/main/resources/Athlete_Contest/Contest/Group/women's3mSynchronised.json",
                "src/main/resources/Athlete_Contest/Contest/Group/women's10mSynchronised.json",
                "src/main/resources/Athlete_Contest/Contest/Single/men3mSpringboard.json",
                "src/main/resources/Athlete_Contest/Contest/Single/men10mPlatform.json",
                "src/main/resources/Athlete_Contest/Contest/Group/men's3mSynchronised.json",
                "src/main/resources/Athlete_Contest/Contest/Group/men's10mSynchronised.json",
                "src/main/resources/Athlete_Contest/Contest/Group/mixed3mand10mTeam.json"
        };

        // 创建一个抓取对象
        HttpClient client = HttpClient.newHttpClient();

        String fetchTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("开始抓取数据，实际抓取时间: " + fetchTime);
        for(int i = 0; i < urls.length; i++){
            System.out.println("正在抓取: " + fileNames[i] + " ...");
            try{
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urls[i]))
                        .header("User-Agent", "Mozilla/5.0")
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if(response.statusCode() == 200){
                    Path snapshotPath = Paths.get("snapshots/" + fileNames[i]);
                    Files.createDirectories(snapshotPath.getParent());   // 自动创建子文件夹
                    Files.writeString(snapshotPath, response.body());

                    Path savePath = Paths.get(fileNames[i]);
                    Files.createDirectories(savePath.getParent());
                    Files.writeString(savePath, response.body());
                    System.out.println("抓取成功" + fileNames[i]);
                }
                else{
                    System.out.println("抓取失败" + fileNames[i] + "状态码: " + response.statusCode());
                }

                // 防止请求过快让对方网站炸了或者被拦截
                Thread.sleep(500);
            }
            catch(Exception e){
                System.out.println("抓取异常" + e.getMessage());
            }
        }
        System.out.println("全部抓取完毕");
    }
}
