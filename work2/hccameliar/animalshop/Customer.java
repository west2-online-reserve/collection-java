package animalshop;

import java.time.LocalDate;

/**
 * @author 102301412
 */
public class Customer {
    private String customerName;
    private int visitTimes = 0;
    private LocalDate visitDate;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "姓名：" + getCustomerName() + " 到店次数：" + getVisitTimes() + " 最新到店时间：" + getVisitDate();
    }
}
