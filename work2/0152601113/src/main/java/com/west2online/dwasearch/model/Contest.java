package com.west2online.dwasearch.model;

import java.util.List;

/**
 * 一个选手（或一对双人选手）在某项目决赛中的成绩。
 *
 * 对应 data.json 顶层 results 里某个项目数组中的一个元素，例如：
 *   { "rank": 1, "fullName": "CHEN Jia",
 *     "dives": ["73.50", "74.40", "76.50", "72.00", "78.00"],
 *     "totalPoints": "374.40" }
 *
 * 分数统一存成字符串，输出时再用 BigDecimal 保证两位小数（作业要求，
 * 不要直接用二进制浮点数做小数运算）。
 */
public class Contest {

    private int rank;              // 排名
    private String fullName;       // 姓名（双人项目形如 "A / B"）
    private List<String> dives;    // 每一跳的得分，按顺序
    private String totalPoints;    // 总分

    public Contest() {
    }

    public Contest(int rank, String fullName, List<String> dives, String totalPoints) {
        this.rank = rank;
        this.fullName = fullName;
        this.dives = dives;
        this.totalPoints = totalPoints;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getDives() {
        return dives;
    }

    public void setDives(List<String> dives) {
        this.dives = dives;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
}
