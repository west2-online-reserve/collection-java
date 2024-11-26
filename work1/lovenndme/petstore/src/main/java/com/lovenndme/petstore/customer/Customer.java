package com.lovenndme.petstore.customer;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int frequency;
    private LocalDate time;

    public Customer(String name, int frequency, LocalDate time) {
        this.name = name;
        this.frequency = frequency;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public void increaseFrequency() {
        this.frequency++;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", frequency=" + getFrequency() +
                ", time=" + getTime() +
                '}';
    }
}
