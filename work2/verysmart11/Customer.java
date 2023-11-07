package work2;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int numberOfVisits;
    protected LocalDate latestArrivalTime;

    public Customer() {
    }

    public Customer(String name, int numberOfVisits, LocalDate latestArrivalTime) {
        this.name = name;
        this.numberOfVisits = numberOfVisits;
        this.latestArrivalTime = latestArrivalTime;
    }

    @Override
    public String toString() {
        return "customerName: " + this.name + "\r\n" +
                "numberOfVisits: " + this.numberOfVisits + "\r\n" +
                "latestArrivalTime: " + this.latestArrivalTime + "\r\n";
    }
}
