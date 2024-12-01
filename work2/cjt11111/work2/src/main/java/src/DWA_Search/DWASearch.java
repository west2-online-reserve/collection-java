package DWA_Search;

import core.CoreModule;
import utils.Utility;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



    public class DWASearch {
        public static  final Pattern REGEX_PLAYERS=Pattern.compile("^players$");
        public static  final Pattern REGEX_CONTEXT=Pattern.compile("^result (Women 1m Springboard|Women 3m Springboard|Women 10m Platform|Women 3m Synchronised|Women 10m Synchronised|Men 1m Springboard|Men 3m Springboard|Men 10m Platform|Men 3m Synchronised|Men 10m Synchronised)$");
        public static  final Pattern REGEX_CONTEST_DETAILED=Pattern.compile("^result (Women 1m Springboard|Women 3m Springboard|Women 10m Platform|Women 3m Synchronised|Women 10m Synchronised|Men 1m Springboard|Men 3m Springboard|Men 10m Platform|Men 3m Synchronised|Men 10m Mynchronised) detail$");
        public static  final Pattern REGEX_RESULT=Pattern.compile("^result.*");

        public static void main(String[] args) throws IOException {
            CoreModule coreMudule=new CoreModule();
            String output=args[1];
            String input=args[0];
            String line;

            Utility.setOutput(output);

            Utility.setInput(input);
            //通过 Matcher 类可以从目标字符串中依次取出特定子串（匹配正则表达式的子串）
            while((line= Utility.readLine())!=null){
                Matcher playerMatcher=REGEX_PLAYERS.matcher(line);
                Matcher resultMatcher=REGEX_RESULT.matcher(line);
                Matcher contestMatcher=REGEX_CONTEXT.matcher(line);
                Matcher contestDetailedMatcher=REGEX_CONTEST_DETAILED.matcher(line);
                //查询players
                if (playerMatcher.find()){
                    coreMudule.displayAllPlayersInfo();
                    continue;
                }
                //查询比赛
                if(resultMatcher.find()){
                    if(contestMatcher.find())
                    {
                        coreMudule.displayResults(contestMatcher.group(1));
                    }else if(contestDetailedMatcher.find()){
                        coreMudule.displayDetailedResults(contestDetailedMatcher.group(1));
                    }else{
                        Utility.write("N/A\n"+"-----");
                    }
                }else{
                    Utility.write("Error\n"+"-----");
                }
            }
            Utility.close();
        }

    }


