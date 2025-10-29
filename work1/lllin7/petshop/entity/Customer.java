package com.petshop.entity;

import java.time.LocalDate;

class Customer {
    private final String name;
    private int frequency;
    private LocalDate latestArrivalTime;

    public Customer(String name, int frequency, LocalDate latestArrivalTime) {
        this.name = name;
        this.frequency = frequency;
        this.latestArrivalTime = latestArrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDate latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    @Override
    public String toString() {
        return "顾客" + " 姓名：" + getName() + " 到店次数：" + getFrequency() + " 最新到店时间：" + getLatestArrivalTime();
    }
}
