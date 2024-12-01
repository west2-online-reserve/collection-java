import lib.CoreModule;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DWASearch {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar ksx.jar input.txt output.txt");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        List<String> validEventNames = Arrays.asList(
                "women 1m springboard",
                "women 3m springboard",
                "women 10m platform",
                "women 3m synchronlsed",
                "women 10m synchronlsed",
                "men 1m springboard",
                "men 3m springboard",
                "men 10m platform",
                "men 3m synchronlsed",
                "men 10m synchronlsed"
        );

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String command;
            CoreModule coreModule = new CoreModule();

            while((command = reader.readLine()) !=null) {
                if (!command.equals(command.toLowerCase())) {
                    writer.write("Error\n-----\n");
                    continue;
                }
                if (command.startsWith("players")) {
                    if (command.trim().equals("players")) {
                        coreModule.displayAllPlayersInfo(writer);
                    } else {
                        writer.write("Error\n-----\n");
                    }
                }else if (command.startsWith("result ")) {
                    String remaining = command.substring(7); // 移除 'result' 并去除前后空格
                    if (remaining.isEmpty()) {
                        writer.write("Error\n-----\n");
                        continue;
                    }
                    // 检查是否包含 ' detail'
                    boolean hasDetail = false;
                    if (remaining.endsWith(" detail")) {
                        hasDetail = true;
                        remaining = remaining.substring(0, remaining.length() - 7); // 移除 ' detail'
                    }
                    String eventName = remaining;
                    // 不去除多余空格，直接比较
                    if (validEventNames.contains(eventName)) {
                        if (hasDetail) {
                            coreModule.displayDetailedResults(writer, eventName);
                        } else {
                            coreModule.displayResultsForEvent(writer, eventName);
                        }
                    } else {
                        writer.write("N/A\n-----\n");
                    }
                } else {
                    writer.write("Error\n-----\n");
                }
            }
//            while ((command = reader.readLine()) != null) {
//                switch (command) {
//                    case "players":
//                        coreModule.displayAllPlayersInfo(writer);
//                        break;
//                    case "result women 1m springboard":
//                        coreModule.displayResultsForEvent(writer, "Women 1m Springboard");
//                        break;
//                    case "result women 3m springboard":
//                        coreModule.displayResultsForEvent(writer, "Women 3m Springboard");
//                        break;
//                    case "result women 3m synchronlsed":
//                        coreModule.displayResultsForEvent(writer, "Women 3m synchronlsed");
//                        break;
//                    case "result women 10m synchronlsed":
//                        coreModule.displayResultsForEvent(writer, "Women 10m synchronlsed");
//                        break;
//                    case "result women 10m platform":
//                        coreModule.displayResultsForEvent(writer, "Women 10m platform");
//                        break;
//                    case "result women 1m springboard detail":
//                        coreModule.displayDetailedResults(writer, "Women 1m springboard");
//                        break;
//                    case "result women 3m springboard detail":
//                        coreModule.displayDetailedResults(writer, "Women 3m springboard");
//                        break;
//                    case "result women 3m synchronlsed detail":
//                        coreModule.displayDetailedResults(writer, "Women 3m synchronlsed");
//                        break;
//                    case "result women 10m synchronlsed detail":
//                        coreModule.displayDetailedResults(writer, "Women 10m synchronlsed");
//                        break;
//                    case "result women 10m platform detail":
//                        coreModule.displayDetailedResults(writer, "Women 10m Platform");
//                        break;
//                    case "result men 1m springboard":
//                        coreModule.displayResultsForEvent(writer, "men 1m Springboard");
//                        break;
//                    case "result men 3m springboard":
//                        coreModule.displayResultsForEvent(writer, "men 3m Springboard");
//                        break;
//                    case "result men 3m synchronlsed":
//                        coreModule.displayResultsForEvent(writer, "men 3m synchronlsed");
//                        break;
//                    case "result men 10m synchronlsed":
//                        coreModule.displayResultsForEvent(writer, "men 10m synchronlsed");
//                        break;
//                    case "result men 10m platform":
//                        coreModule.displayResultsForEvent(writer, "men 10m platform");
//                        break;
//                    case "result men 1m springboard detail":
//                        coreModule.displayDetailedResults(writer, "men 1m springboard");
//                        break;
//                    case "result men 3m springboard detail":
//                        coreModule.displayDetailedResults(writer, "men 3m springboard");
//                        break;
//                    case "result men 3m synchronlsed detail":
//                        coreModule.displayDetailedResults(writer, "men 3m synchronlsed");
//                        break;
//                    case "result men 10m synchronlsed detail":
//                        coreModule.displayDetailedResults(writer, "men 10m synchronlsed");
//                        break;
//                    case "result men 10m platform detail":
//                        coreModule.displayDetailedResults(writer, "men 10m Platform");
//                        break;
//                    default:
//                        writer.write("Error\n-----\n");
//                        break;
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
