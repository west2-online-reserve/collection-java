import java.time.LocalDate;

public class Customer {
    private String name;
    private int visits;
    private LocalDate lastVisit;

    public Customer(String name, int visits, LocalDate lastVisit) {
        this.name = name;
        this.visits = visits;
        this.lastVisit = lastVisit;
    }
    public void updateVisit() {
        this.visits++; // Increment the visit count
        this.lastVisit = LocalDate.now(); // Update the last visit date to now
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visits=" + visits +
                ", lastVisit=" + lastVisit +
                '}';
    }

    // Getter 和 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }
}
