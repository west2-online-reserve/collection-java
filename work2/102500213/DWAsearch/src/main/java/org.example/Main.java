package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("命令行参数数量不对！要求：输入文件名或路径 输出文件名或路径");
            return;
        }
        Path dataFile = Path.of("data.json");
        if(!Files.exists(dataFile)){
            System.out.println("数据文件不存在！");
            return;
        }
        Path inputFile = Path.of(args[0]);
        if(!Files.exists(inputFile)){
            System.out.println("输入文件不存在！");
            return;
        }

        // Json加载
        JsonLoader jsonLoader = new JsonLoader();
        Data data = null;
        try{
            data = jsonLoader.load(dataFile);
        } catch (JsonProcessingException e) {
            System.out.println("json解析失败");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.out.println("json读取失败");
            e.printStackTrace();
            return;
        }

        // 读取命令
        CmdFileReader cmdReader = new CmdFileReader();
        List<String> cmdList;
        try {
            cmdList = cmdReader.read(inputFile);
        } catch (AccessDeniedException e){
            System.out.println("输入文件没有读取权限或被占用锁定！");
            e.printStackTrace();
            return;
        }catch(IOException e){
            System.out.println("输入文件读取异常！");
            e.printStackTrace();
            return;
        }

        // 执行查询
        QueryEngine engine = new QueryEngine();
        String Content = engine.runQuery(cmdList, data);

        // 写出文件
        ResultWriter writer = new ResultWriter();
        Path outputFile = Path.of(args[1]);
        try {
            writer.write(outputFile, Content);
            System.out.println("查询成功");
        } catch (AccessDeniedException e) {
            if (Files.exists(outputFile)) {
                System.out.println("输出文件无写入权限！");
            } else {
                System.out.println("输出文件不存在且父目录没有权限，无法创建输出文件！");
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
