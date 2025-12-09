package main.java;
import main.java.exception.*;
import main.java.util.DWAFile;

import java.io.*;

public class DWASearch {

    private static BufferedReader br;

    public static void main(String[] args) {
        //用Map装命令(finished)
        //把detail功能完善好(finished)
        //判断输入的命令(finished)
        //实现命令行参数的读取，以及配套的文件读写相关异常处理(finished)
        //编写JUnit单元测试
        if (!argsExceptionProcessor(args))
            return;

        DWAFile.setInputFile(args[0]);
        DWAFile.setOutputFile(args[1]);
        try{
            br = new BufferedReader(new FileReader(DWAFile.getInputFile()));
            String command;
             while ((command = readInputFile()) != null){
                 CommandProcessor.runCommand(command);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    //命令行参数判断
    public static boolean argsExceptionProcessor(String[] args){
        try{
            if (args.length != 2){
                throw new CommandLineArgsException("参数数量错误，应该包含输入和输出两个文件路径");
            }
            else if (!new File(args[0]).exists()){
                throw new FilePathException("input.txt文件路径错误");
            }
            else if (!new File(args[1]).exists()){
                throw new FilePathException("output.txt文件路径错误");
            }
            return true;
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public static String readInputFile() {
        try{
            return br.readLine();
        }catch(IOException e){
            System.err.println("读取输入文件错误" + e.getMessage());
            return null;
        }
    }
    //该方法仅用于测试readInputFile时使用
    public static void setBr(BufferedReader newBr) {
        br = newBr;
    }
}
