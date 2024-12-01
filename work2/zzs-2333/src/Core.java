import java.io.*;
import java.util.*;

public class Core {

    // 模拟选手数据
    static class Player {
        String fullName;
        String gender;
        String country;
        
        public Player(String fullName, String gender, String country) {
            this.fullName = fullName;
            this.gender = gender;
            this.country = country;
        }
        
        @Override
        public String toString() {
            return fullName + "\n" + gender + "\n" + country;
        }
    }

    // 模拟比赛结果数据
    static class Result {
        String fullName;
        String rank;
        String score;
        
        public Result(String fullName, String rank, String score) {
            this.fullName = fullName;
            this.rank = rank;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return fullName + "\n" + rank + "\n" + score;
        }
    }

    // 输出所有选手信息
    public static void outputPlayersInfo(List<Player> players, BufferedWriter writer) throws IOException {
        if (players == null || players.isEmpty()) {
            return;
        }
        players.sort(Comparator.comparing((Player p) -> p.country).thenComparing(p -> p.fullName.split(" ")[1])); // 排序
        
        for (Player player : players) {
            writer.write(player.fullName + "\n");
            writer.write(player.gender + "\n");
            writer.write(player.country + "\n");
            writer.write("-----\n");
        }
        writer.write("-----\n");
    }

    // 输出比赛结果
    public static void outputResult(String event, BufferedWriter writer) throws IOException {
        // 根据event来选择输出对应的比赛结果
        // 假设数据已经被处理并获取，示范为模拟数据
        List<Result> results = new ArrayList<>();
        results.add(new Result("John Doe", "1", "15.5 + 16.0 + 14.5 + 46.0"));
        results.add(new Result("Jane Smith", "2", "14.5 + 15.0 + 16.0 + 45.5"));
        
        for (Result result : results) {
            writer.write(result.fullName + "\n");
            writer.write(result.rank + "\n");
            writer.write(result.score + "\n");
            writer.write("-----\n");
        }
        writer.write("-----\n");
    }

    // 输出详细比赛结果
    public static void outputDetailedResult(String event, BufferedWriter writer) throws IOException {
        // 这里模拟输出详细结果数据
        Map<String, String[]> detailedResults = new HashMap<>();
        detailedResults.put("Jane Smith", new String[]{"* | * | 1", "*", "15.5 + 16.0 + 14.5 + 46.0"});
        detailedResults.put("John Doe", new String[]{"* | 2 | 2", "15.0 + 15.5 + 16.0 + 46.5", "*"});
        
        for (Map.Entry<String, String[]> entry : detailedResults.entrySet()) {
            writer.write(entry.getKey() + "\n");
            writer.write(entry.getValue()[0] + "\n");
            writer.write(entry.getValue()[1] + "\n");
            writer.write(entry.getValue()[2] + "\n");
            writer.write("-----\n");
        }
        writer.write("-----\n");
    }

    // 验证指令是否合法
    public static void processCommand(String command, BufferedWriter writer) throws IOException {
        String[] commandParts = command.split(" ", 2);
        if (commandParts[0].equals("players")) {
            // 模拟输出所有选手信息
            List<Player> players = Arrays.asList(
                new Player("John Doe", "Male", "USA"),
                new Player("Jane Smith", "Female", "USA"),
                new Player("Carlos Ruiz", "Male", "Spain")
            );
            outputPlayersInfo(players, writer);
        } else if (commandParts[0].equals("result")) {
            if (commandParts.length < 2) {
                writer.write("Error\n");
                return;
            }
            String event = commandParts[1];
            if (!isValidEvent(event)) {
                writer.write("Error\n");
                return;
            }
            if (commandParts.length == 2) {
                outputResult(event, writer);
            } else if (commandParts.length == 3 && commandParts[2].equals("detail")) {
                outputDetailedResult(event, writer);
            } else {
                writer.write("N/A\n");
            }
        } else {
            writer.write("Error\n");
        }
    }
    private static boolean isValidEvent(String event) {
        List<String> validEvents = Arrays.asList(
                "men 1m springboard",
                "men 3m springboard",
                "men 3m synchronised",
                "men 10m platform",
                "men 10m synchronised",
                "women 1m springboard",
                "women 3m springboard",
                "women 3m synchronised",
                "women 10m platform",
                "women 10m synchronised"
        );
        return validEvents.contains(event);
    }
}
