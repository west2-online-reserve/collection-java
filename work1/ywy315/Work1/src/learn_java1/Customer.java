package learn_java1;

import java.time.LocalDate;

public class Customer implements Comparable<Customer> {
    private String name;//名字
    private int numOfVisits;//光临次数
    private LocalDate latestArrivalTime;//最近光临的时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public Customer(String name, int numOfVisits, LocalDate latestArrivalTime) {
        this.name = name;
        this.numOfVisits = numOfVisits;
        this.latestArrivalTime = latestArrivalTime;
    }

    public void addNumOfVisits(){
        numOfVisits++;
    }

    @Override
    public String toString() {
        return name + "用户总共光临该店" + numOfVisits + "次，最近一次光临的时间是" + latestArrivalTime.toString();
    }


    @Override//实现排序，输出时方便清楚
    public int compareTo(Customer o) {
        return o.latestArrivalTime.compareTo(this.latestArrivalTime);
    }
}
