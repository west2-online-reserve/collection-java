import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int visitCount;
    private LocalDate latestVisitTime;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.visitCount = 1;
        this.latestVisitTime = LocalDate.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void incrementVisitCount() {
        this.visitCount++;
        this.latestVisitTime = LocalDate.now();
    }
    
    public LocalDate getLatestVisitTime() {
        return latestVisitTime;
    }

    @Override
    public String toString() {
        return "顾客信息{" +
                "顾客名字='" + customerName + '\'' +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + latestVisitTime +
                '}';
    }
}