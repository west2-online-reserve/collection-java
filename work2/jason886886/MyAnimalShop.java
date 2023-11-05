package work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jason
 */
public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Customer> arrayListOfCustomers;
    private ArrayList<AbstractAnimal> arrayListOfAnimals;
    private boolean isOpen;
    private double profit;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, ArrayList<Customer> arrayListOfCustomers, ArrayList<AbstractAnimal> arrayListOfAnimals, boolean isOpen) {
        this.balance = balance;
        this.arrayListOfCustomers = arrayListOfCustomers;
        this.arrayListOfAnimals = arrayListOfAnimals;
        this.isOpen = isOpen;
    }


    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double restMoney) {
        this.balance = restMoney;
    }

    public ArrayList<Customer> getArrayListOfCustomers() {
        return arrayListOfCustomers;
    }

    public void setArrayListOfCustomers(ArrayList<Customer> arrayListOfCustomers) {
        this.arrayListOfCustomers = arrayListOfCustomers;
    }

    public ArrayList<AbstractAnimal> getArrayListOfAnimals() {
        return arrayListOfAnimals;
    }

    public void setArrayListOfAnimals(ArrayList<AbstractAnimal> arrayListOfAnimals) {
        this.arrayListOfAnimals = arrayListOfAnimals;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void buyNewAnimal(ArrayList<AbstractAnimal> marketOfAnimals) throws InsufficientBalanceException {
        Scanner sc = new Scanner(System.in);
        boolean isSuccessfullyBuy = false;
        while (!isSuccessfullyBuy) {
            System.out.println("市场内有如下动物:");
            for (AbstractAnimal marketOfAnimal : marketOfAnimals) {
                System.out.println(marketOfAnimal.toString());
            }
            System.out.print("请输入您想要买入动物的名字:");
            String nameOfBoughtAnimal = sc.next();
            for (int i = 0; i < marketOfAnimals.size(); i++) {
                if (nameOfBoughtAnimal.equals(marketOfAnimals.get(i).name)) {
                    if (this.balance < marketOfAnimals.get(i).price) {
                        throw new InsufficientBalanceException("余额不足");
                    }
                    System.out.println("买入成功, 该动物已进入仓库");
                    isSuccessfullyBuy = true;
                    this.balance -= marketOfAnimals.get(i).price;
                    this.arrayListOfAnimals.add(marketOfAnimals.get(i));
                    marketOfAnimals.remove(i);
                    break;
                }
                if (i == marketOfAnimals.size() - 1) {
                    System.out.println("未找到该名字所对应动物,请重试");
                }
            }
        }

    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNoFoundException {
        if (!isOpen) {
            System.out.println("本店已歇业, 请于本店营业时间光临, 谢谢!");
            return;
        }
        if (arrayListOfAnimals.isEmpty()) {
            throw new AnimalNoFoundException("店内没有动物");
        }
        Scanner sc = new Scanner(System.in);
        this.arrayListOfCustomers.add(customer);
        customer.setFrequencyOfVisitingShop(customer.getFrequencyOfVisitingShop() + 1);
        customer.setLastTimeOfVisitingShop(LocalDate.now());
        boolean isSuccessfullyBuy = false;
        while (!isSuccessfullyBuy) {
            System.out.println("本店可购买动物如下:\n");
            for (AbstractAnimal arrayListOfAnimal : arrayListOfAnimals) {
                System.out.println(arrayListOfAnimal.toString());
            }
            System.out.print("请输入您想购买动物的名字:");
            String nameOfBoughtAnimal = sc.next();
            for (int i = 0; i < arrayListOfAnimals.size(); i++) {
                if (arrayListOfAnimals.get(i).name.equals(nameOfBoughtAnimal)) {
                    System.out.println("成功购买动物:\n" + arrayListOfAnimals.get((i)).toString());
                    this.profit += arrayListOfAnimals.get(i).price;
                    this.balance += arrayListOfAnimals.get(i).price;
                    arrayListOfAnimals.remove(i);
                    isSuccessfullyBuy = true;
                    break;
                } else if (i == arrayListOfAnimals.size() - 1) {
                    System.out.println("未找到该名字所对应动物,请重试");
                }
            }
        }
    }

    @Override
    public void closeBusiness() {
        isOpen = false;
        System.out.println("今日到店顾客列表如下:");
        for (Customer arrayListOfCustomer : arrayListOfCustomers) {
            if (LocalDate.now().equals(arrayListOfCustomer.getLastTimeOfVisitingShop())) {
                System.out.println(arrayListOfCustomer);
            }
            System.out.println("本日利润为:" + this.profit);
        }
    }


}
