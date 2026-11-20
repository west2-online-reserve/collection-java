package com.west2online.dwasearch.core;

import com.west2online.dwasearch.model.CompetitionData;

/**
 * 解析一行命令，并返回该命令对应的输出（含 Error / N/A 的处理）。
 *
 * 命令规则（区分大小写、不自动去空格）：
 *   - 恰好 "players"                                    -> 输出全部选手
 *   - 恰好 "result"                                     -> N/A（缺项目名）
 *   - "result " 开头，后面是 8 个项目之一               -> 输出该项目决赛结果
 *   - "result " 开头，后面不是合法项目                  -> N/A
 *   - 其它一切                                          -> Error
 *   - 空行（含纯空白行）                                 -> 忽略（不输出）
 */
public class CommandProcessor {

    private static final String PLAYERS = "players";
    private static final String RESULT = "result";

    private final QueryService queryService;

    public CommandProcessor(CompetitionData data) {
        this.queryService = new QueryService(data);
    }

    /** 处理一行命令，返回输出（结果行都以 ----- 结尾）。 */
    public String process(String line) {
        if (line.isBlank()) {
            return "";
        }

        if (PLAYERS.equals(line)) {
            return queryService.formatPlayers();
        }

        if (RESULT.equals(line)) {
            return error("N/A");
        }

        if (line.startsWith(RESULT + " ")) {
            String event = line.substring(RESULT.length() + 1);
            if (Events.NAME_SET.contains(event)) {
                return queryService.formatResult(event);
            }
            return error("N/A");
        }

        return error("Error");
    }

    private String error(String message) {
        return message + "\n-----\n";
    }
}
