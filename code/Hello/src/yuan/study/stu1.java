package yuan.study;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

class Animal {
    // 动物类可根据需求定义属性和方法，这里简单示例
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Customer {
    // 顾客类可根据需求定义属性和方法，这里简单示例
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}

class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private boolean isOpen;

    public MyAnimalShop() {
        this.balance = 0;
        this.isOpen = true;
    }

    // 实现买入动物方法
    @Override
    public void buyAnimal(Animal animal, double price) throws InsufficientBalanceException {
        if (balance < price) {
            throw new InsufficientBalanceException("余额不足，无法买入动物");
        }
        balance -= price;
        animalList.add(animal);
    }

    // 实现招待客户方法
    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        customerList.add(customer);
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可供出售");
        }
        Animal soldAnimal = animalList.remove(0);
        balance += 100; // 假设每只动物售价100，可根据实际调整
        System.out.println("出售了: " + soldAnimal + " 给 " + customer);
    }

    // 实现歇业方法
    @Override
    public void closeShop() {
        System.out.println("当天光顾的客户列表: ");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        double profit = calculateProfit();
        System.out.println("一天的利润为: " + profit);
    }

    private double calculateProfit() {
        // 这里简单假设初始余额为0，利润就是当前余额，可根据实际逻辑调整
        return balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Animal animal : animalList) {
            sb.append(animal).append("\n");
        }
        return sb.toString();
    }
}

interface AnimalShop {
    void buyAnimal(Animal animal, double price) throws InsufficientBalanceException;

    void serveCustomer(Customer customer) throws AnimalNotFoundException;

    void closeShop();
}