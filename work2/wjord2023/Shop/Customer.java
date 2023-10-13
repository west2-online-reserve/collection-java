package Shop;

import java.time.LocalDateTime;

public class Customer {
    private String name;
    private int numberOfTimeVisit;
    private LocalDateTime latestArrivalTime = LocalDateTime.now();

    public Customer(String name, int numberOfTimeVisit, LocalDateTime latestArrivalTime) {
        this.name = name;
        this.numberOfTimeVisit = numberOfTimeVisit;
        this.latestArrivalTime = latestArrivalTime;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfTimeVisit() {
        return numberOfTimeVisit;
    }

    public void setNumberOfTimeVisit(int numberOfTimeVisit) {
        this.numberOfTimeVisit = numberOfTimeVisit;
    }
    //声明和get，set

    public LocalDateTime getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDateTime latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    @Override
    public String toString() {
        return "客户名称：" + getName() +
                "\n到店次数：" + getNumberOfTimeVisit() +
                "\n最新到店时间：" + getLatestArrivalTime();
    }
}
