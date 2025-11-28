import cn.github.lpx.core_module.CoreModule;

import java.io.*;

public class DWASearch {
    static private final String[] event = {"men 1m springboard", "men 3m springboard", "men 3m synchronised",
            "men 10m platform", "men 10m synchronised", "mixed 3m & 10m team", "mixed 3m synchronised", "mixed 10m synchronised",
            "women 1m springboard", "women 3m springboard", "women 3m synchronised", "women 10m platform", "women 10m synchronised"
    };

    public static void main(String[] args) {
        if(args==null){
            System.err.println("传入的参数为null");
            return;
        }
        //要两个参数
        if (args.length != 2) {
            System.err.println("参数数量不对,需要两参数,输入文本文件名和输出文本文件名");
            return;
        }
        //要文本文件
        if(!args[0].matches(".*\\.txt$")||!args[1].matches(".*\\.txt$")){
            System.err.println("文件要文本文件(.txt)");
            return;
        }
        String inputPath = args[0];
        String outputPath = args[1];
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        //输入文件不存在就退出
        if(!inputFile.exists()){
            System.err.println("目标输入文件找不到，退出");
            return;
        }
        //输出文件不存在尝试新建给定文件名文件
        try {
            if(!outputFile.exists()){
                System.out.println("目标输出文件找不到，尝试为您新建文件...");
                outputFile.createNewFile();
                System.out.println("输出文件新建成功！");
            }
        } catch (IOException e) {
            System.out.println("新建文件失败，请检查原因！");
            e.printStackTrace();
        }
        try (Reader reader = new InputStreamReader(new FileInputStream(inputPath), "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            //可能需要追加信息，还是不清空了!
            //new PrintStream("outputPath").close();//清空输出文件
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.equals("players")) {
                    CoreModule.displayAllPlayersInfo(outputPath);
                } else if (line.matches("^result .* detail$")) {
                    int flag=0;
                    for (int i = 0; i < event.length; i++) {
                        if (line.equals("result " + event[i] + " detail")) {
                            CoreModule.displayDetailResultsForEachEvent(event[i],outputPath);
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0) CoreModule.displayErrorType("N/A",outputPath);
                } else if (line.matches("^result .*") && !line.matches("^result.*detail.*") ) {
                    int flag=0;
                    for (int i = 0; i < event.length; i++) {
                        if (line.equals("result " + event[i])) {
                            CoreModule.displayFinalsResultsForEachEvent(event[i],outputPath);
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0) CoreModule.displayErrorType("N/A",outputPath);
                } else {
                    CoreModule.displayErrorType("Error",outputPath);
                }
                line = bufferedReader.readLine();
            }
            System.out.println("已完成任务！");
        } catch (IOException e) {
            System.err.println("io异常");
            e.printStackTrace();
        }
    }
}
