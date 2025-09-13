package bean;

import java.time.LocalDate;

public class Customer {
    private String name;//顾客姓名
    private int count;//到店次数
    private LocalDate latestArrivalTime;//最新到店时间

    private Animal buyPet;//记录顾客那天所购买的宠物

    @Override//一个重写的toString方法来输出用户信息
    public String toString() {
        System.out.println("顾客的姓名:"+name+" 到店次数："+count+" 最新到店时间:"+latestArrivalTime);
        if(buyPet instanceof Cat)
            System.out.println("顾客买了一只猫");
        else if (buyPet instanceof ChineseRuralDog) {
            System.out.println("顾客买了一只中华田园犬");
        }
        return null;
    }

    public Customer(String name, int count, LocalDate latestArrivalTime) {
        this.name = name;
        this.count = count;
        this.latestArrivalTime = latestArrivalTime;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(LocalDate latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
    }

    public Animal getBuyPet() {
        return buyPet;
    }

    public void setBuyPet(Animal buyPet) {
        this.buyPet = buyPet;
    }

}
