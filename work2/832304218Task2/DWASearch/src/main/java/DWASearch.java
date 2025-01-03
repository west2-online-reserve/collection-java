
/*     */

import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import lib.FetchAthletes;
/*     */ import lib.GetText;
/*     */ import pojo.Player;
/*     */ import java.io.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import java.util.Comparator;
/*     */ import java.util.Scanner;
/*     */ import java.util.StringJoiner;
/*     */ import java.util.TreeSet;

/*     */
/*     */ public class DWASearch {
    /*     */
    public static void main(String[] args) throws IOException {
        /*  20 */
        InputStream inputStream = DWASearch.class.getClassLoader().getResourceAsStream("DWASearch.java");
        StringBuilder stringBuilder = new StringBuilder();
        /*  21 */
        Scanner scanner = new Scanner(System.in);
        /*  22 */
        String s0 = scanner.nextLine();
        /*  23 */
        String path = "src\\main\\java\\data\\" + s0;
        /*     */
        /*  25 */
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            /*     */
            String line;
            /*  27 */
            while ((line = reader.readLine()) != null) {
                /*  28 */
                stringBuilder.append(line);
                /*  29 */
                stringBuilder.append("\n");
                /*     */
            }
            /*  31 */
        } catch (IOException e) {
            /*  32 */
            e.printStackTrace();
            /*     */
        }
        /*  34 */
        String s2 = scanner.nextLine();
        /*  35 */
        String url = "src\\main\\java\\data\\" + s2;
        /*  36 */
        FileWriter fileWriter = new FileWriter(url, false);
        /*  37 */
        fileWriter.close();
        /*  38 */
        String search = stringBuilder.toString();
        /*  39 */
        divide(search, url);
        /*     */
    }

    /*     */
    /*     */
    public static void divide(String search, String outputURL) throws IOException {
        /*  43 */
        FileWriter fileWriter = new FileWriter(outputURL);
        /*  44 */
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        /*     */
        /*  46 */
        String[] require = search.split("\\n");
        /*  47 */
        for (String s : require) {
            /*  48 */
            if (s.matches("player.*")) {
                /*  49 */
                if ("players".equals(s)) {
                    /*  50 */
                    bufferedWriter.write(FetchAthletes.fetchAthletes("src\\main\\java\\data\\athletes.txt"));
                    /*     */
                } else {
                    /*  52 */
                    System.out.println("Error");
                    /*  53 */
                    bufferedWriter.write("Error");
                    /*     */
                }
                /*  55 */
            } else if (s.matches("result(.^\\s)*.*")) {
                /*  56 */
                if (s.matches("result\\s(women|men)\\s(([1|3]m\\sspringboard)|(10m\\splatform)|(3|10)m\\ssynchronised)")) {
                    /*  57 */
                    bufferedWriter.write(fetchOneEvents("src\\main\\java\\data\\" + s.substring(7) + ".txt"));
                    /*  58 */
                } else if (s.matches("result\\s(women|men)\\s(([1|3]m\\sspringboard)|(10m\\splatform)|(3|10)m\\ssynchronised)\\sdetail")) {
                    /*  59 */
                    bufferedWriter.write(fetchEveryEvents("src\\main\\java\\data\\" + s.substring(7, s.length() - 7) + ".txt"));
                    /*     */
                } else {
                    /*  61 */
                    System.out.println("N/A");
                    /*  62 */
                    bufferedWriter.write("N/A");
                    /*     */
                }
                /*     */
            } else {
                /*  65 */
                System.out.println("Error");
                /*  66 */
                bufferedWriter.write("Error");
                /*     */
            }
            /*     */
        }
        /*  69 */
        bufferedWriter.close();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public static String fetchOneEvents(String url) {
        /*  74 */
        String s0 = GetText.getText(url);
        /*  75 */
        JsonParser parser = new JsonParser();
        /*  76 */
        JsonObject jsonObject = parser.parse(s0).getAsJsonObject();
        /*  77 */
        JsonArray heats = jsonObject.get("Heats").getAsJsonArray();
        /*  78 */
        JsonObject asJsonObject = heats.get(0).getAsJsonObject();
        /*  79 */
        JsonArray results = asJsonObject.get("Results").getAsJsonArray();
        /*  80 */
        StringBuilder result = new StringBuilder();
        /*     */
        /*  82 */
        if ("null".equals(results.get(0).getAsJsonObject().get("Competitors").toString())) {
            /*     */
            /*  84 */
            for (int j = 0; j < results.size(); j++) {
                /*  85 */
                Player player = new Player();
                /*  86 */
                result.append(showSingle(results, true, j, player).finalShow());
                /*  87 */
                result.append("\r\n");
                /*  88 */
                result.append("-----");
                /*  89 */
                result.append("\r\n");
                /*     */
            }
            /*     */
        } else {
            /*     */
            /*  93 */
            result.append(showDouble(results, false));
            /*     */
        }
        /*     */
        /*  96 */
        return result.toString();
        /*     */
    }

    /*     */
    /*     */
    private static String showDouble(JsonArray results, boolean show) {
        /* 100 */
        StringBuilder result = new StringBuilder();
        /* 101 */
        for (int j = 0; j < results.size(); j++) {
            /* 102 */
            String names;
            JsonObject eachResult = results.get(j).getAsJsonObject();
            /* 103 */
            JsonArray dives = eachResult.get("Dives").getAsJsonArray();
            /* 104 */
            double[] scores = new double[dives.size()];
            /* 105 */
            for (int k = 0; k < dives.size(); k++) {
                /*     */
                /* 107 */
                JsonObject eachDive = dives.get(k).getAsJsonObject();
                /* 108 */
                scores[k] = eachDive.get("DivePoints").getAsDouble();
                /*     */
            }
            /*     */
            /* 111 */
            double totalPoints = eachResult.get("TotalPoints").getAsDouble();
            /*     */
            /* 113 */
            int rank = eachResult.get("Rank").getAsInt();
            /*     */
            /*     */
            /* 116 */
            JsonArray competitors = eachResult.get("Competitors").getAsJsonArray();
            /* 117 */
            JsonObject competitor1 = competitors.get(0).getAsJsonObject();
            /* 118 */
            JsonObject competitor2 = competitors.get(1).getAsJsonObject();
            /* 119 */
            String fullName1 = competitor1.get("FullName").getAsString();
            /* 120 */
            String fullName2 = competitor2.get("FullName").getAsString();
            /* 121 */
            String last1 = competitor1.get("LastName").toString();
            /* 122 */
            String last2 = competitor2.get("LastName").getAsString();
            /*     */
            /*     */
            /* 125 */
            if (last1.charAt(0) < last2.charAt(0)) {
                /* 126 */
                names = fullName1 + " & " + fullName2;
                /*     */
            } else {
                /* 128 */
                names = fullName2 + " & " + fullName1;
                /*     */
            }
            /* 130 */
            StringJoiner sj = new StringJoiner(" + ");
            /* 131 */
            for (double score : scores) {
                /* 132 */
                sj.add(String.valueOf(score));
                /*     */
            }
            /* 134 */
            if (show) {
                /* 135 */
                System.out.println("Full Name:" + names + "\r\nRank:* | * | " + rank + "\r\nFinalScore:" + sj
/*     */
/* 137 */.toString() + " = " + totalPoints);
                /* 138 */
                result.append("Full Name:").append(names).append("\r\n").append("Rank:* | * | ").append(rank).append("\r\n").append("FinalScore:").append(sj).append(" = ").append(totalPoints);
                /* 139 */
                result.append("\r\n");
                /* 140 */
                System.out.println("-----");
                /* 141 */
                result.append("-----");
                /* 142 */
                result.append("\r\n");
                /*     */
            } else {
                /* 144 */
                System.out.println("Full Name:" + names + "\r\nRank:" + rank + "\r\nScore:" + sj
/*     */
/* 146 */.toString() + " = " + totalPoints);
                /* 147 */
                String str = "Full Name:" + names + "\r\nRank:" + rank + "\r\nScore:" + sj + " = " + totalPoints;
                /*     */
                /*     */
                /* 150 */
                result.append(str);
                /* 151 */
                result.append("\r\n");
                /* 152 */
                System.out.println("-----");
                /* 153 */
                result.append("-----");
                /* 154 */
                result.append("\r\n");
                /*     */
            }
            /*     */
        }
        /*     */
        /*     */
        /* 159 */
        return result.toString();
        /*     */
    }

    /*     */
    /*     */
    private static Player showSingle(JsonArray results, boolean show, int j, Player player) {
        /* 163 */
        JsonObject eachResult = results.get(j).getAsJsonObject();
        /* 164 */
        JsonArray dives = eachResult.get("Dives").getAsJsonArray();
        /* 165 */
        for (int k = 0; k < dives.size(); k++) {
            /*     */
            /* 167 */
            JsonObject eachDive = dives.get(k).getAsJsonObject();
            /* 168 */
            player.getFinalScore()[k] = eachDive.get("DivePoints").getAsDouble();
            /*     */
        }
        /*     */
        /* 171 */
        double totalPoints = eachResult.get("TotalPoints").getAsDouble();
        /* 172 */
        String fullName = eachResult.get("FullName").toString();
        /* 173 */
        int rank = eachResult.get("Rank").getAsInt();
        /* 174 */
        player.setFullname(fullName);
        /* 175 */
        player.setRank(rank);
        /* 176 */
        player.getRankList().add(rank + "");
        /* 177 */
        player.setTotalScore(totalPoints);
        /* 178 */
        if (show) {
            /* 179 */
            System.out.println(player.finalShow());
            /* 180 */
            System.out.println("-----");
            /*     */
        }
        /*     */
        /* 183 */
        return player;
        /*     */
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    public static String fetchEveryEvents(String url) {
        /* 193 */
        StringBuilder result = new StringBuilder();
        /* 194 */
        String s0 = GetText.getText(url);
        /* 195 */
        JsonParser parser = new JsonParser();
        /* 196 */
        JsonObject jsonObject = parser.parse(s0).getAsJsonObject();
        /* 197 */
        JsonArray heats = jsonObject.get("Heats").getAsJsonArray();
        /*     */
        /* 199 */
        if (heats.size() == 2) {
            /*     */
            /* 201 */
            JsonObject asJsonObject = heats.get(1).getAsJsonObject();
            /* 202 */
            JsonArray results = asJsonObject.get("Results").getAsJsonArray();
            /*     */
            /* 204 */
            TreeSet<Player> players = new TreeSet<>(new Comparator<Player>()
                    /*     */ {
                /*     */
                @Override
                public int compare(Player o1, Player o2) {
                    /*     */
                    int i;
                    /* 208 */
                    if ("*".equals(o1.getRankList().get(0))) {
                        /* 209 */
                        if ("*".equals(o1.getRankList().get(1))) {
                            /* 210 */
                            i = Integer.parseInt(o1.getRankList().get(2)) - Integer.parseInt(o2.getRankList().get(2));
                            /*     */
                        } else {
                            /* 212 */
                            i = Integer.parseInt(o1.getRankList().get(1)) - Integer.parseInt(o2.getRankList().get(1));
                            /*     */
                        }
                        /*     */
                    } else {
                        /* 215 */
                        i = Integer.parseInt(o1.getRankList().get(0)) - Integer.parseInt(o2.getRankList().get(0));
                        /*     */
                    }
                    /* 217 */
                    return i;
                    /*     */
                }
                /*     */
            });
            /* 220 */
            for (int j = 0; j < results.size(); j++) {
                /* 221 */
                Player player = new Player();
                /* 222 */
                player = showSingle(results, false, j, player);
                /* 223 */
                player.setPreliminaryScore(player.getFinalScore());
                /* 224 */
                player.setTotalPScore(player.getTotalScore());
                /*     */
                /* 226 */
                players.add(player);
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /* 231 */
            players.forEach(player -> player.getRankList().add("*"));
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /* 237 */
            JsonObject asJsonObject2 = heats.get(0).getAsJsonObject();
            /* 238 */
            JsonArray results2 = asJsonObject2.get("Results").getAsJsonArray();
            /* 239 */
            for (int i = 0; i < results2.size(); i++) {
                /* 240 */
                for (Player player : players) {
                    /* 241 */
                    if (results2.get(i).getAsJsonObject().get("FullName").toString().equals(player.getFullname())) {
                        /* 242 */
                        Player temp = new Player();
                        /* 243 */
                        temp = showSingle(results2, false, i, temp);
                        /* 244 */
                        player.setFinalScore(temp.getFinalScore());
                        /* 245 */
                        player.setTotalScore(temp.getTotalScore());
                        /* 246 */
                        player.getRankList().add(temp.getRank() + "");
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /*     */
            /*     */
            /* 252 */
            players.forEach(Player::detailShow);
            /* 253 */
            players.forEach(player -> {
                /*     */
                result.append(player.detailShow());
                /*     */
                /*     */
                /*     */
                result.append("\r\n");
                /*     */
            });
            /* 259 */
        } else if (heats.size() == 3) {
            /*     */
            /* 261 */
            JsonObject asJsonObject = heats.get(2).getAsJsonObject();
            /* 262 */
            JsonArray results = asJsonObject.get("Results").getAsJsonArray();
            /*     */
            /* 264 */
            TreeSet<Player> players = new TreeSet<>(new Comparator<Player>()
                    /*     */ {
                /*     */
                @Override
                public int compare(Player o1, Player o2) {
                    /*     */
                    int i;
                    /* 268 */
                    if ("*".equals(o1.getRankList().get(0))) {
                        /* 269 */
                        if ("*".equals(o1.getRankList().get(1))) {
                            /* 270 */
                            i = Integer.parseInt(o1.getRankList().get(2)) - Integer.parseInt(o2.getRankList().get(2));
                            /*     */
                        } else {
                            /* 272 */
                            i = Integer.parseInt(o1.getRankList().get(1)) - Integer.parseInt(o2.getRankList().get(1));
                            /*     */
                        }
                        /*     */
                    } else {
                        /* 275 */
                        i = Integer.parseInt(o1.getRankList().get(0)) - Integer.parseInt(o2.getRankList().get(0));
                        /*     */
                    }
                    /* 277 */
                    return i;
                    /*     */
                }
                /*     */
            });
            /* 280 */
            for (int j = 0; j < results.size(); j++) {
                /* 281 */
                Player player = new Player();
                /* 282 */
                player = showSingle(results, false, j, player);
                /* 283 */
                player.setPreliminaryScore(player.getFinalScore());
                /* 284 */
                player.setTotalPScore(player.getTotalScore());
                /* 285 */
                player.setFinalScore(new double[10]);
                /* 286 */
                players.add(player);
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /* 291 */
            JsonObject asJsonObject2 = heats.get(1).getAsJsonObject();
            /* 292 */
            JsonArray results2 = asJsonObject2.get("Results").getAsJsonArray();
            /* 293 */
            for (int i = 0; i < results2.size(); i++) {
                /* 294 */
                for (Player player : players) {
                    /* 295 */
                    if (results2.get(i).getAsJsonObject().get("FullName").toString().equals(player.getFullname())) {
                        /* 296 */
                        Player temp = new Player();
                        /* 297 */
                        temp = showSingle(results2, false, i, temp);
                        /* 298 */
                        player.setSemifinalScore(temp.getFinalScore());
                        /* 299 */
                        player.setTotalSScore(temp.getTotalScore());
                        /* 300 */
                        player.getRankList().add(temp.getRank() + "");
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /* 307 */
            JsonObject asJsonObject3 = heats.get(0).getAsJsonObject();
            /* 308 */
            JsonArray results3 = asJsonObject3.get("Results").getAsJsonArray();
            /* 309 */
            for (int k = 0; k < results3.size(); k++) {
                /* 310 */
                for (Player player : players) {
                    /* 311 */
                    if (results3.get(k).getAsJsonObject().get("FullName").toString().equals(player.getFullname())) {
                        /* 312 */
                        Player temp = new Player();
                        /* 313 */
                        temp = showSingle(results3, false, k, temp);
                        /* 314 */
                        player.setFinalScore(temp.getFinalScore());
                        /* 315 */
                        player.setTotalScore(temp.getTotalScore());
                        /* 316 */
                        player.getRankList().add(temp.getRank() + "");
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /*     */
            /* 321 */
            players.forEach(Player::detailShow);
            /* 322 */
            players.forEach(player -> result.append(player.detailShow()));
            /*     */
        } else {
            /* 324 */
            JsonObject asJsonObjectS = heats.get(0).getAsJsonObject();
            /* 325 */
            JsonArray resultsS = asJsonObjectS.get("Results").getAsJsonArray();
            /* 326 */
            result.append(showDouble(resultsS, true));
            /*     */
        }
        /*     */
        /* 329 */
        return result.toString();
        /*     */
    }
    /*     */
}


/* Location:              C:\Users\27705\Desktop\WestOnlineTask2-1.0-SNAPSHOT-jar-with-dependencies.jar!\com\westonline\DWASearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */