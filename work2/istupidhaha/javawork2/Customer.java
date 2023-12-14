package com.west2.javawork2;

import java.time.LocalDate;

/*成员变量:
         顾客名字(String)
         到店次数(int)
         最新到店时间(LocalDate类)
      方法
      重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息
    */
public class Customer {
    private String name;
    protected int numberVisits;
    private LocalDate latestTime;

    public Customer() {
    }

    public Customer(String name, int numberVisits, LocalDate latestTime) {
        this.name = name;
        this.numberVisits = numberVisits;
        this.latestTime = latestTime;
    }

    public LocalDate getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(LocalDate latestTime) {
        latestTime = latestTime;
    }

    public int getNumberVisits() {
        return numberVisits;
    }

    public void setNumberVisits(int numberVisits) {
        numberVisits = numberVisits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "顾客名:" + getName()+
                ",到店次数:" + getNumberVisits()+
                ",最新到店时间:"+getLatestTime();
    }
}

