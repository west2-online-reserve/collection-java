package org.uzk20;

import org.uzk20.core.CoreModule;
import org.uzk20.utils.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DWASearch {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("请传入输入文件和输出文件路径");
            //System.exit(1);
            return;
        }

        String inputPath=args[0];
        String outputPath=args[1];

        CoreModule coreModule=new CoreModule();
        StringBuilder finalOutput=new StringBuilder();

        try(BufferedReader br=new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                //排错用代码
                //System.out.println("读取到指令：" + line);
                Command cmd = Command.parseCommand(line);
                String cmdOutput = coreModule.processCommand(cmd);
                //打印指令处理后的输出，确认是否有内容
                //System.out.println("指令处理结果：" + cmdOutput);
                finalOutput.append(cmdOutput);
            }
            try (FileWriter fw = new FileWriter(outputPath, false)) {
                fw.write(finalOutput.toString());
            }
            System.out.println("成功处理" + outputPath);
        }catch(IOException e){
            System.out.println("失败"+e.getMessage());
        }
    }
}
