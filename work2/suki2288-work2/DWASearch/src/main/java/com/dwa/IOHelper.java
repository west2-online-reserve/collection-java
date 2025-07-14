package com.dwa;

import java.io.*;

public class IOHelper {

    public String input = "";
    public String output = "";
    public BufferedReader br;
    public BufferedWriter bw;

    // 设置输入文件路径
    public void setInput(String input) throws IOException {
        this.input = input;
        if (br != null) {
            br.close();
        }
        br = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
    }

    // 设置输出文件路径
    public void setOutput(String output) throws IOException {
        this.output = output;
        if (bw != null) {
            bw.close();
        }
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
    }

    // 读取输入文件中的一行内容
    public String readLine() throws IOException {
        if (br == null) {
            throw new IllegalStateException("输入流尚未设置，请先调用setInput()");
        }
        return br.readLine();
    }

    // 写入一行文本不换行
    public void write(String content) throws IOException {
        if (bw == null) {
            throw new IllegalStateException("输出流尚未设置，请先调用setOutput()");
        }
        bw.write(content);
    }

    // 写入一行文本并换行
    public void writeLine(String content) throws IOException {
        write(content);
        bw.newLine();
    }

    // 关闭输入输出流资源
    public void close() throws IOException {
        if (br != null) {
            br.close();
            br = null;
        }
        if (bw != null) {
            bw.close();
            bw = null;
        }
    }
}