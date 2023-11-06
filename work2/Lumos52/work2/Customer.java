package com.west2.work2;

import java.time.LocalDate;

public class Customer {
    private String customerName;
    /**
     * 到店次数
     */
    private int arrivalTimes;
    /**
     * 最新到店时间
     */
    private LocalDate time;

    public Customer(String customerName, int arrivalTimes, LocalDate time) {
        this.customerName = customerName;
        this.arrivalTimes = arrivalTimes;
        this.time = time;
    }

    /**
     * 获取客户姓名
     *
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户姓名
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取到店次数
     *
     * @return arrivalTimes
     */
    public int getArrivalTimes() {
        return arrivalTimes;
    }

    /**
     * 设置到点次数
     *
     * @param arrivalTimes
     */
    public void setArrivalTimes(int arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    /**
     * 获取到店时间
     *
     * @return time
     */
    public LocalDate getTime() {
        return time;
    }

    /**
     * 设置到店时间
     *
     * @param time
     */
    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "顾客" + customerName + "的到店次数是：" + arrivalTimes + ", 到店时间为" + time + "。";
    }
}
