// 命令解析器 —— 负责将用户输入的每一行文本解析为可执行的命令，
// 支持 "players"（查询所有选手）和 "result <event>"（查询赛事结果）两类命令；
// 需处理非法输入：无法识别的命令返回 ERROR，result 后项目名不合法返回 NA，
// result 命令附加多余字符也返回 NA；所有命令区分大小写，全部小写。
package com.dwa.core;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class CommandParser {

    public static final String PLAYERS_COMMAND = "players";
    public static final String RESULT_PREFIX = "result";
    public static final String ERROR = "Error";
    public static final String NA = "N/A";

    private static final Set<String> VALID_EVENTS = new HashSet<>(Arrays.asList(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    ));

    public enum CommandType {
        PLAYERS,
        RESULT,
        ERROR,
        NA
    }

    public static class ParsedCommand {
        private final CommandType type;
        private final String eventKey; // 仅 RESULT 类型有效，如 "result women 3m springboard"

        public ParsedCommand(CommandType type, String eventKey) {
            this.type = type;
            this.eventKey = eventKey;
        }

        public CommandType getType() { return type; }
        public String getEventKey() { return eventKey; }
    }

    /**
     * 解析一行输入命令。
     * @param line 输入行（已去除首尾空白）
     * @return 解析结果，包含命令类型和对应的赛事 key
     */
    public static ParsedCommand parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new ParsedCommand(CommandType.ERROR, null);
        }

        String trimmed = line.trim();

        if (PLAYERS_COMMAND.equals(trimmed)) {
            return new ParsedCommand(CommandType.PLAYERS, null);
        }

        if (trimmed.startsWith(RESULT_PREFIX)) {
            String rest = trimmed.substring(RESULT_PREFIX.length());
            // "result" 后面必须紧跟一个空格
            if (rest.isEmpty() || rest.charAt(0) != ' ') {
                return new ParsedCommand(CommandType.ERROR, null);
            }
            String eventPart = rest.trim();
            // 检查是否为合法赛事名称（精确匹配，不允许额外字符）
            if (VALID_EVENTS.contains(eventPart)) {
                String fullCommand = RESULT_PREFIX + " " + eventPart;
                return new ParsedCommand(CommandType.RESULT, fullCommand);
            } else {
                return new ParsedCommand(CommandType.NA, null);
            }
        }

        return new ParsedCommand(CommandType.ERROR, null);
    }
}
