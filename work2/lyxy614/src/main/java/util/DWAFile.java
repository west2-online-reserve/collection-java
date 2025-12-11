package main.java.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class DWAFile {
    private static File inputFile;
    private static File outputFile;
    public static List<String> loadFiles(){
        List<String> resourceFiles = new ArrayList<>();
        resourceFiles.add("athletes.json");
        resourceFiles.add("Men 1m Springboard.json");
        resourceFiles.add("Men 3m Springboard.json");
        resourceFiles.add("Men 3m Synchronised.json");
        resourceFiles.add("Men 10m Platform.json");
        resourceFiles.add("Men 10m Synchronised.json");
        resourceFiles.add("Women 1m SpringBoard.json");
        resourceFiles.add("Women 3m SpringBoard.json");
        resourceFiles.add("Women 3m Synchronised.json");
        resourceFiles.add("Women 10m Platform.json");
        resourceFiles.add("Women 10m Synchronised.json");
        if (resourceFiles.isEmpty()){
            System.err.println("No resource files found!");
            return null;
        }
        //以下这些是之前用File来读取json内容时，用于初始化所有File对象的代码
//        File folder = new File(resourcePath);
//        if (!folder.exists() || !folder.isDirectory()) {
//            System.err.println(resourcePath + " is not exist or is not a directory");
//            return null;
//        }
//        File[] allFiles = folder.listFiles();
//        for (File file : allFiles) {
//            if (file.isFile() && file.getName().endsWith(".json")) {
//                resourceFiles.add(file);
//            }
//        }
        return resourceFiles;
    }
    //用InputStream来读取json内容
    public static String readJsonFile(String jsonFileName) throws IOException {

        InputStream is = DWAFile.class.getClassLoader().getResourceAsStream(jsonFileName);
        if (is == null) {
            throw new IOException("找不到JSON文件：" + jsonFileName + "（请检查文件名是否写对）");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
        }

        br.close();
        is.close();

        return content.toString();
    }
    public static void setInputFile(String externalInputFile) {
        inputFile = new File(externalInputFile);
    }
    public static File getInputFile() {
        return inputFile;
    }
    public static void setOutputFile(String externalOutputFile) {
        outputFile = new File(externalOutputFile);
    }
    public  static File getOutputFile() {
        return outputFile;
    }

}
