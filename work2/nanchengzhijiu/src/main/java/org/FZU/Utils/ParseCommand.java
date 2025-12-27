package org.FZU.Utils;
import org.FZU.pojo.HttpResponse;
import org.FZU.pojo.Player;
import org.FZU.pojo.Result;

import java.util.ArrayList;
import java.util.Set;

public class ParseCommand {
    Set<String> keyWords= CompetitionUrlConfig.getUrlMapKey();
    HttpUtil httpUtil = new HttpUtil();
    //获取返回体信息，供后续方法解析命令结果
    HttpResponse response = null;
    ArrayList<String> outputs=new ArrayList<>();
//    解析所有命令
    public ArrayList<String> parseAllCommand(ArrayList<String> commands, String fileName){
        for (String command : commands) {
            String urlInfo= CompetitionUrlConfig.findUrlByTarget(command);
            if (urlInfo != null) {
                response = httpUtil.getUrl(urlInfo,"GET");
            }
            outputs.add(parseCommand(command,response,fileName));
        }
        return outputs;
    }

//    解析单个命令
    public String parseCommand(String command, HttpResponse response, String fileName) {
        String output="";
            // 情况1
        if (command.equals("players")) {
            output= Player.displayAllPlayersInfo(response);
            return output;
        } else if (command.startsWith("result ")||command.equals("result")) {
            //情况2
            output=handleResultCommand(command, response, fileName);
        } else {
            return "Error\n-----\n";
        }
        return output;
    }

//    处理开头为result的逻辑
    private String handleResultCommand(String command, HttpResponse response, String fileName) {
        String output="";
        if (keyWords.contains(command)) {
            if (command.endsWith("detail")) {
                output = Result.displayDetailsForEachEven(response);
            } else {
                output = Result.displayResultsForEachEven(response);
            }
            return output;
        } else {
            return "N/A\n-----\n";
        }
    }
}
