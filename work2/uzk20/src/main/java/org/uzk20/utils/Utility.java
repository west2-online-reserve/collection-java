package org.uzk20.utils;

import java.io.*;

public class Utility {
    public static String readJsonFile(String fileName){
        StringBuilder jsonContent=new StringBuilder();
        try (InputStream in = Utility.class.getClassLoader().getResourceAsStream(fileName); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            if(in==null){
                throw new RuntimeException("没找到"+fileName+"文件");
            }
            String line;
            while((line= br.readLine())!=null){
                jsonContent.append(line);
            }
        }catch (IOException e){
            throw new RuntimeException("读取文件失败："+fileName,e);
        }
        return jsonContent.toString();
    }
}
