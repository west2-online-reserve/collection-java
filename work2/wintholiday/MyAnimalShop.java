package work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected List<Animal> animalList;
    protected List<Customer> customerList;
    protected boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (animal.getPrice() > balance) {
            throw new InsufficientBalanceException("余额不够买动物");
        }

        animalList.add(animal);
        balance -= animal.getPrice();
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("动物卖完了");
        }

        System.out.println("接待顾客: " + customer.toString());

        Animal animal = animalList.remove(0);

        System.out.println("卖掉动物: " + animal.toString());

        customerList.add(customer);
        balance += animal.getPrice();
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println("今天的顾客:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        double profit = 5000-balance;
        System.out.println("卖了 " + profit);
    }
}

class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
