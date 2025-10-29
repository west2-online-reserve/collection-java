package Pet.shop;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitTimes;
    private LocalDate newTime;

    public Customer(String name) {
        this.name = name;
    }

    public void come(LocalDate date){
        visitTimes++;
        newTime = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visitTimes=" + visitTimes +
                ", newTime=" + newTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public LocalDate getNewTime() {
        return newTime;
    }
}
