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
// 用于提示用户有哪些项目可查询，如果不需要可以直接删掉或者已经注释掉了
//                System.out.println("players\nDiscipline:");
//                System.out.println("""
//                        Men's 10m Platform
//                        Men's 3m Springboard
//                        Women's 10m Platform
//                        Women's 3m Springboard""");
//                System.out.println("""
//                        Men's 10m Synchronised
//                        Mixed 3m & 10m Team
//                        Men's 3m Synchronised
//                        Women's 10m Synchronised
//                        Women's 3m Synchronised""");
//
//                System.out.print("Please enter the task you want to search: ");

                // 通过读取每一行信息来调用指令
                for (String SearchName : lines) {
                    SearchName = ProjectMatcher.matchDiscipline(SearchName);

                    if (SearchName != null) {
                        if (SearchName.equals(ProjectMatcher.getProjectName(0))) {
                            AthleteSearch.SearchFor();
                        } else {
                            for (Disciplines discipline : Discipline) {
                                if (discipline.getDisciplineName().equals(SearchName)) {
                                    OutputClass.OutputDisciplines(discipline);
                                }
                            }
                        }
                    }
                }
                System.out.flush();
            }
            catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());  // 无法读取文件标识符
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
