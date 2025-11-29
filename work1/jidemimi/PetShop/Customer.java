package PetShop;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitTime;

    public Customer(String name, LocalDate lastVisitTime) {
        this.name = name;
        this.visitCount = 1;
        this.lastVisitTime = lastVisitTime;
    }

    public void incrementVisit() {
        this.visitCount++;
    }

    @Override
    public String toString() {
        return "顾客{" +
                "姓名='" + name + '\'' +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + lastVisitTime +
                '}';
    }

    // getter 方法
    public String getName() { return name; }
    public LocalDate getLastVisitTime() { return lastVisitTime; }
}
