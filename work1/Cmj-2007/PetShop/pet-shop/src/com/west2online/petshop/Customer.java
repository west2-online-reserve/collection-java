package com.west2online.petshop;
import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCnt;
    private LocalDate latestTime;

    public Customer(){

    }

    public Customer(String name){
        this.name = name;
        this.visitCnt = 0;
        this.latestTime = LocalDate.now();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getVisitCnt(){
        return visitCnt;
    }

    public void setVisitCnt(int visitCnt){
        this.visitCnt = visitCnt;
    }

    public LocalDate getLatestTime(){
        return  latestTime;
    }

    public void setLatestTime(LocalDate latestTime){
        this.latestTime = latestTime;
    }

    public void addVisit(){
        this.visitCnt ++;
        this.latestTime = LocalDate.now();
    }

    @Override
    public String toString(){
        return (
                "顾客的姓名为" + getName() +
                " 到店次数:" + getVisitCnt() +
                " 最新到店时间:" + getLatestTime()
                );
    }









}