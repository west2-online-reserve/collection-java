package org.example.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pojo.City;

public class ToCity {
    public City toCity() throws IOException {
        System.out.println("请输入待查寻的城市名（如浦城）：");
        Scanner scanner=new Scanner(System.in);
        String location=scanner.nextLine();
        System.out.println(location);
        OkHttpApi okHttpApi=new OkHttpApi();
        String location1= URLEncoder.encode(location,"UTF-8");
        String url="https://geoapi.qweather.com/v2/city/lookup?key=29f50da3987a40d0bc6298fd74e9a454&location="+location1;
        String run=okHttpApi.run(url);
        Integer code;
        String name;
        Integer id;
        Double lat;
        Double lon;
        String adm2;//市
        String adm1;//省
        String country;
        String utcOffset;
        String type;
        Integer dengji;
        System.out.println(run);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(run);
            name = root.get("location").get(0).get("name").asText();
            id = root.get("location").get(0).get("id").asInt();
            lat = root.get("location").get(0).get("lat").asDouble();
            lon = root.get("location").get(0).get("lon").asDouble();
            adm2 = root.get("location").get(0).get("adm2").asText();
            adm1 = root.get("location").get(0).get("adm1").asText();
            country = root.get("location").get(0).get("country").asText();
            type = root.get("location").get(0).get("type").asText();
            dengji = root.get("location").get(0).get("rank").asInt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new City(name,id,lat,lon,adm2,adm1,country,type,dengji);


    }
}
