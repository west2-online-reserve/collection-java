package user.commands;

import core.data.Event;
import core.lib.Collector;
import core.lib.OutputFormat;
import user.exceptions.EventNotFound;
import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Result extends Command {
    private Map<String,Fn> commandFns;

    public Result() {
        super("result");

        // 注册所有支持的-n功能参数

        commandFns = new HashMap<>();
        commandFns.put("-f",d -> {writeToFile(d.data,d.fileCache, d.arg);});
        commandFns.put("--default-output",dataPack -> {displayOnTerminal(dataPack.data);});
    }

    @Override
    public void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException {
        // result <event> [[--detail] -f filename]
        // 未找到比赛不作为异常，而是内容变为 N/A

        ArrayList<CommandParam> paramSliceList =  param.paramSlice();
        Event target;
        // 头参数是比赛名
        CommandParam first = paramSliceList.get(0);
        if (first.getFirstArg().startsWith("-")) {
            // 证明无定位比赛的名字参数
            target = null;
        } else if (first.size() != 3) {
            target = null; // 未找到
        } else {
            target = Collector.findEvent(
                    data,
                    first.getArg(0),
                    first.getArg(1),
                    first.getArg(2)
            );
        }

        // 迭代执行各个参数指令，输出指令总在最后
        paramSliceList.removeFirst();
        ArrayList<String> end = new ArrayList<String>();
        end.add("--default-output");
        paramSliceList.addLast(new CommandParam(end));

        for (CommandParam item : paramSliceList) {
            DataPack d;
            if (item.size() == 1) {
                d = new DataPack(target,fileCache,null);
            } else {
                d = new DataPack(target,fileCache,item.getArg(1));
            }

            try {
                commandFns.get(item.getFirstArg()).apply(d);
                if (
                        item.getFirstArg().equals("-f") ||
                        item.getFirstArg().equals("--default-output")
                ) {break;}
            } catch (NullPointerException ex) {
                throw new ParamException("指令参数错误或未注册");
            }
        }

    }

    @Override
    public void writeToFile(Object data, FileCache fileCache, String filename) {
        if (data == null || data instanceof Event) {
            BufferedWriter writer = fileCache.createFileWriter(filename);
            OutputFormat.writeResultPlayerFormat((Event) data,writer);
            System.out.printf("文件 %s 写入成功\n",filename);
        } else {
            throw new RuntimeException("读取的数据与预定格式不匹配");
        }
    }

    @Override
    public void displayOnTerminal(Object data) {
        if (data == null || data instanceof Event) {
            OutputFormat.displayEventResult((Event) data);
        }
        else {
            throw new RuntimeException("读取的数据与预定格式不匹配");
        }
    }
}
