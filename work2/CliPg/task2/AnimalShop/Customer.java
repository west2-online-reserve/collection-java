package com.PeanutJava.task2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int nums;
    private LocalDate latestArrivedTime;


    public Customer() {
    }

    public Customer(String name, int LocalNums) {
        this.name = name;
        this.nums = LocalNums;
        this.latestArrivedTime = LocalDate.now();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int LocalNums) {
        this.nums = LocalNums;
    }

    public LocalDate getTime() {
        return latestArrivedTime;
    }

    public void setTime(LocalDate latestArrivedTime) {
        this.latestArrivedTime = latestArrivedTime;
    }

    @Override
    public String toString() {
        return "Customer{name = " + name + ", LocalNums = " + nums + ", latestArrivedTime = " + latestArrivedTime + "}";
    }
}
