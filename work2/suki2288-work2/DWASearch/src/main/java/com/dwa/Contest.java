package com.dwa;

import lombok.Data;
import java.util.List;
// 该类代表每位运动员单个项目单场比赛结果
@Data
public class Contest {
    private String totalPoints;
    private int rank;
    private String fullName;
    private List<String> dives;
}

