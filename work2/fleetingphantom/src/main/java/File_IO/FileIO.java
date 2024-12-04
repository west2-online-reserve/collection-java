package File_IO;

import java.io.*;

public class FileIO {
    private String outputPath;
    private String inputPath;
    private BufferedReader br;
    private boolean isEndOfFile = false;

    public FileIO(String inputPath, String outputPath) {
        this.outputPath = outputPath;
        try {
            this.br = new BufferedReader(new FileReader(inputPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(){
        File file = new File(outputPath);
        if (file.exists()) {
            file.delete();
        }
    }

    //写入文件
    public void fileWrite(String str) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
            bw.write(str);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取文件的一行
    public String fileRead() {
        if (isEndOfFile) {
            return null;
        }
        String str = null;
        try {
            str = br.readLine();
            if (str == null) {
                isEndOfFile = true;
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
