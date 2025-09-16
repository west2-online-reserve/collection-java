import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Core core = new Core();
        //程序运行开始
        long startTime = System.currentTimeMillis();
        System.out.println("程序开始运行...");

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
        //指令map
        Map<String,String> commandsMap = new HashMap<>();
        commandsMap.put("result women 1m springboard","src/main/java/Data/Women 1m SpringBoard.json");
        commandsMap.put("result women 3m springboard","src/main/java/Data/Women 3m SpringBoard.json");
        commandsMap.put("result women 3m synchronised","src/main/java/Data/Women 3m Synchronised.json");
        commandsMap.put("result women 10m synchronised","src/main/java/Data/Women 10m Synchronised.json");
        commandsMap.put("result women 10m platform","src/main/java/Data/Women 10m Platform.json");
        commandsMap.put("result men 1m springboard","src/main/java/Data/Men 1m SpringBoard.json");
        commandsMap.put("result men 3m springboard","src/main/java/Data/Men 3m SpringBoard.json");
        commandsMap.put("result men 3m synchronised","src/main/java/Data/Men 3m Synchronised.json");
        commandsMap.put("result men 10m synchronised","src/main/java/Data/Men 10m Synchronised.json");
        commandsMap.put("result men 10m platform","src/main/java/Data/Men 10m Platform.json");
        commandsMap.put("result women 1m springboard detail","src/main/java/Data/Women 1m SpringBoard.json");
        commandsMap.put("result women 3m springboard detail","src/main/java/Data/Women 3m SpringBoard.json");
        commandsMap.put("result women 3m synchronised detail","src/main/java/Data/Women 3m Synchronised.json");
        commandsMap.put("result women 10m synchronised detail","src/main/java/Data/Women 10m Synchronised.json");
        commandsMap.put("result women 10m platform detail","src/main/java/Data/Women 10m Platform.json");
        commandsMap.put("result men 1m springboard detail","src/main/java/Data/Men 1m SpringBoard.json");
        commandsMap.put("result men 3m springboard detail","src/main/java/Data/Men 3m SpringBoard.json");
        commandsMap.put("result men 3m synchronised detail","src/main/java/Data/Men 3m Synchronised.json");
        commandsMap.put("result men 10m synchronised detail","src/main/java/Data/Men 10m Synchronised.json");
        commandsMap.put("result men 10m platform detail","src/main/java/Data/Men 10m Platform.json");

        while(true) {
            if (s.hasNextLine()) {
                command = s.nextLine();
                //创建BufferedWriter对象
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Input.txt",true));
                    bw.write(command+"\n");
                    bw.close();
                } catch (IOException e){
                    System.out.println("Error: " + e.getMessage());
                }
                //指令处理
                if (command.equals("exit"))
                {
                    break;
                }
                else
                {
                    if (command.equals("players"))
                    {
                        //输出所有选手信息的操作
                        core.showPlayerInfo("src/main/java/Data/athletes.json");
                    }
                    else if (command.startsWith("result") && command.charAt(6)==' ')
                    {
                        if (command.endsWith("detail"))
                        {
                            if (commands.contains(command))
                            {
                                //输出详细的比赛结果
                                core.showProjectResultDetail(commandsMap.get(command));
                            }
                            else{
                                //创建BufferedWriter对象
                                try {
                                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt",true));
                                    //输出N/A
                                    bw.write("N/A\n");
                                    bw.close();
                                } catch (IOException e){
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                        }
                        else
                        {
                            if (commands.contains(command))
                            {
                                //输出简单的比赛结果
                                core.showProjectResult(commandsMap.get(command));
                            }
                            else {
                                //创建BufferedWriter对象
                                try {
                                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt",true));
                                    //输出N/A
                                    bw.write("N/A\n");
                                    bw.close();
                                } catch (IOException e){
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                        }
                    } else {
                        //创建BufferedWriter对象
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Output.txt",true));
                            //输出error
                            bw.write("error\n");
                            bw.close();
                        } catch (IOException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }
            }
        }
        //程序运行结束
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行结束，总耗时：" + (endTime - startTime) + "ms");
    }
}
