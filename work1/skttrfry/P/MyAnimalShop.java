package P;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animals> inventory;
    private List<Customer> customers;
    private boolean isOpen;



    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.inventory = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }
    @Override
    public void buyAnimal(Animals animals) {
        double cost =animals.monney*0.5;
        if(cost>balance){
            throw new  InsufficientBalanceException("no money");
        }
        balance = balance - cost;
        inventory.add(animals);
        System.out.println("Bought animal: " + animals + " , cost: " + cost + " , remaining balance: " + balance);
    }

    @Override
    public void serveCustomer(Customer customer) {
        if (inventory.isEmpty()){
        throw new AnimalNotFoundException("no inventory");
        }
        Customer found = null;
        for (Customer c : customers) {
            if (c.getName().equals(customer.getName())) {
                found = c;
                break;
            }
        }
        if (found == null){
            found = customer;
            customers.add(found);
        }
        found.visit(LocalDate.now());
        Animals sold = inventory.remove(0);
        double monney = sold.monney;
        balance = balance + monney;
        System.out.println("Served customer: " + found.getName() + " sold: " + sold + " monney: " + monney + " new balance: " + balance);
    }

    @Override
    public void closeShop() {
        LocalDate today = LocalDate.now();
        List<Customer> todayCustomers = new ArrayList<>();
        for (Customer c : customers) {
            if (today.equals(c.getLatestVisit())) {
                todayCustomers.add(c);
            }
        }
        if (todayCustomers.isEmpty()){
            System.out.println("todayCustomers is empty");
        }else{
            System.out.println("the Customers:");
            for (Customer c : todayCustomers) {
                System.out.println(c);
            }
        }
        //算钱......
        isOpen = false;
    }
}
