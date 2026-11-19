// 比赛结果条目模型 —— 对应 data.json 中每个 event.results 数组的元素，
// 包含 fullName（选手/组合名）、rank（排名）、countryCode（国籍代码）、
// scores（各轮得分，JSON 中为字符串列表）、totalPoints（总分，字符串）。
// 格式化输出时需将 scores 用 BigDecimal 精确相加，避免浮点误差。
package com.dwa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultEntry {
    private String fullName;
    private int rank;
    private String countryCode;
    private List<String> scores;
    private String totalPoints;

    public ResultEntry() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public List<String> getScores() { return scores; }
    public void setScores(List<String> scores) { this.scores = scores; }

    public String getTotalPoints() { return totalPoints; }
    public void setTotalPoints(String totalPoints) { this.totalPoints = totalPoints; }
}
