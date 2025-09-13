package Test;

import java.time.LocalDate;

/**
 * 为了让Test文件夹简洁一些，我把相同的类放到一个文件夹
 * 实例例访问修饰符已经改成private，并且添加get，set方法
 * 这边具体没有什么改动
 */

public class Customer {

    private String name;
    private int arriveTimes;
    private LocalDate visitDate;

    public Customer() {
    }

    public Customer(String name, int arriveTimes, LocalDate visitDate) {
        this.name = name;
        this.arriveTimes = arriveTimes;
        this.visitDate = visitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArriveTimes() {
        return arriveTimes;
    }

    public void setArriveTimes(int arriveTimes) {
        this.arriveTimes = arriveTimes;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", arriveTimes=" + arriveTimes +
                ", theLatestArriveTime=" + visitDate +
                '}';
    }
}