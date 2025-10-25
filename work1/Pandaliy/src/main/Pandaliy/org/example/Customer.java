package org.example;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;
    private LocalDate LatestTime;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String message;
        int year = LatestTime.getYear();
        int month = LatestTime.getMonthValue();
        int day = LatestTime.getDayOfMonth();
        message = "客户" + name + "总共的到店次数为" + times + ",最新到店时间为" + year + "年" +
                month + "月" + day + "日";
        return message;
    }

    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public LocalDate getLatestTime() {
        return LatestTime;
    }

    public void addVisitTimes() {
        times++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setLatestTime(LocalDate latestTime) {
        LatestTime = latestTime;
    }
}
