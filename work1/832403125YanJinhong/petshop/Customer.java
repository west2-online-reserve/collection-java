package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitcount;
    private LocalDate lastvisitdate;

    public Customer(String name, int visitCount, LocalDate lastVisitDate) {
        this.name = name;
        this.visitcount = visitCount;
        this.lastvisitdate = lastVisitDate;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitCount=" + visitcount +
                ", lastVisitDate=" + lastvisitdate +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }

    public LocalDate getLastvisitdate() {
        return lastvisitdate;
    }

    public void setLastvisitdate(LocalDate lastvisitdate) {
        this.lastvisitdate = lastvisitdate;
    }
}
