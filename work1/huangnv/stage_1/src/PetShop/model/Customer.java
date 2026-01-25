package PetShop.model;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int visitCount;
    protected LocalDate latestVisitDate;

    public Customer(String name, LocalDate latestVisitDate) {
        this.name = name;
        this.visitCount = 0;
        this.latestVisitDate = latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }
    public String getName(){
        return this.name;
    }
    public LocalDate getLatestVisitDate(){
        return this.latestVisitDate;
    }
    public  void addVisitCount(){
        this.visitCount++;
    }
    @Override
    public String toString() {
        return "顾客信息 {" +
                "姓名='" + name + '\'' +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + latestVisitDate +
                '}';
    }
}