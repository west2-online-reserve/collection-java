package user.app;

import user.exceptions.ParamException;
import core.exceptions.ReadJsonException;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Application app = appInit();
            commandRun(app,args);
        } catch (ParamException e) {
            System.exit(0);
        } catch (Exception e) {
            // e.printStackTrace();
            System.exit(1); // 未知其他异常退出
        }
    }

    // 程序初始化 读取源数据包
    public static Application appInit() throws ParamException {
        try {
            // 读取数据
            Application app = new Application();
            app.loadData();

            return app;
        } catch (ReadJsonException ex) {
            System.out.println(ex.getMessage() + "，请检查文件是否存在以及路径是否正确");
            throw new ParamException("程序初始化失败");
        }
    }

    // 执行指令
    public static void commandRun(Application app,String[] args) throws ParamException {
        try {
            ArrayList<String> argList = new ArrayList<>(List.of(args));
            CommandManager cmd = new CommandManager(argList);

            cmd.commandManager(app.getData());
        } catch (ParamException ex) {
            System.out.println("Error");
        } catch (UncheckedIOException e) {
            System.out.println("指定文件读取失败");
        }
    }

}



