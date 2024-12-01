package DWA_Search;
import core.Core;
import core.FileIo;
import domain.Contest;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//DWASearch类,包含了命令行接受程序,input命令匹配程序和对应的output执行程序
public class DWASearch {
    //利用正则表达式,检查指令是否符合要求
    public static final Pattern REGEX_PLAYERS = Pattern.compile("^players$");
    public static final Pattern REGEX_CONTEST = Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised)$");
    public static final Pattern REGEX_CONTEST_DETAILED = Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised) detail$");
    public static final Pattern REGEX_RESULT = Pattern.compile("^result.*");

    public static void main(String[] args) throws IOException {
        //判明命令行参数个数,不符则退出程序
        try {
            if (args.length != 2){
                throw new RuntimeException(("You should put two parameters"));
            }
        }catch(RuntimeException e){
            System.out.println(e.toString());
            return;
        }

        Core core = new Core();

        //建立起对应input和output文件的通路
        FileIo.setInputPath(args[0]);
        FileIo.setOutputPath(args[1]);


        //循环读取input文件的每一行指令,并检查指令正确性,执行对应的指令
        String line;
        while ((line = FileIo.inputRead()) != null){
            Matcher playersMatcher = REGEX_PLAYERS.matcher(line);
            Matcher resultMathcher = REGEX_RESULT.matcher(line);
            Matcher contestMathcher = REGEX_CONTEST.matcher(line);
            Matcher contestDetailedMathcher = REGEX_CONTEST_DETAILED.matcher(line);
            //匹配players指令
            if (playersMatcher.find()){
                core.outputPlayer();
                continue;
            }

            //匹配result类型的指令
            if (resultMathcher.find()){

                if (contestMathcher.find()){

                    //输出单项目决赛结果
                    core.outputFinal(contestMathcher.group(1));
                } else if (contestDetailedMathcher.find()){
                    //输出单项目的多种赛事结果
                    core.outputContestDetailed(contestDetailedMathcher.group(1));
                } else {
                    //result后的指令不匹配
                    FileIo.outputWrite("N/A\n" + "-----\n");
                }

            } else {
                //无法识别指令
                FileIo.outputWrite("Error\n" + "-----\n");
            }

        }

        FileIo.close();
    }
}