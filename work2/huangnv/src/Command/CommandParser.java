package Command;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandParser {

    // ===== 业务相关配置（扩展时修改此处） =====
    // 有效的项目名称列表（10个固定项目）
    private static final Set<String> VALID_EVENT_NAMES = new HashSet<>();

    static {
        VALID_EVENT_NAMES.add("Women 1m Springboard");
        VALID_EVENT_NAMES.add("Women 3m Springboard");
        VALID_EVENT_NAMES.add("Women 10m Platform");
        VALID_EVENT_NAMES.add("Women 3m Synchronised");
        VALID_EVENT_NAMES.add("Women 10m Synchronised");
        VALID_EVENT_NAMES.add("Men 1m Springboard");
        VALID_EVENT_NAMES.add("Men 3m Springboard");
        VALID_EVENT_NAMES.add("Men 10m Platform");
        VALID_EVENT_NAMES.add("Men 3m Synchronised");
        VALID_EVENT_NAMES.add("Men 10m Synchronised");
    }
    // ==========================================

    /**
     * 解析输入文件，返回 Command 列表
     * @param inputPath 输入文件路径
     * @return Command 对象列表
     * @throws IOException 文件读取异常
     */
    public List<Command> parse(String inputPath) throws IOException {
        // 1. 读取文件所有行
        List<String> lines = Files.readAllLines(Paths.get(inputPath), StandardCharsets.UTF_8);

        // 2. 创建结果列表
        List<Command> commands = new ArrayList<>();

        // 3. 遍历每一行，解析成 Command 对象
        for (int i = 0; i < lines.size(); i++) {
            int lineNumber = i + 1;  // 行号从 1 开始
            String line = lines.get(i);
            Command cmd = parseLine(line, lineNumber);
            commands.add(cmd);
        }
        return commands;
    }

    /**
     * 解析单行命令字符串
     * @param line 命令字符串
     * @param lineNumber 行号
     * @return 对应的 Command 对象
     */
    private Command parseLine(String line, int lineNumber) {
        // 去除首尾空格
        String trimmed = line.trim();

        // 空行处理（当作错误命令）
        if (trimmed.isEmpty()) {
            return new ErrorCommand(lineNumber, line);
        }

        // 按空格分割
        String[] parts = trimmed.split("\\s+");
        String firstWord = parts[0];  // 严格按照输入的大小写

        // 判断命令类型（严格匹配小写）
        if (firstWord.equals("players")) {
            // players 命令
            if(parts.length==1) {
                return new PlayerCommand(lineNumber, line);
            }
            else{
                return new ErrorCommand(lineNumber, line);
            }

        } else if (firstWord.equals("result")) {
            // result 命令，需要进一步解析
            return parseResultCommand(parts, lineNumber, line);

        } else {
            // 无法识别的命令（包括大小写不匹配的情况，如 "PLAYERS" 或 "Result"）
            return new ErrorCommand(lineNumber, line);
        }
    }

    /**
     * 解析 result 命令
     * @param parts 分割后的单词数组
     * @param lineNumber 行号
     * @param rawCommand 原始命令字符串
     * @return ResultCommand 对象
     */
    private Command parseResultCommand(String[] parts, int lineNumber, String rawCommand) {
        // result 命令至少有 2 个部分：result + 项目名
        if (parts.length < 2) {
            // 格式错误：只有 "result" 没有项目名
            return new ErrorCommand(lineNumber, rawCommand);
        }

        // 检查最后一个词是否是 "detail"（严格区分大小写）
        boolean isDetail = false;
        int endIndex = parts.length;  // 项目名的结束位置

        if (parts[parts.length - 1].equals("detail")) {
            isDetail = true;
            endIndex = parts.length - 1;  // 项目名不包含最后的 "detail"
        }

        // 提取项目名：从 parts[1] 到 parts[endIndex-1]
        StringBuilder eventNameBuilder = new StringBuilder();
        for (int i = 1; i < endIndex; i++) {
            if (i > 1) {
                eventNameBuilder.append(" ");
            }
            eventNameBuilder.append(parts[i]);
        }
        String eventName = eventNameBuilder.toString();  // 保持原始输入

        // 严格验证项目名是否有效（区分大小写）
        if (!isValidEventName(eventName)) {
            // 项目名无效（包括大小写不匹配），设置为 "N/A"
            return new ResultCommand(lineNumber, rawCommand, "N/A", isDetail);
        }

        // 项目名有效且大小写完全匹配，返回正常的 ResultCommand
        return new ResultCommand(lineNumber, rawCommand, eventName, isDetail);
    }

    /**
     * 验证项目名是否在有效列表中（严格区分大小写）
     * @param eventName 项目名称
     * @return 是否有效
     */
    private boolean isValidEventName(String eventName) {
        // 严格匹配，不转换大小写
        return VALID_EVENT_NAMES.contains(eventName);
    }
}
