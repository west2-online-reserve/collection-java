package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import pojo.Discipline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonUtils {

    public Discipline changeJson(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int code = response.getCode();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root=mapper.readTree(response.getEntity().getContent());

        Discipline discipline = new Discipline();   //new一个新的比赛类
        List<Discipline.player> players=new ArrayList<>();  //new新的参赛者列表

        String DisciplineName= root.get("DisciplineName").asText();
        discipline.setDisciplineName(DisciplineName);   //比赛项目名称


        JsonNode result=root.path("Heats").path(0).path("Results");

        for (JsonNode player : result) {

            String Point0=player.get("TotalPoints").asText();
            JsonNode dives=player.get("Dives");
            String totalPoint="";

            for (JsonNode dive : dives) {

                String point1=dive.get("DivePoints").asText();
                totalPoint=totalPoint+point1+"+";
            }

            totalPoint=totalPoint.substring(0,totalPoint.length()-1);
            totalPoint=totalPoint+"="+Point0;   //分数字符串组装完成

            String fullname=player.get("FullName").asText();
            String rank=player.get("Rank").asText();

            Discipline.player player1=new Discipline.player();
            player1.setRank(rank);
            player1.setFullName(fullname);
            player1.setScore(totalPoint);


            players.add(player1);

        }

        discipline.setPlayers(players);

        return discipline;

    }
}
