package com.west2.work2;

import java.time.LocalDate;

public class Customer {
    /**
     * 客户名字
     */
    private String name;
    /**
     * 客户到店次数
     */
    private int cnt;
    /**
     * 客户最新到店时间
     */
    private LocalDate time;

    public Customer(String name, int cnt, LocalDate time) {
        this.name = name;
        this.cnt = cnt;
        this.time = time;
    }

    @Override
    public String toString() {
        return this.name + "已到店" + this.cnt + "次,最新到店时间为:" + this.time;
    }

    /**
     * 获取客户名字
     *
     * @return 客户名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户名字
     *
     * @param name 客户名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * 设置客户到店次数
     *
     * @param cnt 客户到店次数
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * 获取客户最新到店时间
     *
     * @return time
     */
    public LocalDate getTime() {
        return time;
    }

    /**
     * 设置客户最新到店时间
     *
     * @param time 客户最新到店时间
     */
    public void setTime(LocalDate time) {
        this.time = time;
    }
}
