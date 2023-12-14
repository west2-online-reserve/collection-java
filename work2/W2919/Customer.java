package work2;

import java.time.LocalTime;

public class Customer {
    private String name;
    private int visitNums;
    private LocalTime lastVisit;

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

    public int getVisitNums() {
        return visitNums;
    }

    public void setVisitNums(int visitNums) {
        this.visitNums = visitNums;
    }
}
