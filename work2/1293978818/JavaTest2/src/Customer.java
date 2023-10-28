import java.time.LocalDate;
/**
 * 顾客类
 * @author 1293978818
 */
public class Customer {

    private String customerName;
    private int customerVisitTime;
    private LocalDate customerNewestVisitDate;

    
    public Customer() {
    }
    public Customer(String customerName, int customerVisitTime, LocalDate customerNewestVisitDate) {
        this.customerName = customerName;
        this.customerVisitTime = customerVisitTime;
        this.customerNewestVisitDate = customerNewestVisitDate;
    }

    /**
     * 上述变量的get和set方法
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getCustomerVisitTime() {
        return customerVisitTime;
    }
    public void setCustomerVisitTime(int customerVisitTime) {
        this.customerVisitTime = customerVisitTime;
    }
    public LocalDate getCustomerNewestVisitDate() {
        return customerNewestVisitDate;
    }
    public void setCustomerNewestVisitDate(LocalDate customerNewestVisitDate) {
        this.customerNewestVisitDate = customerNewestVisitDate;
    }

    @Override
    public String toString() {
        return "Customer [customerName=" + customerName + ", customerVisitTime=" + customerVisitTime+ ", customerNewestVisitDate=" + customerNewestVisitDate + "]";
    }

    
}
