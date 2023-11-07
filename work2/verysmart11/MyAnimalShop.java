package work2;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {

    private double balance;
    private double startBalance;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isClose;
    private double profit;

    public MyAnimalShop(double balance, ArrayList<Animal> animalList, ArrayList<Customer> customerList) {
        this.balance = balance;
        this.animalList = animalList;
        this.customerList = customerList;
        startBalance = balance;
    }
    @Override
    public void buyAnimal(Animal a) throws InsufficientBalanceException {
        if (this.isClose == false) {
            try {
                if (balance >= a.Price) {
                    balance -= a.Price;
                    animalList.add(a);
                    System.out.print("buy a ");
                    if (a instanceof Cat) System.out.println("cat");
                    else if (a instanceof ChinesePastoralDog) System.out.println("dog");
                    else if (a instanceof Rabbit) System.out.println("rabbit");
                } else {
                    throw new InsufficientBalanceException();
                }
            }catch (InsufficientBalanceException e){
                System.out.println("balance is not enough");
            }
        } else System.out.println("Store is closing");
    }

    @Override
    public void treatCustomer(Customer c,Scanner sc) {
        if (this.isClose == false) {
            boolean flag = false;
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getName().equals(c.getName())) {
                    flag = true;
                }
            }
            if (flag == false) {
                customerList.add(c);
            }
            c.numberOfVisitsAdd();
            System.out.println("\r\n\r\n\r\n\r\n");
            System.out.println(c);
            try {
                if (animalList.size() == 0) {
                    throw new AnimalNotFountException();
                } else {
                    System.out.println("All animals in our store :");
                    for (int i = 0; i < animalList.size(); i++) {
                        System.out.println((i + 1) + ". " + animalList.get(i).toString());
                    }
                    System.out.println("Please input the number of the animal you want to choose.");
                    int Choice = sc.nextInt()-1;
                    System.out.println(c.getName() + " buys" + animalList.get(Choice).name);
                    System.out.println("enter an item in an account" + animalList.get(Choice).Price);
                    profit += animalList.get(Choice).Price;
                    animalList.remove(Choice);
                }
            }catch (AnimalNotFountException e){
                System.out.println("there is no animal in the store");
            }
        } else System.out.println("Store is closing");
    }



    @Override
    public void close(LocalTime time) throws ParseException {
        if (time.isBefore(LocalTime.of(10, 0))) {
            System.out.println("It is closing time");
            this.isClose = true;

        } else if (time.isAfter(LocalTime.of(22, 0))) {
            System.out.println("It is closing time");
            System.out.println("the profit in today is " + profit);
            boolean flag = false;
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getLatestArrivalTime().equals(LocalDate.now())) {
                    customerList.get(i).toString();
                    flag = true;
                }
            }
            if (flag == false) System.out.println("there is no customer today");
            this.isClose=true;
        } else if(time.isBefore(LocalTime.of(22, 0))&&time.isAfter(LocalTime.of(10, 0))){
            System.out.println("It is opening time");
            this.isClose = false;
        }
    }
}
