package com.west2.work2;

import java.time.LocalDate;

public class ShopClosedException extends RuntimeException {
    /**
     * 店未开的时间
     */
    private LocalDate time;

    public ShopClosedException(LocalDate time) {
        this.time = time;
    }

    public String toString() {
        return this.time + ":本店仍为歇业状态！";
    }
}
