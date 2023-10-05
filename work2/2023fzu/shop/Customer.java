package shop;


import java.time.LocalDate;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:23
 **/

public class Customer {
    private String customerName;
    private int count = 0;//到店次数
    private LocalDate time;//最新到店时间(LocalDate类)

    public Customer(String customerName) {
        if (customerName == null){
            throw new NullPointerException();
        }
        if(this.count == 0) {
            this.customerName = customerName;
        }
        count = 1;
        setTime();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + "'\t" +
                ", count=" + count +"\t"+
                ", time=" + time +"\t"+
                "}\n";
    }

    @Override
    public boolean equals(Object customer){
        if (customer == null)return false;
        return customer instanceof Customer
                && ((Customer) customer).getCustomerName().equals(this.customerName);
    }

    public void setTime() {
        this.time = LocalDate.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void plusCount() {
        this.count++;
    }


}
