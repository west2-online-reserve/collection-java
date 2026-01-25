package org.FZU;

import org.FZU.Utils.FileUtil;
import org.FZU.Utils.ParseCommand;

import java.util.ArrayList;
public class DWASearch {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String fileName="output.txt";
        ArrayList<String> outputs;
//        读取所有命令
        ArrayList<String> commands=FileUtil.readCommands(inputFile);
//        解析所有命令并输出
        outputs=new ParseCommand().parseAllCommand(commands, fileName);
        FileUtil.writeResults(fileName,outputs);
    }
}

