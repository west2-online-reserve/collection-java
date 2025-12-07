package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DWASearch {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar DWASearch.jar <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            List<String> commands = Files.readAllLines(Paths.get(inputFile));

            CoreModule coreModule = new CoreModule();
            coreModule.initializeData();

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
                for (String command : commands) {
                    processCommand(command.trim(), coreModule, writer);
                }
            }

        } catch (Exception e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processCommand(String command, CoreModule coreModule, BufferedWriter writer) throws IOException {
        if (command.equals("players")) {
            coreModule.displayAllPlayers(writer);
        } else if (command.startsWith("result ")) {
            handleResultCommand(command, coreModule, writer);
        } else {
            writer.write("Error\n-----\n");
        }
    }

    private static void handleResultCommand(String command, CoreModule coreModule, BufferedWriter writer) throws IOException {
        String[] parts = command.split("\\s+");
        if (parts.length >= 3 && (parts[1].equals("women") || parts[1].equals("men"))) {
            StringBuilder eventNameBuilder = new StringBuilder();
            eventNameBuilder.append(parts[1]);
            for (int i = 2; i < parts.length; i++) {
                if (!parts[i].equals("detail")) {
                    eventNameBuilder.append(" ").append(parts[i]);
                }
            }

            String eventName = eventNameBuilder.toString().trim();
            boolean isDetail = command.endsWith(" detail");

            if (isValidEventName(eventName)) {
                if (isDetail) {
                    coreModule.displayDetailedResults(writer, eventName);
                } else {
                    coreModule.displayFinalResults(writer, eventName);
                }
            } else {
                writer.write("N/A\n-----\n");
            }
        } else {
            writer.write("N/A\n-----\n");
        }
    }

    private static boolean isValidEventName(String eventName) {
        String[] validEvents = {
                 "women 3m springboard", "women 10m platform",
                 "women 10m synchronised","women 3m synchronised",
                "men 3m springboard", "men 10m platform",
                "men 10m synchronised","men 3m synchronised",
        };

        String lowerEventName = eventName.toLowerCase();
        for (String validEvent : validEvents) {
            if (validEvent.equals(lowerEventName)) {
                return true;
            }
        }
        return false;
    }
}
