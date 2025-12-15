package org.example.lib;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class HttpsCrawler implements JSONFetch {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();


    //获取网页数据api信息
    @Override
    public String getJson(String url) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0")
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        return response.body();


    }


    @Override
    public <T> T fetch(String url, Class<T> tClass) throws IOException, InterruptedException {
        try {
            String jsonString = this.getJson(url);

            return mapper.readValue(jsonString, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> ArrayList<T> fetchArrayList(String url, Class<T> tClass) throws IOException, InterruptedException {

        try {
            String jsonString = this.getJson(url);

            return mapper.readValue(
                    jsonString,
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}


