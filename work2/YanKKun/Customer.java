package Animal;

import java.time.LocalDate;

/**
 *
 * @author 12080
 * 顾客类
 *
 **/
public class Customer {
    private String name;
    private int arrivalTimes;
    private LocalDate lastestArrivalTime;

    public Customer(String name, int arrivalTimes, LocalDate lastestArrivalTime) {
        this.name = name;
        this.arrivalTimes = arrivalTimes;
        this.lastestArrivalTime = lastestArrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTimes() {
        return arrivalTimes;
    }

    public void setArrivalTimes(int arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    public LocalDate getLastestArrivalTime() {
        return lastestArrivalTime;
    }

    public void setLastestArrivalTime(LocalDate lastestArrivalTime) {
        this.lastestArrivalTime = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", arrivalTimes=" + arrivalTimes +
                ", lastestArrivalTime=" + lastestArrivalTime +
                '}';
    }
}
