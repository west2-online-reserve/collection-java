package core;

import java.io.*;

//文件输入输出类,包含了创建对目标文件的通路并进行读写的函数
public class FileIo {

    //类静态变量,包括对input和output的路径,以及对应的缓冲输入输出流
    private static String inputPath;
    private static String outputPath;
    public static BufferedReader br;
    public static BufferedWriter bw;

    //设置对应input和output文件的IO读写通路
    public static void setInputPath(String inputPath) throws IOException{
        FileIo.inputPath = inputPath;
        br = new BufferedReader(new FileReader(inputPath));
    }

    public static void setOutputPath(String outputPath) throws IOException{
        FileIo.outputPath = outputPath;
        bw = new BufferedWriter(new FileWriter(outputPath));
    }

    //对input和output文件进行读和写操作
    public static String inputRead() throws IOException {

        return br.readLine();
    }

    public static void outputWrite(String line) throws IOException {

        bw.write(line);

    }

    public static void close() throws IOException{
        br.close();
        bw.close();
    }
}
