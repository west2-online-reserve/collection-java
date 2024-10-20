package customer;

import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int comeCount = 0;
    private LocalDate LastComeDate;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getComeCount() {
        return comeCount;
    }

    public void setComeCount(int comeCount) {
        this.comeCount = comeCount;
    }

    public LocalDate getLastComeDate() {
        return LastComeDate;
    }

    public void setLastComeDate(LocalDate lastComeDate) {
        LastComeDate = lastComeDate;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("客户名称 :[" + getCustomerName() + "]" +
                " " +"到达次数" + "[" +  getComeCount() +  "]" +
                "最新一次到店时间" + "[" + getLastComeDate() + "]" );

        return builder.toString();


    }

}
