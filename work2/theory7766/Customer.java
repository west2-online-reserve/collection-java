package com.west2.work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    //到店次数
    private int cnt;
    //最新到店时间
    private LocalDate time;

    public Customer(String name, int cnt, LocalDate time) {
        this.name = name;
        this.cnt = cnt;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this.name + "已到店" + this.cnt + "次,最新到店时间为:" + this.time;
    }
}
