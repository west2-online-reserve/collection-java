package com.huayu.work02;

/**
 * Customer类代表顾客的基本信息
 * <p>
 * 该类包含顾客的基本信息和重写的toString方法
 */

import java.time.LocalDate;

public class Customer {
    protected String customerName;
    private int arrivalTime;
    protected LocalDate latestArrivalTime;

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        if (arrivalTime >= 1) {
            this.arrivalTime = arrivalTime;
        } else {
            System.out.println("到店次数有误");
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName=" + customerName +
                ", arrivalTime=" + getArrivalTime() +
                ", latestArrivalTime=" + latestArrivalTime +
                '}';
    }
}
