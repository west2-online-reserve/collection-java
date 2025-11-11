package P;

import java.time.LocalDate;//真不知道这玩意儿
public class Customer {
    private String name;
    private int visits;
    private LocalDate latestVisit;

    public Customer(String name) {
        this.name = name;
        this.visits = 0;
        this.latestVisit = null;
    }

    public String getName() {
        return name;
    }

    public int getVisits() {
        return visits;
    }

    public LocalDate getLatestVisit() {
        return latestVisit;
    }

    public void visit(LocalDate date) {
        this.visits++;
        this.latestVisit = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", visits=" + visits +
                ", latestVisit=" + latestVisit +
                '}';
    }
}
