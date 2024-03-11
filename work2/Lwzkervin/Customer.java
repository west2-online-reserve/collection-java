import java.time.LocalDate;

public class Customer {
    protected String customerName;
    protected int visitCount=0;
    protected LocalDate latestVisitDate;

    public Customer(String customerName, int visitCount, LocalDate latestVisitDate) {
        this.customerName = customerName;
        this.visitCount = visitCount;
        this.latestVisitDate = latestVisitDate;
    }

    @Override
    public String toString() {
        return "顾客信息{" +
                "顾客名字='" + customerName + '\'' +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + latestVisitDate +
                '}';
    }

    // Getter和Setter方法
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLatestVisitDate() {
        return latestVisitDate;
    }

    public void setLatestVisitDate(LocalDate latestVisitDate) {
        this.latestVisitDate = latestVisitDate;
    }
}
