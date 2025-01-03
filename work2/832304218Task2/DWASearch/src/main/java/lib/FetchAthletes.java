package lib;/*    */
/*    */
/*    */


/*    */ import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import com.google.gson.stream.JsonWriter;
import pojo.Player;
/*    */ import java.util.Comparator;
/*    */ import java.util.TreeSet;

/*    */
/*    */ public class FetchAthletes
        /*    */ {
    /*    */
    public static String fetchAthletes(String url) {
        /* 14 */
        StringBuilder result = new StringBuilder();
        /*    */
        /* 16 */
        String s0 = GetText.getText(url);
        /*    */
        /* 18 */
        JsonParser parser = new JsonParser();
        /* 19 */
        JsonArray jsonArray = parser.parse(s0).getAsJsonArray();
        /*    */
        /* 21 */
        for (int i = 0; i < jsonArray.size(); i++) {
            /*    */
            /* 23 */
            JsonArray participations = ((JsonObject) jsonArray.get(i)).get("Participations").getAsJsonArray();
            /*    */
            /*    */
            /*    */
            /* 27 */
            TreeSet<Player> players = new TreeSet<>(new Comparator<Player>()
                    /*    */ {
                @Override
                /*    */ public int compare(Player o1, Player o2) {
                    /* 30 */
                    int i = o1.getNAT().compareTo(o2.getNAT());
                    /* 31 */
                    if (i == 0) {
                        /* 32 */
                        i = o1.getPreferredLastName().compareTo(o2.getPreferredLastName());
                        /*    */
                    }
                    /* 34 */
                    return i;
                    /*    */
                }
                /*    */
            });
            /* 37 */
            for (int j = 0; j < participations.size(); j++) {
                /* 38 */
                String s = participations.get(j).toString();
                /* 39 */
                Player player = (Player) JSON.parseObject(s, Player.class);
                /* 40 */
                players.add(player);
                /*    */
            }
            /* 42 */
            players.forEach(player -> {
                /*    */
                System.out.println(player);
                /*    */
                /*    */
                result.append(player).append("\n");
                /*    */
                System.out.println("-----");
                /*    */
                result.append("-----").append("\n");
                /*    */
            });
            /*    */
        }
        /* 50 */
        return result.toString();
        /*    */
    }
    /*    */
}

