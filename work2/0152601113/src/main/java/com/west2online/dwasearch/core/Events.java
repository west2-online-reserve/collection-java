package com.west2online.dwasearch.core;

import java.util.List;
import java.util.Set;

/**
 * 本轮固定的 8 个查询关键字（唯一真源）。
 *
 * 同时被 DataLoader（校验 data.json 是否齐全）和 CommandProcessor（判断
 * result 后面的项目名是否合法）使用，避免两处各写一份导致不一致。
 */
public final class Events {

    public static final List<String> NAMES = List.of(
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    );

    public static final Set<String> NAME_SET = Set.copyOf(NAMES);

    private Events() {
        // 工具类，禁止实例化
    }
}
