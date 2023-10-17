package XWBN2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    private String shopName;
    private double account;

    private double profit;
    private boolean isOnBusiness;


    //创建宠物列表
    List<Animal> animalArray = new ArrayList<Animal>();

    //创建顾客列表
    List<Customer> customerArray = new ArrayList<Customer>();

    double getAccount() {
        return account;
    }


    //构造函数初始化宠物店的账户余额
    MyAnimalShop(String shopName, double account) {
        this.shopName = shopName;
        this.account = account;
    }


    public void buyNewAnimal(Animal animal) {
        try {
            if (animal.animalPurPrice > account) {
                throw new InsufficientBalanceException(animal.animalName, account);
            } else {
                profit -= animal.animalPurPrice;
                account -= animal.animalPurPrice;
                animalArray.add(animal);
            }
        } catch (InsufficientBalanceException e) {
            System.out.printf(e.toString());
        }
    }

    public void entertainCustomer(Customer customer, Animal animal) {
        try {
            int index = animalArray.indexOf(animal);
            if (isOnBusiness == false) {
                System.out.println("宠物店" + shopName + "休业无法购买宠物" + '\n');
                return;
            } else if (index == -1) {
                throw new AnimalNotFountException(animal.animalName);
            } else {
                System.out.printf("客户购买的宠物为：");
                System.out.println(animal);
                customer.setTimes(customer.getTimes() + 1);
                customer.getLatestArrivedTime();
                profit += animal.animalSellPrice;
                account += animal.animalSellPrice;
                customerArray.add(customer);

            }
        } catch (AnimalNotFountException e) {
            System.out.printf(e.toString());
        }
    }

    public void start() {
        isOnBusiness = true;
    }

    public void close() {
        isOnBusiness = false;
        System.out.println('\n' + shopName + "今天的顾客信息");
        for (Customer c : customerArray) System.out.println(c.toString());
        System.out.println(shopName + "今天的利润为：" + profit + '\n');

    }
}
