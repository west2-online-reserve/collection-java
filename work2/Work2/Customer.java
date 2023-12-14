package com.WestTwo.work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;
    private LocalDate date;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", times=" + times +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
