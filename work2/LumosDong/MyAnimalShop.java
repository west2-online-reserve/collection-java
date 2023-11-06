package com.dong.westtwowork;



import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class MyAnimalShop implements AnimalShop{
    protected double balance;
    protected ArrayList<AbstractAnimal> abstractAnimalList = new ArrayList<AbstractAnimal>();
    protected ArrayList<Customer> customerlist= new ArrayList<>();
    protected boolean isInBusiness;
    private double profit;

    public MyAnimalShop(double balance, boolean isInBusiness) {
        this.balance = balance;
        this.abstractAnimalList = abstractAnimalList;
        this.customerlist = customerlist;
        this.isInBusiness = isInBusiness;
    }
    @Override
    public void purchaseAnimal(AbstractAnimal abstractAnimal){
        try{
            if(balance >= abstractAnimal.price){
                balance -= abstractAnimal.price;
                System.out.println("恭喜您喜提爱宠！目前店内还有宠物" + abstractAnimalList.size() + "只，店内余额为" + balance + "元");
                abstractAnimalList.add(abstractAnimal);
            }else{
                throw new InsufficientResourcesException("余额不足!");
            }
        }catch (InsufficientResourcesException e){
            System.out.println(e);
        }
        }

    @Override
    public void treatCustomers(Customer customer) {
        try{
            if (isInBusiness) {
                System.out.println("店铺打烊！");
                return;
            }
            customerlist.add(customer);
        if (abstractAnimalList.size() == 0) {
            throw new AnimalNotFoundException ("目前无宠物。");
        } else{
            int number = (int) (Math.random()* abstractAnimalList.size());
            AbstractAnimal abstractAnimal = abstractAnimalList.get(number);
            abstractAnimalList.remove(number);
            profit += abstractAnimal instanceof Parrot ? 10000 : (abstractAnimal instanceof Cat ? 200 : 100);
            System.out.println(abstractAnimal);
            customer.setVisitTimes(customer.getVisitTimes()+1);
            customer.setLastVisit(LocalDate.now());
        }
        }catch (ArithmeticException e){
            System.out.println(e);
        }

    }

    @Override
    public void closureOfBusiness() {
        System.out.println("今日顾客列表如下：");
        Iterator i = customerlist.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("今日利润为："+profit);
    }

}
