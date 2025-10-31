package com.github.lpx.model.customer;

import java.time.LocalDate;

public class Customer {
    private int id;
    private String name;//名字
    private int numOfVisits;//到店次数
    private LocalDate lastVisit;//最新到店日期
    private CustomerLevel customerLevel;

    public Customer(String name, int id) {
        this.id = id;
        this.name = name;
        this.numOfVisits = 0;
        this.lastVisit = LocalDate.now();
        this.customerLevel = CustomerLevel.createCustomerLevel(this.numOfVisits);
    }

    @Override
    public String toString() {
        return "名字：" + name + ",到店次数：" + numOfVisits + "最新到店日期：" + lastVisit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit() {
        this.lastVisit = LocalDate.now();
    }

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel() {
        customerLevel.setLevelByNumOfVisits(this.numOfVisits);
    }

    public double getDiscount() {
        return customerLevel.getDiscount();
    }

}
