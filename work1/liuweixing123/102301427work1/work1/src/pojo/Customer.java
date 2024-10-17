package pojo;

import java.time.LocalDate;

public class Customer {
    private String name; //顾客姓名
    private int count;  //到店总次数
    private LocalDate date; //最新到店时间

    public Customer() {
    }

    public Customer(String name, int count, LocalDate date) {
        this.name = name;
        this.count = count;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "顾客{" +
                "姓名='" + name + '\'' +
                ", 到店总次数=" + count +
                ", 最新到店时间=" + date +
                '}';
    }
}
