package user.commands;

import core.data.JsonData;
import core.exceptions.DataPathException;
import core.lib.ReadData;
import user.exceptions.ParamException;
import user.lib.CommandParam;
import user.lib.FileCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * config 用于调整设置的指令。
 * <p>
 *     config --setpath &lt;dir&gt;  设置数据读取路径为 dir
 *     config --setpath-status default/design
 * </p>
 *
 * @author miracle-garbage
 * @date 2026-09-17
 */
public class Config extends Command {
    private static final String SET_PATH = "--setpath";
    private static final String SET_PATH_STATUS = "--setpath-status";
    private static final String STATUS_DEFAULT = "default";
    private static final String STATUS_DESIGN = "design";
    private static final int OPTION_ARG_SIZE = 2;

    private final Map<String, Fn> commandFns;

    public Config() {
        super("config");

        // 注册所有支持的配置参数
        commandFns = new HashMap<>();
        commandFns.put(SET_PATH, dataPack -> changeDataPath(dataPack.arg));
        commandFns.put(SET_PATH_STATUS, dataPack -> changeDataPathStatus(dataPack.arg));
    }

    @Override
    public void execute(JsonData data, CommandParam param, FileCache fileCache) throws ParamException {
        ArrayList<CommandParam> paramSliceList = param.paramSlice();

        if (paramSliceList.isEmpty()) {
            throw new ParamException("config参数错误：缺少配置参数");
        }

        // config 没有位置参数，每个切片都必须是“功能参数 + 参数值”
        for (CommandParam item : paramSliceList) {
            String argName = item.getFirstArg();
            if (!commandFns.containsKey(argName)) {
                throw new ParamException("config参数错误：不支持的参数 " + argName);
            }
            if (item.size() != OPTION_ARG_SIZE) {
                throw new ParamException("config参数错误：" + argName + "需要且仅需要一个参数值");
            }
            if (SET_PATH_STATUS.equals(argName)) {
                validateStatus(item.getArg(1));
            }
        }

        for (CommandParam item : paramSliceList) {
            DataPack dataPack = new DataPack(data, fileCache, item.getArg(1));
            commandFns.get(item.getFirstArg()).apply(dataPack);
        }
    }

    @Override
    public void displayOnTerminal(Object data) {
        throw new UnsupportedOperationException("config指令不支持终端输出");
    }

    @Override
    public void writeToFile(Object data, FileCache fileCache, String filename) {
        throw new UnsupportedOperationException("config指令不支持文件输出");
    }

    private void changeDataPath(String path) {
        try {
            ReadData.changePath(path);
        } catch (DataPathException ex) {
            throw new ParamException(ex.getMessage());
        }
    }

    private void changeDataPathStatus(String status) {
        if (STATUS_DEFAULT.equals(status)) {
            ReadData.changeStatusDefault();
        } else {
            ReadData.changeStatusDesign();
        }
    }

    private void validateStatus(String status) {
        if (!STATUS_DEFAULT.equals(status) && !STATUS_DESIGN.equals(status)) {
            throw new ParamException("config参数错误：status只能为default或design");
        }
    }
}
