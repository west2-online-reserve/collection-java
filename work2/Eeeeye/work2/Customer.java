package com.Eeeeye.base.work2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Customer {
    private String name;
    public int time = 0;
    LocalDateTime localDateTime = LocalDateTime.now();
    OffsetDateTime date = localDateTime.atOffset(ZoneOffset.ofHours(+8));

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;

    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 获取
     * @return localDateTime
     */
    public void increasetime(){
        this.time=this.time+1;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * 设置
     * @param localDateTime
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
//如果获取实时时间应该添加内容
    /**
     * 获取
     * @return date
     */

    public String toString() {
        return "Customer{name = " + name + ", time = " + time + ", localDateTime = " + localDateTime + "}";
    }
}
