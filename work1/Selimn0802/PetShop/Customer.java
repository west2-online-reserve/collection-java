package PetShop;

import java.time.LocalDate;

//顾客类
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate date;

    @Override
    public String toString() {
        return "顾客姓名：" + name + " 到店次数：" + visitCount + " 最新到店时间：" + date;
    }

    public Customer() {}

    public Customer(String name, int visitCount, LocalDate date) {
        this.name = name;
        this.visitCount = visitCount;
        this.date = date;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getVisitCount () {
        return visitCount;
    }

    public void setVisitCount (int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getDate () {
        return date;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }
}
