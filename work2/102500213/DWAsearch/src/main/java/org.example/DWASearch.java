package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DWASearch {
    public static void main(String[] args) {
        try {
            run(args);
            System.out.println("查询成功");
            System.exit(0);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void run(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("命令行参数数量不对！要求：输入文件名或路径 输出文件名或路径");
        }
        Path dataFile = Path.of("data.json");
        if (!Files.exists(dataFile)) {
            throw new RuntimeException("数据文件不存在！");
        }
        Path inputFile = Path.of(args[0]);
        if (!Files.exists(inputFile)) {
            throw new RuntimeException("输入文件不存在！");
        }

        JsonLoader jsonLoader = new JsonLoader();
        Data data;
        try {
            data = jsonLoader.load(dataFile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json解析失败", e);
        } catch (IOException e) {
            throw new RuntimeException("json读取失败", e);
        }

        CmdFileReader cmdReader = new CmdFileReader();
        List<String> cmdList;
        try {
            cmdList = cmdReader.read(inputFile);
        } catch (AccessDeniedException e) {
            throw new RuntimeException("输入文件没有读取权限或被占用锁定！", e);
        } catch (IOException e) {
            throw new RuntimeException("输入文件读取异常！", e);
        }

        QueryEngine engine = new QueryEngine();
        String content = engine.runQuery(cmdList, data);

        ResultWriter writer = new ResultWriter();
        Path outputFile = Path.of(args[1]);
        try {
            writer.write(outputFile, content);
        } catch (AccessDeniedException e) {
            if (Files.exists(outputFile)) {
                throw new RuntimeException("输出文件无写入权限！", e);
            } else {
                throw new RuntimeException("输出文件不存在且父目录没有权限，无法创建输出文件！", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("写入文件发生IO异常！", e);
        }
    }
}
