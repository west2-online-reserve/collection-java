package src.Lib;
import java.io.IOException;
import java.util.HashMap;

import src.DWASearch;


//这个类有两个方法，一个是命令输入，一个是命令识别和处理
//命令输入方法commandInput()用于从input.txt文件中读取命令字符串，并返回一个字符串数组
//命令识别和处理方法commandDistinguish()用于识别命令字符串，并返回指令标签，不同的指令标签对应后续不同的处理方法
public class commandStringProcess {
   public static HashMap<String, Integer> commandMap = new HashMap<String, Integer>();

   public commandStringProcess() {
        commandMap.put("players", 10);
        commandMap.put("result women 1m springboard", 21);
        commandMap.put("result women 3m springboard", 22);
        commandMap.put("result women 10m platform", 23);
        commandMap.put("result women 3m synchronised", 24);
        commandMap.put("result women 10m synchronised", 25);
        commandMap.put("result women 1m springboard detail",121);
        commandMap.put("result women 3m springboard detail",122);
        commandMap.put("result women 10m platform detail",123);
        commandMap.put("result women 3m synchronised detail",124);
        commandMap.put("result women 10m synchronised detail",125);
        commandMap.put("result men 1m springboard", 31);
        commandMap.put("result men 3m springboard", 32);
        commandMap.put("result men 10m platform", 33);
        commandMap.put("result men 3m synchronised", 34);
        commandMap.put("result men 10m synchronised", 35);
        commandMap.put("result men 1m springboard detail",131);
        commandMap.put("result men 3m springboard detail",132);
        commandMap.put("result men 10m platform detail",133);
        commandMap.put("result men 3m synchronised detail",134);
        commandMap.put("result men 10m synchronised detail",135);
   }
    public static String[] commandInput() {
        String fileCommand = "";
        try {
           fileCommand= fileReadAndWrite.readFile(DWASearch.inputFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
        if(fileCommand.isEmpty())
            return new String[0];
        return fileCommand.split("\n");
    }

    //这个函数用于处理命令字符串，区分不同命令，并返回指令标签，不同的指令标签对应后续不同的处理方法
    //指令标签的含义如下：
    //-1 表示无法识别的命令
    //0 表示没有输入任何命令
    //1  表示比赛项目名称不正确或者result后面没有比赛项目名称
    //10 表示players命令
    //21-25 表示女子项目1m、3m、10m、3m同步、10m同步的result命令，如果命令里有detail，则标签加上100
    //31-35 表示男子项目1m、3m、10m、3m同步、10m同步的result命令，如果命令里有detail，则标签加上100

    public int commandDistinguish(String command) {
        if(command.isEmpty())
            return 0;
        else if(commandMap.get(command)==null){
            if(command.startsWith("result ")||command.equals("result"))
                return 1;
            else
                return -1;
        }
        else
            return commandMap.get(command);
    }
}