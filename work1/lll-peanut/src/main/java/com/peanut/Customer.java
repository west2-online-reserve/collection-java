package com.peanut;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitNum;
    private LocalDate lastTime;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitNum=" + visitNum +
                ", lastTime=" + lastTime +
                '}';
    }

    public LocalDate getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }

    public void addVisitNum() {
        this.visitNum++;
    }

    public Customer(String name) {
        this.name = name;
        this.visitNum = 0;
        this.lastTime = null;
    }

    public Customer(){}
}
