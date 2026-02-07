package assessment01;

import java.time.LocalDate;

public class Customer {
    private String name;        // 顾客名字
    private int visitCount;     // 到店次数
    private LocalDate lastVisit; // 最新到店时间

    // 构造方法
    public Customer(String name) {
        this.name = name;
        this.visitCount = 1;  // 初次到店
        this.lastVisit = LocalDate.now(); // 当前时间
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "顾客 [名字=" + name + ", 到店次数=" + visitCount + ", 最新到店时间=" + lastVisit + "]";
    }

    // Getter和Setter
    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void incrementVisitCount() {
        this.visitCount++;
    }
}
