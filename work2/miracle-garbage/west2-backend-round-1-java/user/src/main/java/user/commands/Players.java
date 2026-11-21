package user.commands;

import core.data.JsonData;
import core.lib.OutputFormat;
import user.exceptions.ParamException;
import user.lib.FileCache;
import user.lib.CommandParam;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Players extends Command {
    private Map<String,Fn> commandFns;

    public Players() {
        super("players");

        // 注册所有支持的功能参数
        commandFns = new HashMap<>();
        commandFns.put("-f",d -> {writeToFile(d.data,d.fileCache, d.arg);});
        commandFns.put("--default-output",dataPack -> {displayOnTerminal(dataPack.data);});
    }

    @Override
    public void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException {
        // players [-f filename]

        ArrayList<CommandParam> paramSliceList = param.paramSlice();

        // players 没有位置参数，因此每个切片都必须由已注册的功能参数开头
        for (CommandParam item : paramSliceList) {
            String argName = item.getFirstArg();

            if (!commandFns.containsKey(argName)) {
                throw new ParamException("players参数错误：不支持的参数 " + argName);
            }
            if (item.size() > 2) {
                throw new ParamException("players参数错误：参数过多");
            }
            // -f 是唯一需要带文件名的功能参数
            if (item.size() == 1 && "-f".equals(argName)) {
                throw new ParamException("players参数错误：-f后缺少文件名");
            }
        }

        // 迭代执行各个参数指令，输出指令总在最后
        ArrayList<String> end = new ArrayList<String>();
        end.add("--default-output");
        paramSliceList.addLast(new CommandParam(end));

        for (CommandParam item : paramSliceList) {
            DataPack d;
            if (item.size() == 1) {
                d = new DataPack(data,fileCache,null);
            } else {
                d = new DataPack(data,fileCache,item.getArg(1));
            }

            commandFns.get(item.getFirstArg()).apply(d);
            // 输出指令执行完即结束，-f写入文件后不再向终端输出
            if (
                    item.getFirstArg().equals("-f") ||
                    item.getFirstArg().equals("--default-output")
            ) {break;}
        }
    }

    @Override
    public void writeToFile(Object data, FileCache fileCache, String filename) {
        if (data instanceof JsonData) {
            BufferedWriter fileWriter = fileCache.createFileWriter(filename);
            // 将输出内容写入文件
            OutputFormat.writeAllPlayersInfo((JsonData) data,fileWriter);
            System.out.printf("文件 %s 写入成功\n",filename);
        } else {
            throw new RuntimeException("读取的数据与预定格式不匹配");
        }
    }

    @Override
    public void displayOnTerminal(Object data) {
        if (data instanceof JsonData) {
            OutputFormat.displayAllPlayersInfo((JsonData) data);
        } else {
            throw new RuntimeException("读取的数据与预定格式不匹配");
        }
    }
}
