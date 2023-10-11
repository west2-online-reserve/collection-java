package com.west2.work2;

import java.time.LocalDate;

public class ShopClosed extends RuntimeException {
    private LocalDate time;

    public ShopClosed(LocalDate time) {
        this.time = time;
    }

    public String toString() {
        return this.time + ":本店仍为歇业状态！";
    }
}
