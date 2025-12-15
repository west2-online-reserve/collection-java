package work1.Animalshop;

import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;

import java.time.LocalDate;

public class Customer {
    //顾客名字(String)
    //到店次数(int)
    //最新到店时间(LocalDate 类)

    private String name;
    private int times;
    private LocalDate arrivalTime;

    public Customer(String n,int t,LocalDate a){
        this.name=n;
        this.times=t;
        this.arrivalTime=a;
    }

    public String toString(){
        return "顾客："+name+"\n顾客到店次数："+times+"\n最新到店时间："+arrivalTime+"\n";
    }

    public String getName(){
        return name;
    }

    public int getTimes(){
        return times;
    }

    public LocalDate getArrivalTime(){
        return arrivalTime;
    }

    public void changeTimes(int t){
        this.times=t;
    }

    public void changeArrivalTime(){
        this.arrivalTime=LocalDate.now();
    }

    public void backToYesterday(){
        this.arrivalTime=arrivalTime.minusDays(1);
    }
}
