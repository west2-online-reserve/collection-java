package petShop.shop;

import petShop.animal.Animal;
import petShop.customer.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals= new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private boolean open;
    private List<Customer> todayCustomers = new ArrayList<>();
    private double lastDayBalance;
    private LocalDate today;

    public void setOpen(boolean open) {
        this.open = open;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public double getLastDayBalance() {
        return lastDayBalance;
    }

    public void setLastDayBalance(double lastDayBalance) {
        this.lastDayBalance = lastDayBalance;
    }

    public void printTodayCustomers() {
        System.out.println("Today is "+today+" Customers:"+todayCustomers);
    }
    protected void clearTodayCustomers() {
        todayCustomers.clear();
    }
    public void addTodayCustomer(Customer customer){
        todayCustomers.add(customer);
    }
    public Customer getTodayCustomerById(long id){
        for (Customer customer : todayCustomers) {
            if(customer.getId()==id) return customer;
        }
        return null;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    protected void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    protected void setCustomers (List<Customer> customers) {
        this.customers = customers;
    }
    protected void addCustomer(Customer customer){
        this.customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean isOpen() {
        return open;
    }

    protected void addAnimal(Animal animal) {
        animals.add(animal);
    }
    protected void removeAnimalById(long id) {
        synchronized (this){
            for (Animal animal : animals) {
                if(animal.getId() == id){
                    animals.remove(animal);
                    break;
                }
            }
        }
    }

    public Animal getAnimalById(long id) {
        for (Animal animal : animals) {
            if(animal.getId() == id){
                return animal;
            }
        }
        return null;
    }
    protected Customer getCustomerById(long id) {
        for (Customer customer : customers) {
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }
    protected abstract double computeProfit();
}
