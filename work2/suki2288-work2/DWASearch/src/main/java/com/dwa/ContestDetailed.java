package com.dwa;

import lombok.Data;

@Data
public class ContestDetailed {
    // 0: Preliminary, 1: Semifinal, 2: Final
    private Contest[] contests = new Contest[3];
}