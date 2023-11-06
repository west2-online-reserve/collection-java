package HomeWork;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int count = 0;

    private LocalDate lastTime;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
        this.count = 0;
        this.lastTime = lastTime;
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

    public LocalDate getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDate lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString(){
        return "顾客的姓名是"+name+", "+"到店次数是"+count+", "+"最新到店时间是"+lastTime;

    }
}
