package com.dwa;


import com.dwa.Lib.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DWASearch {

    public static void main(String[] args) throws IOException {

        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        //获取项目目录
        String dirUrl = System.getProperty("user.dir") + File.separator;

        File inputFile = new File(dirUrl + inputFileName);
        System.out.println(inputFile.exists());

        String inputStr = ReadFileUtils.readFile(inputFile);
        String[] split = inputStr.split("\n");
        String firstLine = split[0];
        String endLine = split[split.length-1];
        String outputStr = firstLine;
        OutputService outputService = null ;
        Set<String> fileNames = new HashSet<>();
        fileNames.add("men 1m springboard");
        fileNames.add("men 3m springboard");
        fileNames.add("men 3m synchronised");
        fileNames.add("men 10m platform");
        fileNames.add("men 10m synchronised");
        fileNames.add("women 1m springboard");
        fileNames.add("women 3m springboard");
        fileNames.add("women 3m synchronised");
        fileNames.add("women 10m platform");
        fileNames.add("women 10m synchronised");

        if ("```javascript".equals(firstLine)){
            // 需要进行JavaScript命令解析
            String content = null;
            for (int i = 1;i< split.length-1;i++){
                content = split[i];
                String fileName = "";
                if ("players".equals(content)){
                    outputService = new PlayerService();
                    fileName = "athletes";
                } else if (content.startsWith("result ")){
                    String substring = content.substring("result ".length());
                    if(substring.endsWith(" detail")){
                        //获取详情
                        outputService = new displayResults();
                        fileName = substring.substring(0,substring.length()-" detail".length());
                    }else{
                        //获取决赛的
                        outputService = new displayFinalResults();
                        fileName = substring;
                    }
                if (!fileNames.contains(fileName)){
                    outputService = new NAService();

                }
            }else{
                    outputService = new ErrorService();
                }
                outputStr += outputService.getOutputString(fileName + ".json");
            }
        }
        if (!"```".equals(endLine)){
            outputStr += "\nError\n-----\n";
        }
        outputStr += "```";
        File outputFile = new File(dirUrl + outputFileName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(outputStr);
        writer.flush();
    }
}
