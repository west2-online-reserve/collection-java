package work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int count=0;
    private LocalDate time;

    public Customer() {
    }

    public Customer(String name, int count, LocalDate time) {
        this.name = name;
        this.count = count;
        this.time = time;
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

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "客户信息{" + "客户姓名：" + name +  ", 客户到店次数：" + count + ", 客户最新到店时间：" + time + "}";
    }
}
