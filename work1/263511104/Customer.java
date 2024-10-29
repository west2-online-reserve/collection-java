package work1;

import java.time.LocalDate;

public class Customer {


    private String name;
    private int visitCount;
    private LocalDate lastVisitDate;

    public Customer(String name) {
        this.name = name;
        this.visitCount = 0;
        this.lastVisitDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Visits: " + visitCount + ", Last Visit: " + lastVisitDate;
    }

    public void visit() {
        visitCount++;
        lastVisitDate = LocalDate.now();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

}
