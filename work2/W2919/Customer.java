package work2;

import java.time.LocalTime;

public class Customer {
    String name;
    int visitNums;
    LocalTime lastVisit;

    public String getName() {
        return name;
    }

    public Customer() {
    }

    public Customer(String name, int visitNums, LocalTime lastVisit) {
        this.name = name;
        this.visitNums = visitNums;
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "Customer name:" + name +
                ", visit numbers:" + visitNums +
                ", last visit time:" + lastVisit;
    }
}
