package CongWork2;

import java.time.LocalDate;

public class Customer2 {
    private String CustomerName;
    private int count;
    private LocalDate time;

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Customer2(String customerName) {
        this.CustomerName=customerName;

    }

    @Override
    public String toString() {
        return "姓名："+getCustomerName()+" "+"到店次数："+getCount()+" "+"时间："+getTime();
    }

}
