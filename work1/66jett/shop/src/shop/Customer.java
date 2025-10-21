package shop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 顾客类
 */
public class Customer {

    /**
     * 顾客名字
     */
    private String name;

    /**
     * 到店次数
     */
    private int visitCount;

    /**
     * 最新到店时间
     */
    private LocalDate latestVisitTime;

    /**
     * 构造方法
     */
    public Customer(String name) {
        this.name = name;
        this.visitCount = 0;
        this.latestVisitTime = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLatestVisitTime() {
        return latestVisitTime;
    }

    public void setLatestVisitTime(LocalDate latestVisitTime) {
        this.latestVisitTime = latestVisitTime;
    }

    /**
     * 增加访问次数并更新最新访问时间
     */
    public void addVisit() {
        this.visitCount++;
        this.latestVisitTime = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("顾客信息 [姓名: %s, 到店次数: %d, 最新到店时间: %s]",
                name, visitCount, latestVisitTime.format(formatter));
    }
}