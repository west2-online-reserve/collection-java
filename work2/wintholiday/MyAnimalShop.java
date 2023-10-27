package work2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected double profit;
    protected List<Animal> animalList;
    protected List<Customer> customerList;
    protected boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.profit = profit;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (animal.getPrice() > balance) {

            throw new InsufficientBalanceException("余额不足买动物");
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
        System.out.println("现有动物编号为"+(animalList.size()-1)+"顾客购买的动物编号为编号为");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n>=animalList.size()){
            throw new AnimalNotFoundException("该动物卖了,重新输");
        }
            Animal animal = animalList.remove(n);

            System.out.println("卖掉动物: " + animal.toString());

            customerList.add(customer);
            profit += animal.getPrice();

    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println("今天的顾客:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }

        System.out.println("卖了 " + profit);
    }
}



