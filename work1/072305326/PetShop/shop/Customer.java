package com.cyx.Amimals;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitTime;

    public Customer(String name) {
        this.name = name;
        this.visitCount = 1;
        this.lastVisitTime = LocalDate.now();
    }
    // setter

    public void setVisitCount() {
        this.visitCount++;
    }
    public void setLastVisitTime(LocalDate lastVisitTime){
        this.lastVisitTime = lastVisitTime;
    }
    // getter
    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public LocalDate getLastVisitTime() {
        return lastVisitTime;
    }

    @Override
    public String toString() {
        return "顾客名字是：" + name + "，访问次数为：" +
                visitCount + "，上次访问时间为：" + lastVisitTime;
    }
}
