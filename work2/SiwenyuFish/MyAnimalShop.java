package work2;

import java.time.LocalDate;
import java.util.ArrayList;


public class MyAnimalShop implements AnimalShop {

    private double balance;
    private ArrayList animalList;
    private ArrayList customerList;
    private boolean isClosed;
    private double profit;//利润

    public MyAnimalShop(double balance, ArrayList animalList, ArrayList customerList, boolean isClosed) {
        this.balance = balance;
        this.animalList = animalList;
        this.customerList = customerList;
        this.isClosed = isClosed;
    }

    @Override
    public void purchaseAnimal(Animal animal) {
        if (animal.price > balance)
            throw new InsufficientBalanceException("宠物店余额不足,购买动物失败");
        balance -= animal.price;
        System.out.println("买入动物成功");
        animalList.add(animal);
    }

    @Override
    public void entertainCustomer(Customer customer, Animal animal) {

        //宠物店关门时直接return，不记录客户光顾门店的信息
        if (isClosed) {
            System.out.println("宠物店已经关门，无法招待顾客");
            return;
        }

        LocalDate nowTime = LocalDate.now();
        customer.setLatestArrivalTime(nowTime);
        customer.setCnt(customer.getCnt() + 1);
        int flag = 0;

        if (animalList.contains(animal)) {
            animalList.remove(animal);
            if(!customer.isCome()){
                customerList.add(customer);//顾客今天没有来过则添加
                customer.setCome(true);
            }
            balance += animal.price * (1.3);//动物的售价比进货价格多百分之三十
            profit += animal.price * 0.3;
            flag = 1;
            System.out.println(customer.getName() + "购买了" + animal.name + "!");
        }

        if (flag == 0)
            throw new AnimalNotFountException("宠物店里没有该动物");

    }

    @Override
    public void closeDown() {
        if (!isClosed) {
            isClosed = true;
            System.out.println("--------宠物店歇业--------");
            System.out.println("今天赚了"+profit);
            System.out.println("宠物店的余额为"+balance);
            System.out.println("今天光顾的顾客列表如下");
            System.out.println(customerList);
            System.out.println("------------------------");
        }
        else {
            System.out.println("店铺已经关门，请不要重复操作");
        }

    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "balance=" + balance +
                ", animalList=" + animalList +
                ", customerList=" + customerList +
                ", isClosed=" + isClosed +
                '}';
    }
}
