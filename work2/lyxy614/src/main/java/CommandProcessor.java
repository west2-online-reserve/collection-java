package main.java;

import main.java.model.Athlete;
import main.java.model.CompetitionResultRoot;
import main.java.util.DWAFile;
import main.java.util.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CommandProcessor {
    private static Map<String, Runnable> COMMANDS = new HashMap<>();
    private static Set<String> competitionNames = new HashSet<>();
    private static List<Athlete> athletes;
    private static OutputModule coreModule = new CoreModule();

    static {
        List<String> resourceFiles = DWAFile.loadFiles();
        try {
            for (String fileName : resourceFiles) {

                String commandName = fileName.substring(0, fileName.indexOf(".json"));
                String content = DWAFile.readJsonFile(fileName);
                if (commandName.equals("athletes")) {
                    athletes = JsonParser.<Athlete>parserArray(content, Athlete.class);
                    COMMANDS.put("players", () ->
                            coreModule.displayAllPlayersInfo(DWAFile.getOutputFile().getPath(), athletes)
                    );
                } else {
                    competitionNames.add(commandName);
                    CompetitionResultRoot competitionResultRoot;
                    competitionResultRoot = JsonParser.<CompetitionResultRoot>parser(content, CompetitionResultRoot.class);
                    COMMANDS.put("result " + commandName.toLowerCase(), () ->
                            coreModule.displayResultsForEachEvent(DWAFile.getOutputFile().getPath(), competitionResultRoot));
                    COMMANDS.put("result " + commandName.toLowerCase() + " detail", () ->
                            coreModule.displayDetailResultsForEachEvent(DWAFile.getOutputFile().getPath(), competitionResultRoot));
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }


    }
    //专门负责执行和检查命令的方法
    public static void runCommand(String command){
        //检查逻辑：首先，如果直接能匹配原命令，就直接运行，无需检查
        if (COMMANDS.containsKey(command)){
            COMMANDS.get(command).run();
            return;
        }
        //不能执行的，先检查命令（前缀）是否合法，不合法的输出"Error"，结束检查
        //把命令按空格分割，以单独检查比赛名称有没有错误
        String [] args = command.split("\\s+");
        //要求中没有提到如果result和detail与比赛名称之间有超过一个的空格需要报错，那就默认可以执行
        //所以这里统一把多空格转为一个空格来保证能与之前初始化的命令相匹配
        command = command.replaceAll("\\s+", " ");
        String wrongMessage;
        if (command.startsWith("players")){
            //players只能单独作为命令
            if (!command.equals("players")){
                wrongMessage = "Error";
                coreModule.displayWrongMessage(DWAFile.getOutputFile().getPath(), wrongMessage);
            }
        }
        else if (command.startsWith("result ")){
            //若前缀为"result"且后有空格，继续按顺序检查比赛名称
            if (!competitionNames.contains(args[1])){
                wrongMessage = "N/A";
                coreModule.displayWrongMessage(DWAFile.getOutputFile().getPath(), wrongMessage);
            }
            //比赛名称也合法，继续检查后缀是否为"detail"
            else if (!args[2].equals("detail")){
                wrongMessage = "N/A";
                coreModule.displayWrongMessage(DWAFile.getOutputFile().getPath(), wrongMessage);
            }

        }
        else {
            wrongMessage = "Error";
            coreModule.displayWrongMessage(DWAFile.getOutputFile().getPath(), wrongMessage);
        }
    }

}
