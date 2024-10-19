package com.petshop;

import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    double balance,initalBalance;
    ArrayList<Animal> animals;
    ArrayList<Customer> customers;
    boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.initalBalance = balance;
        this.animals = new ArrayList<Animal>();
        this.customers = new ArrayList<Customer>();
        this.isOpen = false;
    }
    public void purchaseAnimal(Animal animal,double costOfPurchase) {
        if(balance < costOfPurchase){
            throw new InsufficientBalanceException("Insufficient balance: "+balance+" < "+costOfPurchase);
        }
        balance -= costOfPurchase;
        animals.add(animal);
    }
    public void sellAnimal(String AnimalSpecies) {
        boolean checkWhetherAnimalExists = false;
        int findTheAnimal;
        for(findTheAnimal = 0; findTheAnimal<animals.size(); findTheAnimal++){
            if(AnimalSpecies.equals(animals.get(findTheAnimal).getSpecies())){
                checkWhetherAnimalExists = true;
                break;
            }
        }
        if(!checkWhetherAnimalExists){
            throw new AnimalNotFountException(AnimalSpecies+" not found");
        }
        balance += animals.get(findTheAnimal).getPrice();
        System.out.println("Animal sold: "+animals.get(findTheAnimal).toString());
        animals.remove(findTheAnimal);
    }
    public void showAnimalList(){
        if(animals.isEmpty()){
            System.out.println("No Animals in Shop");
            return;
        }
        System.out.println("Animals in Shop:");
        for(int i = 0; i<animals.size(); i++){
            System.out.println(animals.get(i).toString());
        }
    }
    public void entertainCustomer(Customer customer){
        customers.add(customer);
    }
    public void showCustomerList(){
        if(customers.isEmpty()){
            System.out.println("No Customers Entertained");
            return;
        }
        for(int i = 0; i<customers.size(); i++){
            System.out.println(customers.get(i).toString());
        }
    }
    public void openShop(){
        isOpen = true;
    }
    public void closeShop(){
        isOpen = false;
        System.out.print("Total Customers Entertained today: "+customers.size()+"\n");
        for(int i=0;i<customers.size();i++){
            System.out.println(customers.get(i).toString());
        }
        System.out.println("Profit earned today: "+(balance-initalBalance)+"\n");
    }
    public void whetherIsOpen(){
        if(isOpen){
            System.out.println("The Shop Is Open");
        }
        else{
            System.out.println("The Shop Is Closed");
        }
    }
}
