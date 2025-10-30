package com.Anna_west2_work01.animalShop.customer;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitTimes;
    private LocalDate latestVisit;

    public Customer() {}

    public Customer(String name, int visitTimes, LocalDate latestVisit) {
        this.name = name;
        this.visitTimes = visitTimes;
        this.latestVisit = latestVisit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public LocalDate getLatestVisit() {
        return latestVisit;
    }

    public void setLatestVisit(LocalDate latestVisit) {
        this.latestVisit = latestVisit;
    }

    @Override
    public String toString() {
        return "Customer : " +
                "name = " + name + "\n" +
                "visitTimes = " + visitTimes + "\n" +
                "latestVisit = " + latestVisit + "\n";
    }
}

