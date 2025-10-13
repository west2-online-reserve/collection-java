import Constant.DivingEventConstants;
import Constant.FileConstants;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Lib {
    /*
        作用： 输出运动员信息到dest中
        首先用ResourceUtil的readFile读取player的json数据，利用json工具解析得到每个player并将其封装
     */
    public static void outputPlayers(String dest) {
        String playerData = ResourceUtil.readFile(FileConstants.PALYER_JSON_FILEPATH);

        ArrayList<Player> players = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        JSONArray countryArray = JSON.parseArray(playerData);
        for (int i = 0; i < countryArray.size(); i++) {
            JSONObject countryObject = countryArray.getJSONObject(i);
            String countryName = countryObject.getString("CountryName");
            JSONArray participations = countryObject.getJSONArray("Participations");
            for (int j = 0; j < participations.size(); j++) {
                JSONObject participation = participations.getJSONObject(j);
                int temp = participation.getInteger("Gender");
                String gender = temp == 0 ? "Male" : "Female";
                String lastName = participation.getString("PreferredLastName");
                String firstName = participation.getString("PreferredFirstName");
                Player player = new Player(lastName + " " + firstName, gender, countryName);
                players.add(player);
            }
        }

        players.sort(Player::compareTo);

        for (Player player : players) {
            stringBuilder.append(player.toString());
            stringBuilder.append(FileConstants.DELIMITER);
        }
        Lib.outPrint(stringBuilder.toString(), dest);
    }

    /*
        作用： 将比赛结果输出到dest中
     */
    public static void outputFinalResults(String fileName, String dest) {
        String s = ResourceUtil.readFile(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray heats = jsonObject.getJSONArray("Heats");
        for (int i = 0; i < heats.size(); i++) {
            JSONObject heat = heats.getJSONObject(i);
            JSONArray results = heat.getJSONArray("Results");
            for (int j = 0; j < results.size(); j++) {
                JSONObject result = results.getJSONObject(j);
                String fullName = result.getString("FullName");
                int rank = result.getInteger("Rank");
                String totalPoints = result.getString("TotalPoints");
                JSONArray judgesScores = result.getJSONArray("Dives");
                int a = judgesScores.size();
                stringBuilder.append("FullName:")
                        .append(fullName)
                        .append("\nRank:")
                        .append(rank)
                        .append("\nScore:");
                for (int k = 0; k < a; k++) {
                    JSONObject judge = judgesScores.getJSONObject(k);
                    String score = judge.getString("DivePoints");
                    stringBuilder.append(score);
                    if (k != judgesScores.size() - 1)
                        stringBuilder.append(" + ");
                }
                stringBuilder.append(" = " + totalPoints);
                stringBuilder.append(FileConstants.DELIMITER);
            }
        }
        outPrint(stringBuilder.toString(), dest);
    }

    public static void outputFinalResultsDetail(String fileName, String dest) {
        ArrayList<Result> resultArrayList = new ArrayList<>();
        String s = ResourceUtil.readFile(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray heats = jsonObject.getJSONArray("Heats");
        for (int i = heats.size() - 1; i >= 0; i--) {
            String[] rankList = new String[3];
            JSONObject heat = heats.getJSONObject(i);
            JSONArray results = heat.getJSONArray("Results");
            String phaseName = heat.getString("PhaseName");
            int k = -1;
            if (phaseName.equals("Finals")) {
                k = 2;
            } else if (phaseName.equals("Semifinals")) {
                k = 1;
            } else if (phaseName.equals("Preliminaries")) {
                k = 0;
            }
            for (int j = 0; j < results.size(); j++) {
                JSONObject result = results.getJSONObject(j);
                String fullName = (String) result.get("FullName");
                int i1 = fullName.lastIndexOf('/');
                if (i1 != -1) {
                    String fullName1 = fullName.substring(i1 + 1).split(" ")[1];
                    String fullName2 = fullName.substring(0, i1).split(" ")[1];
                    int rrrr = fullName.compareTo(fullName2);
                    if (rrrr < 0) {
                        fullName = fullName.substring(i1 + 1) + " & " + fullName.substring(0, i1);
                    } else if (rrrr > 0) {
                        fullName = fullName.substring(0, i1) + " & " + fullName.substring(i1 + 1);
                    } else {
                        fullName = fullName.substring(i1 + 1) + " & " + fullName.substring(0, i1);
                    }
                }
                Integer rank = result.getInteger("Rank");
                String totalPoints = result.getString("TotalPoints");
                JSONArray judgesScores = result.getJSONArray("Dives");
                int a = judgesScores.size();
                String score = "";
                for (int r = 0; r < a; r++) {
                    JSONObject judge = judgesScores.getJSONObject(r);
                    score = score + judge.getString("DivePoints");
                    if (r != judgesScores.size() - 1)
                        score += " + ";
                }
                score += (" = " + totalPoints);
                int flag = 0;
                for (int b = 0; b < resultArrayList.size(); b++) {
                    if (resultArrayList.get(b).getName().equals(fullName)) {
                        resultArrayList.get(b).setScoreByNum(score, k);
                        resultArrayList.get(b).setRankByNum(rank.toString(), k);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    String[] ss1 = {"*", "*", "*"};
                    ss1[k] = score;
                    String[] ss2 = {"*", "*", "*"};
                    ss2[k] = rank.toString();
                    Result result1 = new Result(fullName, ss2, ss1);
                    resultArrayList.add(result1);
                }
            }
        }
        for (Result result : resultArrayList) {
            stringBuilder.append(result.toString());
        }
        outPrint(stringBuilder.toString(), dest);
    }

    /*
        封装一个方法将数据写入dest
     */
    public static void outPrint(String s, String dest, boolean isAppend) {
        try (FileWriter fileWriter = new FileWriter(dest, StandardCharsets.UTF_8, isAppend);) {
            fileWriter.write(s);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        默认追加
     */
    public static void outPrint(String s, String dest) {
        outPrint(s, dest, true);
    }

    public static void processArgs(String[] args) {
        if (args.length != 2) {
            throw new ArgumentsException(FileConstants.ARGS_ERROR);
        }

        File file = new File(args[0]);

        for (String arg : args) {
            if (!arg.endsWith(".txt")) {
                throw new ArgumentsException(FileConstants.SUFFIX_ERROR);
            }
        }

        if (!file.exists()) {
            throw new ArgumentsException(FileConstants.INPUT_FILE_NOT_EXIST);
        }

        Path outputArgPath = Paths.get(args[1]);
        String dest;
        if (outputArgPath.isAbsolute()) {
            dest = args[1];
        } else {
            dest = DivingEventConstants.OUTPUT_PATH + args[1];
        }

        if (!Files.exists(outputArgPath)) {
            System.out.println("即将创建新文件" + dest);
        } else {
            System.out.println("即将覆盖文件" + dest);
            System.out.println("如果想覆盖文件请输入1，追加输入其他");
            String s = new Scanner(System.in).nextLine();
            if (s.equals("1")) {
                outPrint("", dest, false);
            }

        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {

            String content;
            while ((content = bufferedReader.readLine()) != null) {

                if (content.equals("players")) {
                    Lib.outputPlayers(dest);
                    continue;
                }

                if (!content.startsWith("result ")) {
                    Lib.outPrint(FileConstants.String_ERROR + FileConstants.DELIMITER, dest);
                    continue;
                }
                String subContent = content.substring("result ".length());
                if (subContent.endsWith(" detail")) {
                    subContent = subContent.substring(0, subContent.length() - " detail".length());
                }
                if (subContent.isEmpty()) {
                    Lib.outPrint(FileConstants.String_Wrong + FileConstants.DELIMITER, dest);
                    continue;
                }

                int commandType = content.endsWith(" detail") ? FileConstants.RESULT_DETAIL : FileConstants.RESULT;
                boolean eventExists = Lib.findEvents(subContent, dest, commandType);

                if (!eventExists) {
                    Lib.outPrint(FileConstants.String_Wrong + FileConstants.DELIMITER, dest);
                }
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean findEvents(String subContent, String dest, int num) {
        for (int i = 0; i < DivingEventConstants.ALL_DIVING_EVENTS.length; i++) {
            String s = DivingEventConstants.ALL_DIVING_EVENTS[i];
            if (subContent.equals(s)) {
                if (num == FileConstants.RESULT_DETAIL) {
                    Lib.outputFinalResultsDetail(DivingEventConstants.FILE_NAMES[i], dest);
                } else {
                    Lib.outputFinalResults(DivingEventConstants.FILE_NAMES[i], dest);
                }

                return true;
            }
        }
        return false;
    }
}
