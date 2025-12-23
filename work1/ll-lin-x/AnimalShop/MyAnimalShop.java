package west2.task1.AnimalShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop {
    private double balance;
//    ArrayList<Animal> animals;
//    ArrayList<Custormer> customers;
    private HashMap<Custormer, LocalDate> custormersMap;
    private boolean openFlag;
    private double profit;
    private HashMap<Animal,Double>  animalsMap;



    public MyAnimalShop(double balance,boolean openFlag) {
        this.balance = balance;
//        animals = new ArrayList<>();
//        customers = new ArrayList<>();
        animalsMap = new HashMap<>();
        custormersMap = new HashMap<>();
        this.openFlag = openFlag;
    }
    @Override
    public void buyNewAnimal(Animal animal) {
        double price = animal.getPrice();
        if(price>this.balance){
            throw new InsufficientBalanceException("not sufficient funds");
        }else{
            this.balance -= price;
            this.profit -=price;
//            this.animals.add(animal);
            this.animalsMap.put(animal,price);
            String out = "花费了" + price + "购买了" + animal.getName() + ",余额" + this.balance;
            System.out.println(out);
        }

    }

    @Override
    public void entertainCustomer(Custormer custormer,Animal animal) {
        custormer.setLast(LocalDate.now());
        this.custormersMap.put(custormer,LocalDate.now());
//        int index = animals.indexOf(animal);
        Double price = animalsMap.get(animal);
        if(price == null){
            throw new AnimalNotFoundException("animal not found");
        }
        else{
//            this.animals.remove(index);
            animalsMap.remove(animal);
            this.balance += price;
            this.profit += price;
            System.out.println("卖出了一只宠物" + animal.toString() + "余额:" + this.balance);
        }
    }

    @Override
    public void closed() {
        this.openFlag = false;
        custormersMap.forEach((custormer,time)->{
            if(Objects.equals(time, LocalDate.now())){
                System.out.print(custormer+" ");
            }
        });
        System.out.println("今日利润:" + this.profit);
    }
}
