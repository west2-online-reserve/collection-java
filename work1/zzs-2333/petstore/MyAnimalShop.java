package com.petstore;

import java.util.ArrayList;
import java.util.List;

class MyAnimalShop implements AnimalShop {
    private double balance;//余额
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;
    private double profit;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;//默认营业
        this.profit = 0.0;
    }

    @Override
    public void buyAnimal(String name,int age,String gender,boolean isVaccineInjected){
        Animal animal = new ChineseGardenDog(name,age,gender,isVaccineInjected);
        if(balance < animal.AnimalPrice){
            throw new InsufficientBalanceException("余额不足，无法购买动物。");
        }
        animalList.add(animal);
        balance -= animal.AnimalPrice;
    }
    @Override
    public void serveCustomer(Customer customer, String animalName) {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("没有可出售的动物。");
        }
        Animal animalToSell = null;
        for (Animal animal : animalList) {
            if (animal.AnimalName.equals(animalName)) {
                animalToSell = animal;
                break;
            }
        }

        if (animalToSell == null) {
            throw new AnimalNotFoundException("指定的动物未找到。");
        }

        customerList.add(customer);
        profit += animalToSell.AnimalPrice;
        animalList.remove(animalToSell);
        balance += animalToSell.AnimalPrice;
        System.out.println("出售动物信息: " + animalToSell.toString());
    }
    @Override
    public void closeShop() {
        System.out.println("当天顾客列表:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        System.out.printf("今天的利润为: %.2f元\n", profit);
        isOpen = false;
    }
}
