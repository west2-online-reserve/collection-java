package Eyrine;

import java.time.LocalDate;

public class Customer {

    private String name;
    public int num;
    private LocalDate now;

    public Customer(String name, int num, LocalDate now) {
        this.name = name;
        this.num = num;
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public LocalDate getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", latestTime=" + now +
                '}';
    }
}
