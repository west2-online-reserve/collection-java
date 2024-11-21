import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProcessContent {
    private static String outputFilePath = null;

    //接收input.txt的指令，转化为相对地址传给CoreModule
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("men 1m springboard", "src/data/info/men 1m springboard.txt");
        map.put("men 3m springboard", "src/data/info/men 3m springboard.txt");
        map.put("men 3m synchronised", "src/data/info/men 3m synchronised.txt");
        map.put("men 10m platform", "src/data/info/men 10m platform.txt");
        map.put("men 10m synchronised", "src/data/info/men 10m synchronised.txt");
        map.put("women 1m springboard", "src/data/info/women 1m springboard.txt");
        map.put("women 3m springboard", "src/data/info/women 3m springboard.txt");
        map.put("women 3m synchronised", "src/data/info/women 3m synchronised.txt");
        map.put("women 10m platform", "src/data/info/women 10m platform.txt");
        map.put("women 10m synchronised", "src/data/info/women 10m synchronised.txt");
    }

    public static void processContent(String order) throws IOException {
        order.trim();
        if (order.equals("players")) {
            CoreModule.displayAllPlayersInfo("src/data/info/allAthlete.txt");
        }else if (order.startsWith("result ")) {
            String temp = order.substring(7);
            if (temp.endsWith(" detail")) {
                temp = temp.substring(0, temp.length() - 7);//将" detail"截断
                if (map.containsKey(temp)) {
                    CoreModule.displayResultsForEachEvent(map.get(temp), true);
                }else{
                    CoreModule.displayNA();
                }
            } else {
                if (map.containsKey(temp)) {
                    CoreModule.displayResultsForEachEvent(map.get(temp), false);
                } else {
                    CoreModule.displayNA();//找不到对应比赛
                }
            }
        } else {
            CoreModule.displayError();//错误信息
        }
    }

    //清空output.txt的函数，保证开始读入input.txt前，output.txt为空
    public static void clear() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        writer.write("");
        writer.close();
    }

    public static void setOutputFilePath(String output) {
        outputFilePath = output;
        CoreModule.setOutputFilePath(outputFilePath);
    }
}
