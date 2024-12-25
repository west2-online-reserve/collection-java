package Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    // 余额
    protected double balance;
    // 动物列表
    protected List<Animal> animals = new ArrayList<>();
    // 顾客列表
    protected List<Customer> customers = new ArrayList<>();
    // 是否营业
    protected boolean isOpen;
    // 开店日期
    protected LocalDate openingDate;

    // 构造方法，初始化余额、开店日期
    public MyAnimalShop(double balance, LocalDate openingDate) {
        this.balance = balance;
        this.openingDate = openingDate;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    // 买宠物
    @Override
    public void buyNewPet(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.price) {
            throw new InsufficientBalanceException("余额不足,购买失败");
        } else {
            animals.add(animal);
            balance -= animal.price;
            System.out.println("已购买：" + animal);
        }
    }

    // 接待顾客，判断是否同一天
    @Override
    public void receiveGuests(Customer customer) {
        customers.add(customer);
        LocalDate currentDate = customer.getVisitDate(); // 顾客到店日期
        if (currentDate.isEqual(openingDate)) {
            System.out.println("顾客 " + customer + " 在开店当天到店");
        } else {
            System.out.println("顾客 " + customer + " 不在开店当天到店");
        }
    }

    // 计算利润，关店
    @Override
    public void close() {
        if (animals.isEmpty()) {
            System.out.println("售罄，关门");
        }

        System.out.println("今日顾客");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        double profit = 0;
        for (Animal animal : animals) {
            profit += animal.price;
        }

        System.out.println("今天赚了" + profit);
        balance += profit;
        isOpen = false;
    }
}