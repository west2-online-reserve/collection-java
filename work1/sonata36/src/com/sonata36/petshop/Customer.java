package com.sonata36.petshop;

import java.time.LocalDate;

/** 顾客及其最近一次到店的信息。 */
public final class Customer {
    private final String name;
    private int visitCount;
    private LocalDate lastVisitDate;

    public Customer(String name) {
        this(name, 0, null);
    }

    public Customer(String name, int visitCount, LocalDate lastVisitDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("顾客名字不能为空");
        }
        if (visitCount < 0) {
            throw new IllegalArgumentException("到店次数不能为负数");
        }
        this.name = name;
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
    }

    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    void recordVisit(LocalDate date) {
        visitCount++;
        lastVisitDate = date;
    }

    Customer snapshot() {
        return new Customer(name, visitCount, lastVisitDate);
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', visitCount=" + visitCount
                + ", lastVisitDate=" + (lastVisitDate == null ? "暂无" : lastVisitDate) + "}";
    }
}
