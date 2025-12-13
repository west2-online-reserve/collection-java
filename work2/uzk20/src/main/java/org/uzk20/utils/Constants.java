package org.uzk20.utils;

import java.util.HashSet;
import java.util.Set;


public class Constants {

    public static final Set<String> EVENT_NAMES = new HashSet<>();
    // 分隔符
    public static final String SEPARATOR = "-----\n";
    // 指令关键字
    public static final String CMD_PLAYERS = "players";
    public static final String CMD_RESULT = "result";
    // 阶段
    public static final String STAGE_PRELIMINARY = "Preliminary";
    public static final String STAGE_SEMIFINAL = "Semifinal";
    public static final String STAGE_FINAL = "Final";
    // 占位符（无成绩/未参赛）
    public static final String PLACEHOLDER = "*";
    // 性别
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";

    static{
        EVENT_NAMES.add("women 1m springboard");
        EVENT_NAMES.add("women 3m springboard");
        EVENT_NAMES.add("women 10m platform");
        EVENT_NAMES.add("women 3m synchronised");
        EVENT_NAMES.add("women 10m synchronised");
        EVENT_NAMES.add("men 1m springboard");
        EVENT_NAMES.add("men 3m springboard");
        EVENT_NAMES.add("men 10m platform");
        EVENT_NAMES.add("men 3m synchronised");
        EVENT_NAMES.add("men 10m synchronised");
    }
}
