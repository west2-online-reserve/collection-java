package com.wdasearch;

import java.io.*;

public class DWASearch {
    public static void main(String[] args) {
        if (args.length != 2) {
            try {
                throw new IOException("输入文件数不等于2");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                while ((line = reader.readLine()) != null) {
                    if (line.equals("players")) {
                        CoreModule.displayAllPlayersInfo(writer);
                    } else if (!line.matches("result .*")) {
                        writer.write("Error\n-----\n");
                    } else {
                        line = line.substring(7);
                        CoreModule.displayResults(writer, line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
