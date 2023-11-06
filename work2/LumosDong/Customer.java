package com.dong.westtwowork;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int visitTimes;
    protected LocalDate lastVisit;

    public Customer() {}

    public Customer(String name, int visitTimes) {
        this.name = name;
        this.visitTimes = visitTimes;
        this.lastVisit = lastVisit;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return visitTimes
     */
    public int getVisitTimes() {
        return visitTimes;
    }

    /**
     * 设置
     * @param visitTimes
     */
    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    /**
     * 获取
     * @return lastVisit
     */
    public LocalDate getLastVisit() {
        return lastVisit;
    }

    /**
     * 设置
     * @param lastVisit
     */
    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }
    @Override
    public String toString() {
        return "顾客信息：{name = " + name + ", visitTimes = " + visitTimes + ", lastVisit = " + lastVisit + "}";
    }
}
