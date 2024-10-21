package com.java.west2.work2.shop;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int times;
    protected LocalDate recentDate;

    public Customer(String name, int times, LocalDate recentDate) {
        this.name = name;
        this.times = times;
        this.recentDate = recentDate;
    }

    public Customer(String name) {
        this.name = name;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setRecentDate(LocalDate recentDate) {
        this.recentDate = recentDate;
    }

    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public LocalDate getRecentDate() {
        return recentDate;
    }

    @Override
    public String toString(){
        return "Customer" + "\n" +
                "-----------" + "\n" +
                "name: " + name + "\n" +
                "times: " + times + "\n" +
                "recentDate: " +recentDate + "\n";
    }
}
