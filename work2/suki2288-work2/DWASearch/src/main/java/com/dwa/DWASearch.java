package com.dwa;

import java.io.IOException;

/**
 * 主程序入口，解析命令行参数，读取输入文件，调用CoreModule完成功能等等
 */
public class DWASearch {
    public static void main(String[] args) {
        // 要求传入两个参数input和output
        if (args.length != 2) {
            System.out.println("请使用此格式 java -jar DWASearch.jar input.txt output.txt");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        IOHelper ioHelper = new IOHelper();

        try {
            // 设置输入和输出文件的路径
            ioHelper.setInput(inputFile);
            ioHelper.setOutput(outputFile);

            // 此对象构建时自动加载Json
            CoreModuleImpl core = new CoreModuleImpl();

            // 主循环
            String line;
            while ((line = ioHelper.readLine()) != null) {
                line = line.trim().toLowerCase();

                if (line.isEmpty()) {
                    continue;
                }

                // 当指令是players的情况
                if ("players".equals(line)) {
                    core.displayAllPlayersInfo(ioHelper);

                    // 当指令是其他result XXX的情况
                } else if (line.startsWith("result")) {
                    String[] parts = line.split("\\s+");
                    // 排除一些错误情况（？）
                    if (parts.length < 3 || parts.length > 5) {
                        ioHelper.writeLine("Error");
                        ioHelper.writeLine("-----");
                        continue;
                    }

                    // 提取性别和项目关键字
                    String gender = parts[1];
                    StringBuilder contestBuilder = new StringBuilder();
                    contestBuilder.append(gender);

                    boolean isDetail = false;

                    // 拼接项目名(如 "1m springboard" 或 "10m synchronised")
                    for (int i = 2; i < parts.length; i++) {
                        if ("detail".equals(parts[i])) {
                            isDetail = true;
                            break;
                        }
                        contestBuilder.append(" ").append(parts[i]);
                    }

                    String contestName = contestBuilder.toString();

                    // 验证比赛项目名合法性
                    if (!core.isValidContestName(contestName)) {
                        ioHelper.writeLine("N/A");
                        ioHelper.writeLine("-----");
                        continue;
                    }

                    if (isDetail) {
                        core.displayDetailedResults(contestName, ioHelper);
                    } else {
                        core.displayResults(contestName, ioHelper);
                    }
                } else {
                    // 未识别指令
                    ioHelper.writeLine("Error");
                    ioHelper.writeLine("-----");
                }
            }

            ioHelper.close();

        } catch (IOException e) {
            System.err.println("IO异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}