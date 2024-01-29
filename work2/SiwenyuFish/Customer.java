package work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int cnt;
    private LocalDate latestArrivalTime;
    private boolean isCome;//顾客今天是否来过

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean come) {
        isCome = come;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDate latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public Customer(String name, int cnt, LocalDate latestArrivalTime) {
        this.name = name;
        this.cnt = cnt;
        this.latestArrivalTime = latestArrivalTime;
        this.isCome=false;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", cnt=" + cnt +
                ", latestArrivalTime=" + latestArrivalTime +
                '}';
    }
}
