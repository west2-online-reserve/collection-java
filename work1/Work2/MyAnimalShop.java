package Work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class MyAnimalShop implements AnimalShop {
    private double remaining;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;

    public MyAnimalShop(double initialremaining) {
        this.remaining = initialremaining;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }


    @Override
    public void buy(Animal animal) {
        if (remaining >= animal.price) {
            remaining -= animal.price;
            animalList.add(animal);
        } else {
            throw new InsufficientBalanceException("余额不足");

        }


    }

    @Override
    public void reception(Customer customer) {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("没有可出售的动物！");
        } else {
            customerList.add(customer);
            customer.visit();
            System.out.println("请选择你要购买的宠物");
            Scanner scanner = new Scanner(System.in);
            String aniaml = scanner.nextLine();
            Animal soldAnimal = animalList.remove(0);
            remaining += soldAnimal.price;
            System.out.println(soldAnimal);
            customer.getLatestarrive(LocalDate.now());

        }


    }

    @Override
    public void close() {
        System.out.println("今日顾客名单:");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        System.out.printf("今日利润: %.2f\n", remaining);
        isOpen = false;

    }


}

















