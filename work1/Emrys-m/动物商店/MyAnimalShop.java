package HomeWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    public double balance;
    public double yesterdayBalance;
    List<Animal> animalList = new ArrayList<>();
    List<Customer> customersList = new ArrayList<>();
    public boolean isOpen;

    public MyAnimalShop(double balance,boolean isOpen) {
        this.balance = balance;
        this.isOpen = isOpen;
        this.yesterdayBalance = this.balance;
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if(animal.purchasePrice > this.balance){
            throw new InsufficientBalanceException("余额不足");
        }
        else{
            this.balance -= animal.purchasePrice;
            this.animalList.add(animal);
            System.out.println("购买成功,余额："+ this.balance + " " + this.yesterdayBalance);
        }
    }

    @Override
    public void serveGuests(Customer customer, Animal animal) {
        //判断顾客是否曾到访
        Iterator<Customer> it = customersList.iterator(); //创建顾客迭代器
        boolean flag = false;
        while(it.hasNext()){
            Customer currentCustomer = it.next();
            if(currentCustomer.name.equals(customer.name)){
                currentCustomer.num++;
                currentCustomer.getNewDate();
                flag = true;
                break;
            }
        }
        if(!flag) customersList.add(customer);

        //判断动物情况
        if(animalList.isEmpty()){ //判断动物列表是否为空
            throw new AnimalNotFountException("没有动物");
        }
        else {
            boolean sign = false; //标记顾客是否成功购买动物
            Iterator<Animal> its = animalList.iterator(); //创建动物迭代器
            while(its.hasNext()){
                Animal currentAnimal = its.next();
                if(currentAnimal.getClass() == animal.getClass()) { //判断当前动物是否符合顾客要求
                    System.out.println("购买成功");
                    System.out.println(currentAnimal);
                    this.balance += currentAnimal.price; //顾客付钱，宠物店收钱
                    animalList.remove(currentAnimal); //将动物移除列表
                    sign = true;
                    break;
                }
            }
            if(!sign) throw new AnimalNotFountException("没有动物"); //如果没有符合顾客需求的动物
        }
    }

    @Override
    public void closeDown() {
        System.out.println("歇业！");
        Iterator<Customer> it = customersList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        double profit = balance - yesterdayBalance;
        System.out.println("利润是:" + profit);
        this.isOpen = false;
    }

    @Override
    public String toString() {
        System.out.println("balance:" + this.balance + "\n" + "是否营业:" + this.isOpen + "\n");
        Iterator<Customer> it = customersList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        return "";
    }
}
