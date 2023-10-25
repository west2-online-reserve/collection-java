package AnimalShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    protected double balance;
    protected List<Animal> animalList = new ArrayList<>();
    protected List<Customer> customerList = new ArrayList<>();
    protected boolean isRest;
    private double profit = 0;

    public MyAnimalShop(boolean isRest, double balance) {
        this.isRest = isRest;
        this.balance = balance;
    }


    @Override
    public void buy(Animal animal) {
        try {
            if (balance >= animal.price) {
                balance -= animal.price;
                animalList.add(animal);
                System.out.println("购买成功！当前宠物店有宠物" + animalList.size() + "只，余额为" + balance + "，购买的宠物信息：" + animal);
            } else {
                throw new InsufficientBalanceException("余额不足！");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }

    }

    @Override
    public void treatCustomer(Customer customer) {
        try {
            if (isRest) {
                System.out.println("门店已经打烊了。");
                return;
            }
            customerList.add(customer);
            if (animalList.size() == 0) {
                throw new AnimalNotFoundException("很抱歉，目前我店没有宠物！");
            } else {
                int num = (int) (Math.random() * animalList.size());
                Animal animal = animalList.get(num);
                animalList.remove(num);
                profit += animal instanceof ChineseFieldDog ? 100 : (animal instanceof Cat ? 200 : 300);
                System.out.println(animal);
                customer.times++;
                customer.lastestTimeArrived = LocalDate.now();
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void rest() {
        System.out.println("今日顾客：");
        Iterator iterator = customerList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("今日利润：");
        System.out.println(profit);
    }
}
