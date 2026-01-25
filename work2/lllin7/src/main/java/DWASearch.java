import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASearch {
    private static final Pattern PLAYERS_PATTERN = Pattern.compile("^players$");
    private static final Pattern RESULT_PATTERN = Pattern.compile(
            "^result " +
                    "((women|men) (1m springboard|3m (springboard|synchronised)|10m (platform|synchronised)))" +  // 细化项目分组
                    "( detail)?$"
    );

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        String inputPath = args[0];
        String outputPath = args[1];
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))){
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                processCommand(line, outputPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processCommand(String command, String outputPath) {
        Matcher playersMatcher = PLAYERS_PATTERN.matcher(command);
        Matcher resultMatcher = RESULT_PATTERN.matcher(command);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
            if (playersMatcher.matches()) {
                CoreModule.displayAllPlayersInfo(outputPath);
            } else if (resultMatcher.matches()) {
                String eventName = resultMatcher.group(1);
                boolean isDetail = command.endsWith("detail");
                CoreModule.displayResultsForEachEvent(outputPath, eventName, isDetail);
            } else if (command.startsWith("result")) {
                bw.write("N/A\n-----\n");
            } else {
                bw.write("Error\n-----\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
