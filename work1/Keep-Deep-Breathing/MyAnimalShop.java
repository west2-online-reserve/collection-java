import Entity.Animal;
import Entity.Customer;

import Exception.InsufficientBalanceException;
import Exception.AnimalNotFountException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    double balance;
    double todayMoney;
    List<Animal> animals = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    boolean isWork;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, double todayMoney, List<Animal> animals, List<Customer> customers, boolean isWork) {
        this.balance = balance;
        this.todayMoney = todayMoney;
        this.animals = animals;
        this.customers = customers;
        this.isWork = isWork;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTodayMoney() {
        return todayMoney;
    }

    public void setTodayMoney(double todayMoney) {
        this.todayMoney = todayMoney;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if(balance-animal.getPrice()>=0){
            animals.add(animal);
            balance-=animal.getPrice();
            todayMoney-=animal.getPrice();
        }
        else{
            throw new InsufficientBalanceException("余额不足");
        }

    }

    @Override
    public void serveCustomer(Customer customer, Animal animal) {
        if(animals.contains(animal)){
            animals.remove(animal);
            customer.setTime(customer.getTime()+1);
            customer.setArrivalTime(LocalDate.now());
            customers.add(customer);
            balance+=animal.getPrice();
            todayMoney+=animal.getPrice();
            System.out.println(animal.toString());
            System.out.println();
        }
        else{
            throw new AnimalNotFountException("店里没有这个动物");
        }
    }

    @Override
    public void close() {

        for(Customer c : customers){
            if(c.getArrivalTime().equals(LocalDate.now())){
                System.out.println(c.toString());
                System.out.println();
            }
        }
        System.out.println("今天的利润是"+todayMoney);
        System.out.println();
        todayMoney=0;
    }
}
