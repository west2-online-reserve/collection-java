import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int time;
    private LocalDate latestVisit;

    public Customer(String customerName, int time, LocalDate latestVisit) {
        this.customerName = customerName;
        this.time = time;
        this.latestVisit = latestVisit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getLatestVisit() {
        return latestVisit;
    }

    public void setLatestVisit(LocalDate latestVisit) {
        this.latestVisit = latestVisit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", time=" + time +
                ", latestVisit=" + latestVisit +
                '}';
    }
}
