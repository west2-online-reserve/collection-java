package org.uzk20.utils;

import java.util.Locale;

public class Command {
    // 指令的类型分为三种
    public enum CmdType { PLAYERS, RESULT, INVALID }

    private CmdType cmdType;
    private String eventName;
    private boolean isDetail;
    private boolean isLegal;
    private String errorMsg;

    private Command(){}

    static public Command parseCommand(String line) {
        Command cmd = new Command();
        String newString = line.trim();// 删掉空格
        // 如果是查询运动员
        if (newString.equals(Constants.CMD_PLAYERS)) {
            cmd.cmdType = CmdType.PLAYERS;
            cmd.isLegal = true;
            return cmd;
        }
        // 查询比赛结果
        if (newString.startsWith(Constants.CMD_RESULT + " ")) {
            cmd.cmdType = CmdType.RESULT;
            String[] parts = newString.split("\\s+");
            int endIndex = parts.length;
            cmd.isDetail = false;

            // 先校验是否有不合法的后缀
            if (parts.length > 2 && !parts[parts.length - 1].equals("detail")) {
                cmd.isLegal = false;
                cmd.errorMsg = "N/A";
                return cmd;
            }

            // 处理detail后缀
            if (parts.length > 1 && parts[parts.length - 1].equals("detail")) {
                cmd.isDetail = true;
                endIndex = parts.length - 1;
            }

            // 拼接项目名
            StringBuilder eventNameSb = new StringBuilder();
            for (int i = 1; i < endIndex; i++) {
                if (i > 1) eventNameSb.append(" ");
                eventNameSb.append(parts[i]);
            }
            String eventName = eventNameSb.toString();

            // 项目名是否合法
            if (Constants.EVENT_NAMES.contains(eventName)) {
                cmd.eventName = eventName;
                cmd.isLegal = true;
            } else {
                cmd.isLegal = false;
                cmd.errorMsg = "N/A";
            }

            return cmd;
        }

        // 有指令但是无效的情况处理
        cmd.cmdType = CmdType.INVALID;
        cmd.isLegal = false;
        cmd.errorMsg = "Error";
        return cmd;
    }

    public CmdType getCmdType() {
        return cmdType;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}