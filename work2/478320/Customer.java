package com.huayu.work02;

/**
 * Customer类代表顾客的基本信息
 *
 * 该类包含顾客的基本信息和重写的toString方法
 */
import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int arrivalTime;
    private LocalDate latestArrivalTime;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDate latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", latestArrivalTime=" + latestArrivalTime +
                '}';
    }
}
