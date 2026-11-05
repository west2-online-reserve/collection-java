package com.diving;

import com.diving.controller.CommandController;
import com.diving.service.DataFetchService;
import com.diving.service.QueryService;
import com.diving.service.impl.DataFetchServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: 缺少输入文件参数。用法: java -jar DWAsearch.jar input.txt");
            return;
        }

        String inputFile = args[0];
        if (!Files.exists(Paths.get(inputFile))) {
            System.out.println("Error: 输入文件不存在: " + inputFile);
            return;
        }

        // 1. 加载数据 (从 data.json 读取)
        DataFetchService fetchService = new DataFetchServiceImpl();
        QueryService queryService = new QueryService(fetchService.fetchData());
        CommandController controller = new CommandController(queryService);

        // 2. 逐行执行 input.txt 中的命令
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            for (String line : lines) {
                String cmd = line.trim();
                if (!cmd.isEmpty()) {
                    controller.executeCommand(cmd);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: 读取输入文件失败");
        }
    }
}