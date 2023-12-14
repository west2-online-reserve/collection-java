package collection

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private boolean isClosed;
    public MyAnimalShop(double money,boolean isClosed){
        this.money=money;
        this.isClosed=isClosed;
    }
    ArrayList<Animal> animalArrayList=new ArrayList<Animal>();
    ArrayList<Customer> customerArrayList=new ArrayList<Customer>();
    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public void buyAnimal(Animal newAnimal) {
        try{
            if(isClosed){
                System.out.println("宠物店已经关闭了");
                return;
            }
            System.out.println("店内余额："+money);
            System.out.println("所需资金："+newAnimal.price);
            if(money>= newAnimal.price){
                money -= newAnimal.price;
                animalArrayList.add(newAnimal);
                System.out.println("购买"+newAnimal+"成功！剩余"+money);
            }else{
                throw new InsufficientBalanceException("余额不够，购买失败");
            }
        }
        catch (InsufficientBalanceException e){
            System.out.println(e.toString());
        }


    }
    @Override
    public void inCUstomer(Customer newCustomer) {
        System.out.println(" ");
        try{
            if(isClosed){
                System.out.println("宠物店已经关闭！");
                return;
            }else{

                if(!animalArrayList.isEmpty()){
                    /*System.out.println("请输入您要购买的宠物：tiger,cat or chineseFieldDog?");
                    Scanner scanner=new Scanner(System.in);
                    String animal=scanner.nextLine();
                    switch (animal){

                    }*/customerArrayList.add(newCustomer);
                    newCustomer.setTimes(+1);
                    newCustomer.setLastTime(LocalDate.now());
                }else{
                throw new AnimalNotFoundException("当前无宠物能够购买");
                }
            }
        }catch (AnimalNotFoundException e){
            System.out.println(e.toString());
        }

    }
    @Override
    public void Closed() {
        System.out.println("顾客名单为：");
        if(animalArrayList.isEmpty()){
            System.out.println("暂无");
        }
        for (Customer it:customerArrayList){
            System.out.println(it.toString());
        }
        System.out.println("店内剩余余额为：");
        System.out.println(money);
    }
}

