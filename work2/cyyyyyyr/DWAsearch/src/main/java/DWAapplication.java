import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import pojo.Discipline;
import pojo.JoinCountry;
import utils.JacksonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DWAapplication {


    public static void main(String[] args) throws IOException {

        System.out.println("Hello World!");

        Map<String,Discipline> games=new HashMap<>();
        List<JoinCountry.Participations> players=new ArrayList<>();




        // 获取默认配置的 HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();




        //获取全部运动员url
        String url="https://api.worldaquatics.com/fina/competitions/5019/athletes?gender=&countryId=";

        //具体比赛项目url
        String url1="https://api.worldaquatics.com/fina/events/2b08da89-8232-4ffc-8df3-95865ba41b82";
        String url2="https://api.worldaquatics.com/fina/events/581a26af-8e97-427c-9adb-5fdd8f4fc9c1";
        String url3="https://api.worldaquatics.com/fina/events/61b483ec-4022-4555-9960-fba9cd1d702d";
        String url4="https://api.worldaquatics.com/fina/events/c3cca7c9-5ef7-4a69-a173-82fe75ffcc4b";
        String url5="https://api.worldaquatics.com/fina/events/4c18d241-ad14-436a-bd38-25dfb47f1cb7";
        String url6="https://api.worldaquatics.com/fina/events/450a9e84-5db0-40c4-a45c-4c3bb2392f48";
        String url7="https://api.worldaquatics.com/fina/events/08d17e1c-94c2-4c56-bf2b-603eeba24f8c";
        String url8="https://api.worldaquatics.com/fina/events/f86bd80d-a342-40f7-a521-90a50ad9d5c5";
        List<String> urls=List.of(url1,url2,url3,url4,url5,url6,url7,url8);


        // 创建 GET 请求对象
        HttpGet httpGet = new HttpGet(url);


// 调用 HttpClient 的 execute 方法执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

// 获取请求状态
        int code = response.getCode();


        //获取全部比赛项目及其信息，并存入map

        JacksonUtils utils=new JacksonUtils( );
        for (String tmpurl: urls) {

            Discipline game=utils.changeJson(tmpurl);
            games.put(game.getDisciplineName(),game);
        }



        //获取全部运动员

        try {

            String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();


            List<JoinCountry> joinCountry = objectMapper.readValue(result, new TypeReference<List<JoinCountry>>(){});

            for (JoinCountry joinCountry1 : joinCountry) {

                List<JoinCountry.Participations> list = joinCountry1.getParticipations();
                if (list != null) {
                    for (JoinCountry.Participations p : list) {
                        players.add(p);
                    }
                }

            }



        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }


        String filepath=args[1];
        System.out.println(args[0]);
        FileWriter fw = new FileWriter(filepath);
        List<String> lines = Files.readAllLines(Paths.get(args[0]));
        for (String line : lines) {
            if (line.trim().equals("players")){
                for (JoinCountry.Participations player : players) {
                    fw.write("Full name:"+player.getPreferredFirstName()+" "+player.getPreferredLastName()+"\n");
                    if (player.getGender().equals("1")) {fw.write("Gender:Female\n");}
                    else {fw.write("Gender:Male\n");}
                    fw.write("Country:"+player.getNAT()+"\n");
                    fw.write("------------------\n");
                }
            }
            else {
                if (line.length()<7) {

                    fw.write("Error\n");
                    fw.write("------------------\n");
                    continue;
                }
                String s1=line.substring(0,7);
                if (!s1.equals("result ")){
                    fw.write("Error\n");
                    fw.write("------------------\n");
                    continue;
                }
                String s2=line.substring(7);

                Discipline discipline = games.get(s2);
                if (discipline == null) {
                    fw.write("N/A\n");
                    fw.write("------------------\n");
                    continue;
                }
                else {
                            for (Discipline.player player: discipline.getPlayers()) {

                                fw.write("Full Name:"+player.getFullName()+"\n");
                                fw.write("Rank:"+player.getRank()+"\n");
                                fw.write("Score:"+player.getScore()+"\n");
                                fw.write("------------------\n");

                            }


                }


            }
        }


        fw.close();





    }



}
