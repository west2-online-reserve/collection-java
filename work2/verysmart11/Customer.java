package work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int numberOfVisits;
    private LocalDate latestArrivalTime;

    public Customer() {
    }

    public String getName() {
        return this.name;
    }

    public void numberOfVisitsAdd() {
        numberOfVisits++;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public Customer(String name, int numberOfVisits) {
        this.name = name;
        this.numberOfVisits = numberOfVisits;
        this.latestArrivalTime = LocalDate.now();
    }

    @Override
    public String toString() {
        return "customerName: " + this.name + "\r\n" +
                "numberOfVisits: " + this.numberOfVisits + "\r\n" +
                "latestArrivalTime: " + this.latestArrivalTime + "\r\n";
    }
}
