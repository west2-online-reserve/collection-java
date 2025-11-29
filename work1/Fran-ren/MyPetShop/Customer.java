import java.time.LocalDate;
import java.util.Scanner;

public class Customer {
    private String name;
    private int visitStoreCount;
    private LocalDate latesArrivalTimeToStore;

    Scanner sc = new Scanner(System.in);

    public Customer(String name, int visitStoreCount, LocalDate latesArrivalTimeToStore) {
        this.name = name;
        this.visitStoreCount = visitStoreCount;
        this.latesArrivalTimeToStore = latesArrivalTimeToStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitStoreCount() {
        return visitStoreCount;
    }

    public void setVisitStoreCount(int visitStoreCount) {
        this.visitStoreCount = visitStoreCount;
    }

    public LocalDate getLatesArrivalTimeToStore() {
        return latesArrivalTimeToStore;
    }

    public void setLatesArrivalTimeToStore(LocalDate latesArrivalTimeToStore) {
        this.latesArrivalTimeToStore = latesArrivalTimeToStore;
    }

    @Override
    public String toString() {
        return "Customer{name=''" + name + "', visitStoreCount='" + visitStoreCount + ", latesArrivalTimeToStore='"
                + latesArrivalTimeToStore + "'}";
    }
}
