package com.sty;

import java.time.LocalDate;

public class Customer {
    private String name ;
    private int ArriveTime;
    private LocalDate time ;

    public Customer(String name, int arriveTime, LocalDate time) {
        this.name = name;
        ArriveTime = arriveTime;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(int arriveTime) {
        ArriveTime = arriveTime;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", ArriveTime=" + ArriveTime +
                ", time=" + time +
                '}';
    }
}
