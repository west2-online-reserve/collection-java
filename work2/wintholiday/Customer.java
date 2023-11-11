package work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int count;
    private LocalDate lastestArrivedTime;

    public Customer(String name, int count) {
        this.name = name;
        this.count = count;
        this.lastestArrivedTime = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getcount() {
        return count;
    }

    public void setcount(int count) {
        this.count = count;
    }

    public LocalDate getlastestArrivedTime() {
        return lastestArrivedTime;
    }

    public void setlastestArrivedTime(LocalDate lastestArrivedTime) {
        this.lastestArrivedTime = lastestArrivedTime;
    }

    @Override
    public String toString() {
        return "顾客：" +
                "姓名'" + name + '\'' +
                ", 来了" + count +"次"+
                ", 时间为" + lastestArrivedTime +
                '}';
    }
}
