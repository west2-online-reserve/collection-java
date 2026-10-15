package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utility {
    private static String output = "";
    private static String input = "";
    public static BufferedWriter bw;
    public static BufferedReader br;

    public static String readLine() throws IOException {
        return br.readLine();
    }

    public static void write(String s) throws IOException {
        // 题目要求输出换行统一使用 \n，不要用 newLine()（Windows 下会是 \r\n）
        bw.write(s);
        bw.write("\n");
    }

    public static void close() throws IOException {
        if (br != null) {
            br.close();
        }
        if (bw != null) {
            bw.close();
        }
    }

    public static void setOutput(String output) throws IOException {
        Utility.output = output;
        // 题目要求输出 UTF-8
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
    }

    public static void setInput(String input) throws FileNotFoundException {
        Utility.input = input;
        // 题目要求输入 UTF-8
        br = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
    }
}
