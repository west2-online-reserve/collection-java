package com.catowl.animalshop;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int numberOfVisits;
    private LocalDate latestArrivalTime;

    public Customer(String name, LocalDate localDate) {
        this.name = name;
        this.latestArrivalTime = localDate;
        this.numberOfVisits = 1;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setNumberOfVisits() {
        this.numberOfVisits += 1;
    }

    public void setLatestArrivalTime(LocalDate localDate) {
        latestArrivalTime = localDate;
    }


    @Override
    public String toString() {
        return "顾客的名字：" + getName() + " 到店次数：" + getNumberOfVisits();
    }
}
