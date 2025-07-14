package com.dwa;

import java.util.List;

public interface CoreModule {

    // 查询所有选手的基本信息
    void displayAllPlayersInfo();

    // 查询指定比赛项目的决赛结果并输出
    void displayResults(String contestName);

    // 查询指定比赛类型的比赛结果并返回（0: Preliminary, 1: SemiFinal, 2: Final）
    void displayResults(String contestName, IOHelper ioHelper);

    // 查询指定比赛项目所有阶段的详细结果并输出
    void displayDetailedResults(String contestName);
}


