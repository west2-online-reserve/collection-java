package user.lib;

import user.commands.Command;
import user.commands.Config;
import user.commands.Players;
import user.commands.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandList {
    // 命令注册表
    private final Map<String, Command> commandMap = new HashMap<>();
    // 支持的文件扩展名
    private final ArrayList<String> extensions = new ArrayList<>();

    public CommandList() {
        Players players = new Players();
        Result result = new Result();
        Config config = new Config();
        commandMap.put(players.getName(), players);
        commandMap.put(result.getName(),result);
        commandMap.put(config.getName(), config);
        extensions.add(".txt");
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public ArrayList<String> getExtensions() {
        return extensions;
    }

    // 判断首个参数的合法性 (仅通过点号分辨是文件还是指令，然后是否存在于指令注册表中)
    public boolean isLegal(String arg) {
        if (arg == null || arg.isEmpty()) {
            return false;
        }
        // 如果是文件查看扩展名是否在extensions中
        if (isFile(arg)) {
            int dotIndex = arg.lastIndexOf('.');
            String extension = arg.substring(dotIndex);
            return extensions.contains(extension);
        }
        // 如果是命令查看是否在commandMap的key中
        return commandMap.containsKey(arg);
    }

    // 判断是否是文件(如果是文件可能是整个路径)
    public boolean isFile(String arg) {
        return !isCommand(arg);
    }

    // 判断是否是命令
    public boolean isCommand(String arg) {
        // 只看有没有"."
        return !arg.contains(".");
    }
}
