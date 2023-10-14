package com.west2.check02;

import com.generic.more.Person;

import java.time.LocalDate;

/**
 * @author yuyu
 */
public class Customer {
    protected String name;
    /**
     * arrvalTimes：到店次数
     */
    protected int arrvalTimes;
    /**
     * lastestArrvalTimes：最后一次到店时间
     */
    protected LocalDate lastestArrvalTime;


    public Customer() {
    }

    @Override
    public String toString() {
        return "顾客信息:" + '\n' +
                "name:" + name + " " +
                "arrvalTimes:" + arrvalTimes + " " +
                "lastestArrvalTime:" + lastestArrvalTime + " ";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrvalTimes() {
        return arrvalTimes;
    }

    public void setArrvalTimes(int arrvalTimes) {
        this.arrvalTimes = arrvalTimes;
    }

    public LocalDate getLastestArrvalTime() {
        return lastestArrvalTime;
    }

    public void setLastestArrvalTime(LocalDate lastestArrvalTime) {
        this.lastestArrvalTime = lastestArrvalTime;
    }


    public Customer(String name, int arrvalTimes, LocalDate lastestArrvalTime) {
        this.name = name;
        this.arrvalTimes = arrvalTimes;
        this.lastestArrvalTime = lastestArrvalTime;

    }
}
