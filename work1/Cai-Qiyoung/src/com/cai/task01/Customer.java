package com.cai.task01;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitTime;
    private String ID; //用来唯一确定一个顾客

    private static final DateTimeFormatter CHINESE_FORMAT = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    public Customer(String ID, String name, int count, LocalDate lastVisitTime) {
        this.ID = ID;
        this.name = name;
        this.visitCount = count;
        this.lastVisitTime = lastVisitTime;
    }

    public Customer(String ID, String name) {
        this.ID = ID;
        this.name = name;
        this.visitCount = 0;
        this.lastVisitTime = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(LocalDate lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    @Override
    public String toString() {
        String formattedDate = lastVisitTime.format(CHINESE_FORMAT);
        return String.format("Customer{ID=%s , 姓名='%s', 到店次数=%d, 最新到店时间=%s}", ID ,name, visitCount, formattedDate);
    }
}
