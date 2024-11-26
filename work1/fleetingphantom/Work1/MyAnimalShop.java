package Work1;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    public ArrayList<Animal> animals;
    private ArrayList<Customer> customerArrayList;
    double profitRate;
    boolean isOpen;
    private double profit;

    public MyAnimalShop() {
        animals = new ArrayList<>();
        customerArrayList = new ArrayList<>();
    }

    public MyAnimalShop(double balance) {
        this.balance = balance;
        isOpen = true;
        profitRate = 0.5;
        animals = new ArrayList<>();
        profit = 0;
        customerArrayList = new ArrayList<>();
    }

    public MyAnimalShop(double balance, double profitRate) {
        this.balance = balance;
        isOpen = true;
        this.profitRate = profitRate;
        animals = new ArrayList<>();
        profit = 0;
        customerArrayList = new ArrayList<>();
    }

    public MyAnimalShop(double balance, ArrayList<Animal> animals, ArrayList<Customer> customerArrayList, boolean isOpen, double profitRate, double profit) {
        this.balance = balance;
        this.animals = animals;
        this.customerArrayList = customerArrayList;
        this.isOpen = isOpen;
        this.profitRate = profitRate;
        this.profit = profit;
    }


    @Override
    public void buyAnimals(ArrayList<Animal> marketAnimals, int num) {
        if (marketAnimals.size() < num || num <= 0) {
            throw new AnimalNotFountException("AnimalNotFountException", num, marketAnimals.size());
        } else if (marketAnimals.get(num - 1).value > this.balance) {
            throw new InsufficientBalanceException("InsufficientBalanceException", this.balance, marketAnimals.get(num - 1).value);
        } else {
            this.animals.add(marketAnimals.get(num - 1));
            this.balance -= marketAnimals.get(num - 1).value;
            marketAnimals.remove(num - 1);
        }
    }

    @Override
    public void serveCustomer(Customer customer, int num) {
        if (!isOpen) {
            System.out.println("关门了");
            return;
        }
        String tmpStr = customer.getName();
        boolean newCustomer = true;

        for (int i = 0; i < customerArrayList.size(); i++) {
            //判断是不是新顾客
            if (tmpStr.equals(customerArrayList.get(i).getName())) {
                //增加关顾次数
                customerArrayList.get(i).setTime(customerArrayList.get(i).getTime() + customer.getTime());
                //是否更新最新到店时间
                if (customer.getArriveDate().isAfter(customerArrayList.get(i).getArriveDate())) {
                    customerArrayList.get(i).setArriveDate(customer.getArriveDate());
                }

                newCustomer = false;
                break;
            }

        }

        if (newCustomer) {
            customerArrayList.add(customer);
            System.out.println("欢迎新顾客" + customer.getName() + "光临");
        } else {
            System.out.println("欢迎老顾客" + customer.getName() + "光临");
        }

        if (num <= 0 || num > animals.size()) {
            throw new AnimalNotFountException("AnimalNotFountException", num, animals.size());
        } else {
            balance += animals.get(num - 1).value * (1 + profitRate);
            System.out.println("已售出动物:");
            System.out.println(animals.get(num - 1));
            System.out.println("出售价格:" + animals.get(num - 1).value * (1 + profitRate));
            if (customer.getArriveDate().isEqual(LocalDate.now())) {
                profit += animals.get(num - 1).value * profitRate;
            }
            animals.remove(num - 1);
        }
        System.out.println("---------------------------------");
    }

    @Override
    public void closeShop() {
        isOpen = false;
        System.out.println("今天光顾的顾客:");
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerArrayList.get(i).getArriveDate().isEqual(LocalDate.now())) {
                System.out.println(customerArrayList.get(i));
                System.out.println();
            }
        }
        System.out.println("今天的利润:" + profit);
    }
}
