package com.dwa;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.dwa.model.DivingData;
import com.dwa.service.DataLoader;
import com.dwa.service.PlayerService;
import com.dwa.service.ResultService;


public class Main {
    public static void main(String[] args) {
        // 1. 检查命令行参数
        if (args.length < 2) {
            System.out.println("Error: 缺少参数 input.txt");
            System.exit(1);
        }


        String inputFileName = args[0];
        String outputFileName = args[1];
        System.out.println("成功接收到参数，正在读取文件: " + inputFileName);

        // 2. 检查data.json是否存在
        File dataFile = new File("data.json");
        if (!dataFile.exists()) {//exists 检查硬盘上有无data.json文件
            System.err.println("Error: data.json file not found.");
            System.exit(1);
        }
        
        try(PrintWriter writer = new PrintWriter(outputFileName)) {
            // 3. 读取input.txt的全部内容
            String content = Files.readString(Paths.get(inputFileName));//readString 把文件读成一个字符串
            System.out.println("--- 文件内容如下 ---");
            System.out.println(content);

            // 4. 解析data.json获取数据
            DivingData data =DataLoader.loadData();//调用loadDate方法 解析data.json

            if (data != null) {
                System.out.println("--- 开始执行查询指令 ---");

                // 5. 按行拆分input.txt的内容
                String[] lines = content.split("\n");

                // 6. 各种边界异常处理
                for (String line : lines) {
                    line = line.trim(); // 去掉前后空格
                    if (line.isEmpty()) continue; // 忽略空行

                    if (line.equals("players")) {
                        // 处理 players 指令
                        PlayerService.printPlayers(data.players, writer);
                    } else if (line.startsWith("result ")) {
                        // 处理 result 指令
                        String eventName = line.substring(7).trim();
                        // 检查项目名是否为空，或包含多余空格
                        if (eventName.isEmpty() || eventName.contains("  ")) {
                            writer.println("N/A");
                            writer.println("-----");
                        } else {
                            ResultService.printResults(data.results, eventName,writer);
                        }
                    } else {
                        // 处理未知指令
                        writer.println("Error");
                        writer.println("-----");
                    }
                }
            } else {
                System.err.println("Error: Failed to parse data.json.");
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("读取文件失败！原因: " + e.getMessage());
            System.exit(1);
        }
    }
}