package dwasearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Query {

    public static final String LINE = "-----";

    private GameData data;

    public Query(GameData data) {
        this.data = data;
    }

    public String showPlayers() {

        ArrayList<Player> list = new ArrayList<Player>();
        for (int i = 0; i < data.players.size(); i++) {
            list.add(data.players.get(i));
        }

        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player a, Player b) {
                String c1 = a.country == null ? "" : a.country;
                String c2 = b.country == null ? "" : b.country;
                int r = c1.compareTo(c2);
                if (r != 0) {
                    return r;
                }
                r = a.getLastName().compareTo(b.getLastName());
                if (r != 0) {
                    return r;
                }
                return (a.fullName == null ? "" : a.fullName).compareTo(b.fullName == null ? "" : b.fullName);
            }
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            Player p = list.get(i);
            sb.append("Full Name:").append(p.fullName).append("\n");
            sb.append("Gender:").append(p.gender).append("\n");
            sb.append("Country:").append(p.country).append("\n");
            sb.append(LINE);
        }
        return sb.toString();
    }

    public String showResult(String eventName) {

        ArrayList<ResultItem> list = data.events.get(eventName);
        if (list == null) {
            return "N/A" + "\n" + LINE;
        }

        ArrayList<ResultItem> copy = new ArrayList<ResultItem>();
        for (int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }
        Collections.sort(copy, new Comparator<ResultItem>() {
            @Override
            public int compare(ResultItem a, ResultItem b) {
                return a.rank - b.rank;
            }
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < copy.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            ResultItem item = copy.get(i);
            sb.append("Full Name:").append(item.fullName).append("\n");
            sb.append("Rank:").append(item.rank).append("\n");
            sb.append("Score:");
            for (int j = 0; j < item.scores.size(); j++) {
                if (j > 0) {
                    sb.append(" + ");
                }
                sb.append(item.scores.get(j));
            }
            sb.append(" = ").append(item.totalPoints).append("\n");
            sb.append(LINE);
        }
        return sb.toString();
    }
}
