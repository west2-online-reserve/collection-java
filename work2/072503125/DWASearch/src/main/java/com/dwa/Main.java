package com.dwa;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // 1. 检查命令行参数
        if (args.length < 1) {
            System.out.println("Error: 缺少参数 input.txt");
            System.exit(1); //缺少参数时，直接退出JVM，1代表非正常退出
        }
        String inputFileName = args[0];
        System.out.println("成功接收到参数，正在读取文件: " + inputFileName);

        // 2. 检查data.json是否存在
        File dataFile = new File("data.json");
        if (!dataFile.exists()) {//exists 检查硬盘上有无data.json文件
            System.err.println("Error: data.json file not found.");
            System.exit(1); 
        }
        
        try {
            // 3. 读取input.txt的全部内容
            String content = Files.readString(Paths.get(inputFileName));//readString 把文件读成一个字符串
            System.out.println("--- 文件内容如下 ---");
            System.out.println(content);

            // 4. 解析data.json获取数据
            com.dwa.model.DivingData data = com.dwa.service.DataLoader.loadData();//调用loadDate方法 解析data.json

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
                        com.dwa.service.PlayerService.printPlayers(data.players);
                    } else if (line.startsWith("result ")) {
                        // 处理 result 指令
                        String eventName = line.substring(7).trim();
                        // 检查项目名是否为空，或包含多余空格
                        if (eventName.isEmpty() || eventName.contains("  ")) {
                            System.out.println("N/A");
                            System.out.println("----");
                        } else {
                            com.dwa.service.ResultService.printResults(data.results, eventName);
                        }
                    } else {
                        // 处理未知指令
                        System.out.println("Error");
                        System.out.println("----");
                    }
                }
            } else {
                System.err.println("Error: Failed to parse data.json.");
                System.exit(1); // 解析失败，非零退出码
            }
        } catch (Exception e) {
            System.err.println("读取文件失败！原因: " + e.getMessage());
            System.exit(1); // 异常退出，非零退出码
        }
    }
}