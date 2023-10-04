package com.huayu.work02;

import java.time.LocalDate;

public class Customer {
    String customerName;
    int arrivalTime;
    LocalDate latestArrivalTime;


    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", latestArrivalTime=" + latestArrivalTime +
                '}';
    }
}
