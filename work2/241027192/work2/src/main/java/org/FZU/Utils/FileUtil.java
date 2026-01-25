package org.FZU.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtil {
    //读取文件命令
    public static ArrayList<String> readCommands(String inputFile) {
        ArrayList<String> commands=new ArrayList<>();
        try {
//            清空output.txt
            FileWriter writer=new FileWriter("output.txt");
            writer.close();
//            读取命令并存储
            Path path = Paths.get(inputFile);
            InputStream inputStream = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
            if (commands.isEmpty()) {
                System.out.println("错误: 输入文件为空");
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("错误: 无法读取输入文件 - " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("错误: 输入文件为空");
        }
        return commands;
    }
    public static void writeResults(String outputFile, ArrayList<String> outputs) {
        for (String output : outputs) {
            try {
                BufferedWriter writer=new BufferedWriter(new FileWriter("output.txt", true));
                writer.write(output);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
