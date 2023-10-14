package com.huayu.work02;

import java.time.LocalDate;
//这里是顾客类，有到店时间，到店次数，和最近一次光顾时间，当然还有他的名字
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
