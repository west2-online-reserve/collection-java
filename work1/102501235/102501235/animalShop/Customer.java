package animalShop;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int times;
    private LocalDate lastComeTime;
    public Customer(String name, int times, LocalDate lastComeTime) {
        this.name = name;
        this.times = times;
        this.lastComeTime = lastComeTime;
    }

    @Override
    public String toString() {
        return "顾客名字："+name+"\n"+
                "到店次数："+times+"\n"+
                "最新到店时间："+lastComeTime;
    }
}
