import java.time.LocalDate;

public class Customer {
    private String name;
    private int arrival;
    private LocalDate latestReachTime;

    Customer(String name, LocalDate reachTime) {
        this.name = name;
        arrival = 1;
        latestReachTime = reachTime;
    }

    public String getName() {
        return name;
    }

    public void updateArrival(LocalDate arrivalTime) {
        arrival++;
        latestReachTime = arrivalTime;
    }

    @Override
    public String toString() {
        String x = "Customer Name:\t\t\t" + name + "\nArrival Times:\t\t\t" + String.valueOf(arrival) + "\nLatest Arrival Time:\t" + String.valueOf(latestReachTime) + "\n";
        return x;
    }
}