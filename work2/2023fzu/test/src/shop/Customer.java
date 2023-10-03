package shop;

import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

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
            new NullPointerException();
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
                "customerName='" + customerName + "\'\t" +
                ", count=" + count +"\t"+
                ", time=" + time +"\t"+
                "}\n";
    }

    @Override
    public boolean equals(Object customer){
        if (customer == null)return false;
        if(customer instanceof Customer
                        &&((Customer) customer).getCustomerName().equals(this.customerName)
        )return true;
        return false;
    }
    public LocalDate getTime() {
        return time;
    }
    public void setTime() {
        this.time = LocalDate.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        customerName = customerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void plusCount() {
        this.count++;
    }


}
