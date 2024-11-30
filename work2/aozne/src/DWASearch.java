package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DWASearch {
    private static Map<String,String> map=new HashMap<>();
    static {
        map.put("men 1m springboard","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/men 1m springboard.json");
        map.put("men 3m springboard","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/men 3m springboard.json");
        map.put("men 3m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/men 3m synchronised.json");
        map.put("men 10m platform","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/men 10m platform.json");
        map.put("men 10m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/men 10m synchronised.json");
        map.put("women 1m springboard","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 1m springboard.json");
        map.put("women 3m springboard","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 3m springboard.json");
        map.put("women 3m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 3m synchronised.json");
        map.put("women 10m platform","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 10m platform.json");
        map.put("women 10m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 10m synchronised.json");
//        map.put("men 1m springboard","men 1m springboard.json");
//        map.put("men 3m springboard","men 3m springboard.json");
//        map.put("men 3m synchronised","men 3m synchronised.json");
//        map.put("men 10m platform","men 10m platform.json");
//        map.put("men 10m synchronised","men 10m synchronised.json");
//        map.put("women 1m springboard","women 1m springboard.json");
//        map.put("women 3m springboard","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 3m springboard.json");
//        map.put("women 3m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 3m synchronised.json");
//        map.put("women 10m platform","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 10m platform.json");
//        map.put("women 10m synchronised","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/women 10m synchronised.json");
    }
    public static void main(String[] args){
        CoreModule coreModule=new CoreModule();
        String in;
        clearFile("src/main/java/org/example/"+args[1]);
        try(BufferedReader br=new BufferedReader(new FileReader("src/main/java/org/example/"+args[0]))){
            while((in=br.readLine()) != null){
                if(in.isEmpty()){
                    continue;
                }
                String temp=in.trim();
                if(temp.equals("players")){
//                    coreModule.displayAllPlayersInfo("C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org/example/output.txt","C:/Users/wuwuwuwu/Desktop/java/work2/work2/src/main/java/org.example/data/athletes.json");
                    coreModule.displayAllPlayersInfo("src/main/java/org/example"+args[1],"athletes.json");
                }
                else if(temp.startsWith("result ")){
                    String name=temp.substring(7);
                    if(name.endsWith(" detail")){
                        name=name.substring(0,name.length()-7);
                        if(map.containsKey(name)){
                            // 不同情况下 有的没有半决赛flag应该为2 有半决赛的话则为3 synchronised flag应为4
                            int num;
                            if(name.equals("men 1m springboard") || name.equals("women 1m springboard")){
                                num=2;
                            }
                            else if(name.equals("men 3m springboard") || name.equals("women 3m springboard") || name.equals("men 10m platform") || name.equals("women 10m platform")){
                                num=3;
                            }
                            else{
                                num=1;
                            }
                            coreModule.displayResultsForEachEvent("src/main/java/org/example"+args[1],name+".json",num);
                        }
                        else{
                            displayNA("src/main/java/org/example"+args[1]);
                        }
                    }
                    else{
                        if(map.containsKey(name)){
                            coreModule.displayResultsForEachEvent("src/main/java/org/example"+args[1],name+".json",1);
                        }
                        else{
                            displayNA("src/main/java/org/example"+args[1]);
                        }
                    }
                }
                //输入不合法指令
                else{
                    displayError("src/main/java/org/example"+args[1]);
                }
            }
        }catch (IOException e){
            System.out.println("找不到输入文件");
            displayError("src/main/java/org/example"+args[1]);
        }
    }
    //清空上一次操作在output中留下的文本
    public static void clearFile(String fileName){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(fileName))){
            bw.write("");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void displayNA(String fileName){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(fileName,true))){
            bw.write("N/A\n-----\n");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void displayError(String fileName){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(fileName,true))){
            bw.write("Error\n-----\n");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
