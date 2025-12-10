package crawler.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import crawler.DataCrawler;
import crawler.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerInfoParser implements Parser{
    public static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String parse(String rawData) {
        List<Player> players=new ArrayList<>();

        JsonNode jsonArr;
        try {
            jsonArr = objectMapper.readTree(rawData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for(JsonNode countryNode: jsonArr){
            String countryName = countryNode.path("CountryName").asText();
            JsonNode participators = countryNode.path("Participations");
            for(JsonNode participatorNode: participators){
                Player player=new Player();
                player.setName(participatorNode.path("PreferredLastName").asText()+" "+participatorNode.path("PreferredFirstName").asText());
                player.setCountry(countryName);
                player.setGender(participatorNode.path("Gender").asInt()==0?"Male":"Female");

                players.add(player);
            }
        }

        players.sort(Comparator.comparing(Player::getCountry).thenComparing(Player::getName));

        int estimatedSize = players.size() * 100;
        StringBuilder sB=new StringBuilder(estimatedSize);
        for(Player player: players){
            sB.append("Full Name:");
            sB.append(player.getName());
            sB.append("\nGender:");
            sB.append(player.getGender());
            sB.append("\nCountry:");
            sB.append(player.getCountry());
            sB.append(DataCrawler.SEPARATOR);
        }
        return sB.toString();
    }
}
