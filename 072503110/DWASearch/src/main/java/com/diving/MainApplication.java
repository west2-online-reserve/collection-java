package com.diving;

import com.diving.controller.CommandController;
import com.diving.exception.DataAccessException;
import com.diving.service.DataFetchService;
import com.diving.service.QueryService;
import com.diving.service.impl.DataFetchServiceImpl;
import com.diving.util.FileUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainApplication {
    public static final String DEFAULT_OUTPUT_FILE = "output.txt";

    private final DataFetchService dataFetchService;

    public MainApplication() {
        this(new DataFetchServiceImpl());
    }

    public MainApplication(DataFetchService dataFetchService) {
        this.dataFetchService = dataFetchService;
    }

    public static void main(String[] args) {
        new MainApplication().run(args);
    }

    /**
     * 执行一次查询流程；出错时直接以对应退出码结束进程，正常结束以 0 退出。
     */
    public void run(String[] args) {
        if (args == null || args.length < 1) {
            System.err.println("Error: 缺少输入文件参数。用法: java -jar DWASearch.jar input.txt [output.txt]");
            System.exit(1);
        }

        Path inputPath = Paths.get(args[0]);
        if (!Files.isRegularFile(inputPath)) {
            System.err.println("Error: 输入文件不存在: " + args[0]);
            System.exit(1);
        }

        Path outputPath = Paths.get(args.length >= 2 ? args[1] : DEFAULT_OUTPUT_FILE);

        // 1. 读取 input.txt 中的命令
        // 失败时 System.exit 会立即结束进程，默认值只是为了通过编译器的初始化检查
        List<String> lines = List.of();
        try {
            lines = FileUtil.readAllLines(inputPath.toString());
        } catch (DataAccessException e) {
            System.err.println("Error: 读取输入文件失败: " + inputPath);
            System.exit(2);
        }

        // 2. 加载数据 (从 data.json 读取)
        QueryService queryService = null;
        try {
            queryService = new QueryService(dataFetchService.fetchData());
        } catch (DataAccessException e) {
            System.err.println("Error: 加载数据失败: " + e.getMessage());
            System.exit(4);
        }

        // 3. 逐行执行命令，结果先写入缓冲区，最终写入输出文件
        StringWriter buffer = new StringWriter();
        PrintWriter writer = new PrintWriter(buffer);
        CommandController controller = new CommandController(queryService, writer);
        for (String line : lines) {
            String cmd = line.trim();
            if (!cmd.isEmpty()) {
                controller.executeCommand(cmd);
            }
        }
        writer.flush();

        // 4. 输出到文件
        try {
            FileUtil.writeFile(outputPath.toString(), buffer.toString());
        } catch (DataAccessException e) {
            System.err.println("Error: 无法写入输出文件: " + outputPath);
            System.exit(3);
        }
        System.exit(0);
    }
}