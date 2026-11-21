package com.dwasearch.crawler;

import com.dwasearch.crawler.model.*;
import com.dwasearch.model.DataSnapshot;
import com.dwasearch.model.players.Country;
import com.dwasearch.model.results.Competition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//运行指令：java -cp target/DWASearch.jar com.dwasearch.crawler.DataCrawler
public class DataCrawler {
    public static void main(String[] args) throws Exception {
        final Path outputPath = Path.of("data.json");
        final String eventsURL = "https://api.worldaquatics.com/fina/competitions/5019/events";
        final String playersURL = "https://api.worldaquatics.com/fina/competitions/5019/athletes";

        //创建DataSnapshot, Source对象，准备保存数据快照的比赛名称、时间等信息，为后面的序列化做准备
        DataSnapshot dataSnapshot = new DataSnapshot();
        Source source = new Source();
        source.setEventsURL(eventsURL);
        source.setPlayersURL(playersURL);

        //先向该api发送请求，api将返回含有所有需要的events的基本信息的json
        String eventsURI = source.getEventsURL();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(eventsURI))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //填充Source
        if (response.statusCode() == 200){
            source.setResultsFetchedAt(Instant.now());
            System.out.println(response.statusCode() + ": Get events successfully");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            source.setEvent(root.path("OfficialName").asText(""));
        }
        else {
            System.err.println(response.statusCode() + ": Get events failed");
        }

        //解析获取到的json并保存其中特定的id字段，这些字段将直接用于具体results的api请求，
        List<String> idList = getEventsIdList(response.body());
        String resultsBaseURI =  "https://api.worldaquatics.com/fina/events";

        //测试：尝试请求players的api并输出到文件
        Thread.sleep(500);
        String playersURI = source.getPlayersURL();
        request = HttpRequest.newBuilder()
                .uri(new URI(playersURI))
                .GET()
                .build();
        HttpResponse<String> playersResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (playersResponse.statusCode() == 200){
            source.setPlayersFetchedAt(Instant.now());
            System.out.println(playersResponse.statusCode() + ": Get players successfully");
        }
        else {
            System.err.println(playersResponse.statusCode() + ": Get players failed");
        }

        //解析players
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        List<Country> countries = null;
        if (playersResponse.statusCode() == 200) {
            countries = mapper.readValue(playersResponse.body(), new TypeReference<List<Country>>(){});
        }


        //请求各比赛结果数据并解析
        Thread.sleep(500);
        List<Competition> competitions = new ArrayList<>();
        if (!idList.isEmpty()){
            for (String id : idList){
                request = HttpRequest.newBuilder()
                        .uri(new URI(resultsBaseURI + "/" + id))
                        .GET()
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Competition competition = mapper.readValue(response.body(), Competition.class);
                if (response.statusCode() == 200 && competition != null){
                    competitions.add(competition);
                    System.out.println(response.statusCode() + ": " + competition.getDisciplineName());
                }
                else{
                    System.err.println(response.statusCode());
                }
                Thread.sleep(500);
            }
        }
        else {
            System.err.println("idList为空，无法通过events的id获取results信息");
        }

        //初始化数据快照并输出到文件data.json
        dataSnapshot.setSource(source);
        dataSnapshot.setCountries(countries);
        dataSnapshot.setCompetitions(competitions);
        mapper.writeValue(outputPath.toFile(), dataSnapshot);
    }
    private static List<String> getEventsIdList(String json){
        ObjectMapper mapper = new ObjectMapper();
        Events events = null;
        try {
            events = mapper.readValue(json, Events.class);
        }catch (JsonProcessingException e){
            System.err.println("events解析失败：" + e.getMessage());
        }
        List<String> disciplineIdList = new ArrayList<>();
        if (events != null){
            for (Sports sports : events.getSports()) {
                if (sports != null) {
                    for (Discipline discipline : sports.getDisciplineList()) {
                        disciplineIdList.add(discipline.getId());
                    }
                }
            }
        }
        return disciplineIdList;
    }
}
