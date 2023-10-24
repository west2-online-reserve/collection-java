import java.time.LocalDate;
import java.util.PriorityQueue;

public class Customer {
    private String name;
    private int visitShopTimes;
    LocalDate latestVisitShopTime;


    public Customer(String name, int visitShopTimes) {
        this.name = name;
        this.visitShopTimes = visitShopTimes;
        //无法确定顾客是否到店，故暂时不构造latestVisitShopTime
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitShopTimes() {
        return visitShopTimes;
    }

    public void setVisitShopTimes(int visitShopTimes) {
        this.visitShopTimes = visitShopTimes;
    }

    public LocalDate getLatestVisitShopTime() {
        return latestVisitShopTime;
    }

    public void setLatestVisitShopTime(LocalDate latestVisitShopTime) {
        this.latestVisitShopTime = latestVisitShopTime;
    }

    @Override
    public String toString() {
        return "Customer{ nam:" + getName()
                + ",visitShopTimes:" + getVisitShopTimes() +
                ",latestVisitShopTime:" + getLatestVisitShopTime() + "}";
    }
}
