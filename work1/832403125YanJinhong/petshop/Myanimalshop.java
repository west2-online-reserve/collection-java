package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Myanimalshop implements AnimalShop{
    private double balance;//余额
    private double profit;//利润
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;


    public Myanimalshop(double balance) {
        this.balance = balance;
        this.profit = 0;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException{
        if (this.balance < animal.getBuyingprice()) {
            throw new InsufficientBalanceException(
                    "余额不足, 无法购买动物; 当前余额: " + this.balance + ", 动物价格: " + animal.getBuyingprice()
            );
        }
        // 余额足够, 扣除余额
        this.balance -= animal.getBuyingprice();
        // 添加动物
        this.animalList.add(animal);
        System.out.println("【商店购买了】 " + animal);
        System.out.println("【当前余额】  " + this.balance);

    }

    @Override
    public void greetCustomer(Customer customer, Animal animal) throws AnimalNotFoundException, IllegalStateException{
        if (this.isOpen==false) {
            System.out.println("商店已歇业, 无法招待客户");
            throw new IllegalStateException("商店已歇业");
        }


        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("动物列表为空, 无法招待客户");
        }


        if (!animalList.contains(animal)) {
            throw new AnimalNotFoundException("动物列表中没有名为【 " + animal.getName() + " 】的动物, 无法招待客户");
        }
        // 如果未打疫苗, 则打疫苗
        if (animal instanceof Chinesedog) {
            Chinesedog dog = (Chinesedog) animal;
            if (dog.isVaccineInjected==false) {
                dog.setVaccineInjected(true);
                System.out.println("为" + dog.getName() + "打了疫苗");
            }
        }


        if (!customerList.contains(customer)) {
            customerList.add(customer);
        }
        this.profit += animal.getSellingprice() - animal.getBuyingprice();
        this.balance += animal.getSellingprice();
        this.animalList.remove(animal);
        // 更新顾客信息
        customer.setVisitcount(customer.getVisitcount() + 1);
        customer.setLastvisitdate(LocalDate.now());

        System.out.println("【招待客户】  [客户]: " + customer + " ====> [购买了]: " + animal);
    }

    @Override
    public void close() {
        this.isOpen = false;
        // 打印利润
        System.out.println("【商店已歇业】 利润为: " + this.profit);
        profit = 0;
    }

    @Override
    public void open() {
        this.isOpen = true;
        System.out.println(" ============ 商店已开业 ============ ");
        System.out.println("【当前余额:】 " + this.balance);
        System.out.println("【当前动物列表:】 " + this.animalList);
        System.out.println("【当前顾客列表:】 " + this.customerList);
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
