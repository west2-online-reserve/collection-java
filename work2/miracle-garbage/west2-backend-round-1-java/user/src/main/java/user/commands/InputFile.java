package user.commands;

import user.lib.CommandList;
import user.lib.CommandParam;
import user.exceptions.ParamException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class InputFile {
    /*
        指令参数首个文件的情况
     */

    public static void execute(CommandParam param, ArrayDeque<CommandParam> commandQueue, CommandList commandList) throws UncheckedIOException {
        // 读取文件，将里面的内容每行为一个命令读取构造依次加入队列,不检查内部指令合法性,文件不存在抛出异常即可

        // 先检查指令本身合法性
        if (param.size() > 2) throw new ParamException("参数过多");
        if (param.size() == 2) { // 第二参数只能填写文件
            if (commandList.isCommand(param.getArg(1)) || !commandList.isLegal(param.getArg(1))) throw new ParamException("第二个文件参数错误");
        }

        try {
            for (String line : Files.readAllLines(Path.of(param.getFirstArg()))) {
                // 跳过空行
                if (line.isBlank()) {
                    continue;
                }
                // 一行按空白切分成多个参数，构造为一条指令加入队列末尾
                if (param.size() == 2) { // 如果后面有文件，每个指令后面加上"-f <filename>"
                    ArrayList<String> tmp = new ArrayList<>(List.of(line.trim().split("\\s+")));
                    tmp.add("-f");
                    tmp.add(param.getArg(1));
                    commandQueue.addLast(new CommandParam(tmp));
                } else {
                    commandQueue.addLast(new CommandParam(new ArrayList<>(List.of(line.trim().split("\\s+")))));
                }
            }
        } catch (IOException e) {
            // 文件不存在等读取失败的情况直接抛出
            throw new UncheckedIOException(e);
        } catch (InvalidPathException e) {
            // 文件路径可能异常
            throw e;
        }
    }
}
