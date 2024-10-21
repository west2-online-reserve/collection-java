package chongwudian;

import java.time.LocalDate;
public class Customer {
    private String name;
    private int count;
    private LocalDate time;
    public  Customer(){

    }

    public Customer(String name, int count, LocalDate times) {
        this.name = name;
        this.count = count;
        this.time = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTimes(LocalDate times) {
        this.time = times;
    }

    @Override
    public String toString() {
        return "名字："+getName()+" 第"+getCount()+"次来关顾"+" 日期为："+getTime();
    }
}
