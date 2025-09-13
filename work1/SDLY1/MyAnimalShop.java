package west2;


import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance >= animal.getPrice()) {
            animals.add(animal);
            balance -= animal.getPrice();
        } else {
            throw new InsufficientBalanceException("余额不足，无法购买动物");
        }
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内无动物可售");
        }
        customer.visit();
        customers.add(customer);
        Animal soldAnimal = animals.remove(0); 
        balance += soldAnimal.getPrice();
        System.out.println("顾客 " + customer.getName() + " 购买了 " + soldAnimal.toString());
    }

    @Override
    public void closeShop() {
        isOpen = false;
        System.out.println("今日顾客：" + customers);
        System.out.println("今日利润：" + (balance));
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}