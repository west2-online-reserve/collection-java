package src.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends AbstractAnimal {


    public Customer(String customerName, int numberOfVisits, LocalDate localVisit, AbstractAnimal localBuy) {
        this.customerName = customerName;
        this.numberOfVisits = numberOfVisits;
        this.localVisit = localVisit;
        this.localBuy = localBuy;
    }

    /* 顾客名字(String)
        到店次数(int)
        最新到店时间(LocalDate类)
        方法
        重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息*/
    private String customerName;
    private int numberOfVisits;
    private LocalDate localVisit;
    private AbstractAnimal localBuy;

    public AbstractAnimal getLocalBuy() {
        return localBuy;
    }

    public void setLocalBuy(AbstractAnimal localBuy) {
        this.localBuy = localBuy;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public LocalDate getLocalVisit() {
        return localVisit;
    }

    public void setLocalVisit(LocalDate localVisit) {
        this.localVisit = localVisit;
    }

    //顾客当天买的宠物
    public AbstractAnimal buy(AbstractAnimal animal, ArrayList listOfAnimal) {

        return animal;
    }

    @Override
    public String toString() {
        String s = " customerName: " + getCustomerName();
        s = s + " numberOfVisits: " + getNumberOfVisits();
        s = s + " localVisit: " + getLocalVisit();
        s = s + " localBuy: " + getLocalBuy();
        return s;
    }
}
