package shop.animalshop;

import com.java.pta.shop.animal.Animal;
import com.java.pta.shop.customer.Customer;
import com.java.pta.shop.myexception.AnimalNotFountException;
import com.java.pta.shop.myexception.InsufficientBalanceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class MyAnimalShop implements AnimalShop{

    private String name;

    private double balance=0;

    private double profit =0;

    private List<Animal> listOfAnimals = new ArrayList<>();

    private List<Customer> listOfCustomers = new ArrayList<>();

    public MyAnimalShop(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public void buyAnimal(Animal animal) {
        if( balance >= animal.getPrice() ){
            listOfAnimals.add(animal);
            balance -= animal.getPrice();
            profit -= animal.getPrice();
            System.out.println("动物购买成功=>" + animal.toString());
            System.out.println("当前余额为:"+getBalance());
        }else{
            throw new InsufficientBalanceException("当前余额=>"+getBalance());
        }

    }

    @Override
    public void serviceCustomer(Customer customer, String animalName) {
        //顾客存在标记
        int flag = 0;

        //如果顾客存在，则刷新顾客信息
        for (int i = 0; i < listOfCustomers.size(); i++) {
            if( listOfCustomers.get(i).getName().equals(customer.getName())){
                System.out.println("该顾客已存在");
                flag = 1;
                listOfCustomers.get(i).setLastVisitDate( LocalDate.now());
                listOfCustomers.get(i).setNumberOfVisits( listOfCustomers.get(i).getNumberOfVisits() + 1);
            }
        }
        //顾客不存在，则提那家顾客信息
        if( flag == 0 ){
            customer.setLastVisitDate( LocalDate.now());
            customer.setNumberOfVisits(1);
            listOfCustomers.add(customer);
        }

        Animal animal = null;
        int idxAnimal = 0;
        if( listOfAnimals.size() >0 ){
            for (int i = 0; i < listOfAnimals.size(); i++) {
                if( listOfAnimals.get(i).getName().equals(animalName)){
                    animal = listOfAnimals.get(i);
                    idxAnimal = 1;
                    break;
                }
            }
            if (idxAnimal == 1) {
                listOfAnimals.remove(animal);
                balance += animal.getPrice();
                profit += animal.getPrice();
                System.out.println("动物出售成功=>" + animal.toString());
            }else {
                throw new  AnimalNotFountException("店内名字叫"+animalName+"的动物不存在");
            }
        }else{
            throw new AnimalNotFountException("店内名字叫"+animalName+"的动物不存在");
        }
    }

    @Override
    public void close() {
        double total = 0;
        LocalDate today = LocalDate.now();
        List<Customer> todayCustomer = new ArrayList<>();


        for (int i = 0; i < listOfCustomers.size(); i++) {
            //如果客户列表中的最后一次时间和今天相同，则增加到当天用户里面
            if( today.equals( listOfCustomers.get(i).getLastVisitDate() ) ){
                todayCustomer.add(listOfCustomers.get(i));
            }
        }

        System.out.println("==========今天到店客户信息如下============");
        for (int i = 0; i < todayCustomer.size(); i++) {

            System.out.println( todayCustomer.get(i).toString());

        }
        System.out.println();

        showProfit();
    }

    public void showProfit(){
        System.out.println("==========今天利润如下============");
        System.out.println("利润为:"+profit);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Animal> getListOfAnimals() {
        return listOfAnimals;
    }

    public void setListOfAnimals(List<Animal> listOfAnimals) {
        this.listOfAnimals = listOfAnimals;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
}
