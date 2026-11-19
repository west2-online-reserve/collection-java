import java.util.List;

public class PlayerFormatter {
    public static String formatterPlayer(List<Player> players){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            sb.append("Full Name:").append(players.get(i).getFullname())
                            .append("\nGender:").append(players.get(i).getGender())
                            .append("\nCountry:").append(players.get(i).getCountry())
                            .append("\n-----\n");
        }
        return sb.toString();
    }
}