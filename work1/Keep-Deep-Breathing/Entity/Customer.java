package Entity;

import java.time.LocalDate;

public class Customer {
    String name;
    int time;
    LocalDate ArrivalTime;

    public Customer() {
    }

    public Customer(String name, int time, LocalDate arrivalTime) {
        this.name = name;
        this.time = time;
        ArrivalTime = arrivalTime;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        ArrivalTime = arrivalTime;
    }


    @Override
    public String toString() {
        return  "顾客姓名：" +name +
                "\n顾客到店次数：" + time +
                "\n顾客最新到店时间：" + ArrivalTime;
    }
}
