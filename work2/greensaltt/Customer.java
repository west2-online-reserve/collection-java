package animalshop;

import java.time.LocalDate;

/**
 * 客户类
 */
public class Customer {

    /**
     * 定义成员变量：姓名、到店次数、最新到店时间
     */
    private String customerName;
    private int times;
    private LocalDate newestTime;


    public String getCustomerName() {
        return customerName;
    }

    public int getTimes() {
        return times;
    }

    public LocalDate getNewestTime() {
        return newestTime;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setNewestTime(LocalDate newestTime) {
        this.newestTime = newestTime;
    }

    /**
     *
     * @return 顾客信息
     */
    @Override
    public String toString() {
        return "顾客的姓名为："+customerName
                +"\n该顾客的到店次数为："+times
                +"\n该顾客的最新到店时间为："+newestTime;
    }
}
