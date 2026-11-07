package user.app;

import core.lib.DataOutput;
import core.lib.OutputFormat;
import user.commands.InputFile;
import user.exceptions.EventNotFound;
import user.lib.CommandList;
import user.lib.CommandParam;
import user.lib.FileCache;
import core.data.JsonData;
import user.exceptions.ParamException;

import java.io.BufferedWriter;
import java.io.UncheckedIOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class CommandManager {
    // 具体命令
    private final CommandParam args;

    public CommandManager(ArrayList<String> args) {
        this.args = new CommandParam(args);
    }

    // 命令转发 只检查第一个参数
    public void commandManager(JsonData data) throws ParamException {
        /*
            维护一个指令队列。
            先根据第一个参数判断是指令还是文件（且判断合法性，非法参数直接输出Error）
            如果是文件：
                尝试读取文件里的所有指令并依次添加到指令队列里
            如果是指令：
                将这个指令添加到指令队列即可

            最后：根据队列迭代执行所有指令
         */

        // 指令队列：每个元素是一条指令的参数列表
        CommandList commandList = new CommandList();
        ArrayDeque<CommandParam> commandQueue = new ArrayDeque<>();

        if (args.isEmpty()) {
            throw new ParamException("无任何参数输入");
        }

        // 指令的检查与提取信息
        if (commandList.isLegal(args.getFirstArg())) {
            if (commandList.isCommand(args.getFirstArg())) {
                commandQueue.addLast(args);
            } else {
                // 读取文件，将里面的内容每行为一个命令读取构造依次加入队列,文件不存在抛出异常即可
                InputFile.execute(args,commandQueue,commandList);
            }
        } else {
            throw new ParamException("首个参数错误");
        }

        // 构建文件池
        FileCache fileCache = new FileCache();

        // 迭代执行指令，每个都进行合法检查
        for (CommandParam argLine : commandQueue) {
            try {
                if (!commandList.isCommand(argLine.getFirstArg()) || !commandList.isLegal(argLine.getFirstArg())) { // 文件内指令不支持再次为input.txt文件指令
                    // throw new ParamException("首个参数错误");
                    /*
                        将参数错误处理为：
                            有文件参数：
                                写Error进文件
                            无文件参数：
                                终端打印Error
                     */
                    ArrayList<CommandParam> paramSliceList =  argLine.paramSlice();
                    boolean fileInput = false;
                    String filename = null;
                    for (CommandParam p : paramSliceList) {
                        if (p.getFirstArg().equals("-f")) {
                            fileInput = true;
                            filename = (p.size() < 2 ? null : p.getArg(1));
                            break;
                        }
                    }

                    if (fileInput) {
                        BufferedWriter w = fileCache.createFileWriter(filename);
                        OutputFormat.writeError(w);
                        System.out.printf("文件 %s 写入成功\n",filename);
                    } else {
                        OutputFormat.displayError();
                    }
                } else {
                    // 指令转发
                    commandList.getCommandMap().get(argLine.getFirstArg()).execute(data,new CommandParam(argLine,1),fileCache);
                }
            } catch (ParamException ex) {
                System.out.println("ParameterError");
            } catch (UncheckedIOException | IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        fileCache.clear();
    }

}
