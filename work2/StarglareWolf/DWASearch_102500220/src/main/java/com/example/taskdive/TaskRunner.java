package com.example.taskdive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskRunner {
    public static void run(String[] args)throws InvalidInputException {
        if(args[0].equals("fetch")){
            DataFetchSpider.getData();
            return;
        }
        if (args.length != 2) {
            throw new InvalidInputException("Usage: java -jar DWASearch.jar <input_file> <output_file>");
        }
        String inputFile = args[0];
        String outputFile = args[1];

        File inFile = new File(inputFile);
        // 确定文件目录是否存在以及是否是文件目录
        if (!inFile.exists() || !inFile.isFile()) {
            throw new InvalidInputException("Error: Input file '" + inputFile + "' does not exist");
        }

        try {
            List<Disciplines> Discipline = new ArrayList<>();
            // 读取输入文件中的每一行信息
            List<String> lines = Files.readAllLines(Paths.get(inputFile), StandardCharsets.UTF_8);
            // 标准输出流
            PrintStream originalOut = System.out;
            // 文件重定向输出流
            // 使用 try 包裹，运行结束后 fileOut 会自动关闭，避免文件无法关闭的情况出现
            try(PrintStream fileOut = new PrintStream(new FileOutputStream(outputFile), true, StandardCharsets.UTF_8)) {

                // 输出重定向,这样就不用大规模更改其它地方的输出了
                System.setOut(fileOut);

                // 预先装入数据
                GroupSearch.SearchFor(Discipline);
                SingleSearch.SearchFor(Discipline);

                // 通过读取每一行信息来调用指令
                for (String SearchName : lines) {
                    if (SearchName == null || SearchName.trim().isEmpty()) continue;
                    if(SearchName.startsWith("players")){
                        if(SearchName.equals("players")){
                            AthleteSearch.SearchFor();
                        }
                        else {
                            System.out.println("Error");
                            System.out.println("-----");
                        }
                    }
                    else if(SearchName.startsWith("result")){
                        String[] parts = SearchName.trim().split("\\s+");
                        String firstWord = parts[0];
                        if(firstWord.startsWith("result"))
                        {
                            if(!firstWord.equals("result"))
                            {
                                System.out.println("Error");
                                System.out.println("-----");
                                continue;
                            }

                            if(parts.length < 2)
                            {
                                System.out.println("N/A");
                                System.out.println("-----");
                                continue;
                            }

                            boolean isMatch = false;
                            for(int i = 0;i < ProjectMatcher.VALID_PROJECTS.size();i++){
                                if(SearchName.equals("result " + ProjectMatcher.VALID_PROJECTS.get(i).toLowerCase().replace("'s", ""))){
                                    String result =  ProjectMatcher.VALID_PROJECTS.get(i);
                                    isMatch = true;
                                    for(Disciplines discipline : Discipline){
                                        if (discipline.getDisciplineName().equals(result)) {
                                            OutputClass.OutputDisciplines(discipline);
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            if(!isMatch){
                                System.out.println("N/A");
                                System.out.println("-----");
                            }
                        }
                    }
                    else{
                        System.out.println("Error");
                        System.out.println("-----");
                    }

                }
                if(fileOut.checkError()){     // PrintStream 会吞掉错误，导致出错但是正常运行，所以需要通过其自带方法 checkError() 检查错误
                   throw new IOException("Failed to write to output file '" + outputFile + "'");
                }

                System.out.flush();
            }
            catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());  // 无法读取文件标识符
                System.exit(1);
            }
            finally {
                // 恢复至标准输出流
                System.setOut(originalOut);
            }
        }
        catch(IOException e)
        {
            System.err.println("Error: File access failed - "+ e.getMessage());  // 文件访问失败标识
            System.exit(1);
        }
    }
}
