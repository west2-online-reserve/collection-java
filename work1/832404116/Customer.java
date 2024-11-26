package com.animals;

import java.time.LocalDate;

/*成员变量:
顾客名字(String)
到店次数(int)
最新到店时间(LocalDate类)
方法
重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息*/
public class Customer {
    private String Customername;
    private int times;
    private LocalDate time;

    public Customer(String costumername, int times) {
        Customername = costumername;
        this.time = time;
    }

    public Customer() {
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "姓名："+ getCustomername()+" "+"到店次数："+getTimes()+" "+"时间："+getTime();
    }
}


