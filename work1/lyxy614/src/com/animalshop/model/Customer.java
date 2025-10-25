package com.animalshop.model;

import java.time.LocalDate;
public class Customer {
    protected String name;
    protected int times;
    protected LocalDate lastDate;
    public Customer(String name){
        this.name = name;
        this.times = 0;
        this.lastDate = null;
    }
    public void setDate(){
        this.lastDate = LocalDate.now();
    }
    public void setTimes(){
        this.times++;
    }
    public LocalDate getLastDate(){
        return this.lastDate;
    }
    @Override
    public String toString(){
        return String.format("名字: %s, 到店次数: %d, 最新到店时间: %s", name, times, lastDate.toString());
    }
}
