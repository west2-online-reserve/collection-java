package work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitDate;

    public Customer(String name) {
        this.name = name;
        this.visitCount = 0;
        this.lastVisitDate = null;
    }
    public void visit() {
        visitCount++;
        lastVisitDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Customer: " + name +
                ", Visit Count: " + visitCount +
                ", Last Visit Date: " + lastVisitDate;
    }
}
