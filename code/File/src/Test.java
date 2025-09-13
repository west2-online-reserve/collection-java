import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        //指令
        String command = null;
        //指令列表
        ArrayList<String> commands = new ArrayList<>(Arrays.asList("players",
                "result women 1m springboard",
                "result women 3m springboard",
                "result women 3m synchronised",
                "result women 10m synchronised",
                "result women 10m platform",
                "result men 1m springboard",
                "result men 3m springboard",
                "result men 3m synchronised",
                "result men 10m synchronised",
                "result men 10m platform",
                "result women 1m springboard detail",
                "result women 3m springboard detail",
                "result women 3m synchronised detail",
                "result women 10m synchronised detail",
                "result women 10m platform detail",
                "result men 1m springboard detail",
                "result men 3m springboard detail",
                "result men 3m synchronised detail",
                "result men 10m synchronised detail",
                "result men 10m platform detail"));

        while(true) {
            if (s.hasNextLine()) {
                command = s.nextLine();
                //指令处理
                if (command.equals("exit"))
                {
                    break;
                }
                else
                {
                    if (commands.equals("players"))
                    {
                        //输出所有选手信息的操作

                    }
                    if (command.startsWith("result"))
                    {
                        if (command.endsWith("detail"))
                        {
                            if (commands.contains(command))
                            {
                                //输出详细的比赛结果
                            }
                            else{
                                //输出N/A
                            }

                        }
                        else
                        {
                            if (commands.contains(command))
                            {
                                //输出简单的比赛结果
                            }
                            else
                            {
                                //输出N/A
                            }
                        }
                    }
                    //输出error


                }
            }
        }
    }
}
