package com.jay.work2;

import java.io.IOException;

public class DWASearch {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error");
            System.out.println("java DWASearch input.txt output.txt");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];
        String inputContent;
        try {
            inputContent = Lib.readFile(inputPath);
        } catch (IOException e) {
            System.out.println("Fail to get the input.txt: " + e.getMessage());
            return;
        }

        CoreModule core = new CoreModule();
        StringBuilder outputContent = new StringBuilder();
        String[] lines = inputContent.split("\\n");

        for (String line : lines) {
            String cmd = line.trim();
            if (cmd.isEmpty()) {
                continue;
            }

            if (!Lib.isAllLowerCase(cmd)) {
                outputContent.append("Error\n-----\n");
                continue;
            }

            if ("players".equals(cmd)) {
                String playersOutput = core.getAllPlayersInfo();
                if (playersOutput.isEmpty()) {
                    outputContent.append("N/A\n-----\n");
                } else {
                    outputContent.append(playersOutput);
                    if (!playersOutput.endsWith("\n")) {
                        outputContent.append("\n");
                    }
                    outputContent.append("-----\n");
                }
            } else if (cmd.startsWith("result ")) {
                String resultOutput = core.getEventResult(cmd);
                if (resultOutput == null || resultOutput.isEmpty()) {
                    outputContent.append("N/A\n-----\n");
                } else {
                    outputContent.append(resultOutput);
                    if (!resultOutput.endsWith("\n")) {
                        outputContent.append("\n");
                    }
                    outputContent.append("-----\n");
                }
            } else {
                outputContent.append("Error\n-----\n");
            }
        }

        try {
            Lib.writeFile(outputPath, outputContent.toString().trim());
            System.out.println("Write to output.txt successfully!");
        } catch (IOException e) {
            System.out.println("Failed to write to output.txt: " + e.getMessage());
        }
    }
}
