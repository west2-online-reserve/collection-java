package shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的宠物店
 */
public class MyAnimalShop implements AnimalShop {

    /**
     * 店的余额
     */
    private double balance;

    /**
     * 动物列表
     */
    private List<Animal> animalList;

    /**
     * 顾客列表
     */
    private List<Customer> customerList;

    /**
     * 是否正在营业
     */
    private boolean isOpen;

    /**
     * 当日利润
     */
    private double dailyProfit;

    /**
     * 当日顾客列表
     */
    private List<Customer> todayCustomers;

    /**
     * 当前日期
     */
    private LocalDate currentDate;

    /**
     * 构造方法
     */
    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.todayCustomers = new ArrayList<>();
        this.isOpen = false;
        this.dailyProfit = 0.0;
        this.currentDate = LocalDate.now();
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if (animal.getPrice() > balance) {
            throw new InsufficientBalanceException(
                    String.format("余额不足！当前余额: %.2f元, 动物价格: %.2f元", balance, animal.getPrice()));
        }

        balance -= animal.getPrice();
        animalList.add(animal);
        System.out.println("成功买入动物: " + animal.toString());
    }

    @Override
    public void serveCustomer(Customer customer, String animalName) {
        if (!isOpen) {
            System.out.println("店铺未营业，无法招待顾客！");
            return;
        }

        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可出售！");
        }

        // 查找动物
        Animal targetAnimal = null;
        for (Animal animal : animalList) {
            if (animal.getName().equals(animalName)) {
                targetAnimal = animal;
                break;
            }
        }

        if (targetAnimal == null) {
            throw new AnimalNotFoundException("未找到名为 " + animalName + " 的动物！");
        }

        // 更新顾客信息
        if (!customerList.contains(customer)) {
            customerList.add(customer);
        }
        customer.addVisit();

        // 更新当日顾客列表（不重复添加）
        if (!todayCustomers.contains(customer)) {
            todayCustomers.add(customer);
        }

        // 出售动物
        balance += targetAnimal.getPrice();
        dailyProfit += targetAnimal.getPrice();
        animalList.remove(targetAnimal);

        System.out.println("成功出售动物给 " + customer.getName() + ": " + targetAnimal.toString());
        System.out.println("当前余额: " + balance + "元");
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("店铺已经歇业！");
            return;
        }

        isOpen = false;
        System.out.println("=== 宠物店歇业 ===");
        System.out.println("当天光顾的客户列表:");

        if (todayCustomers.isEmpty()) {
            System.out.println("今日无顾客光顾");
        } else {
            for (Customer customer : todayCustomers) {
                System.out.println(customer.toString());
            }
        }

        System.out.println("当天利润: " + dailyProfit + "元");
        System.out.println("当前总余额: " + balance + "元");

        // 重置当日数据
        todayCustomers.clear();
        dailyProfit = 0.0;
        currentDate = LocalDate.now();
    }

    /**
     * 开店
     */
    public void openShop() {
        if (isOpen) {
            System.out.println("店铺已经在营业中！");
            return;
        }

        // 检查日期是否变化，如果变化则重置当日数据
        if (!currentDate.equals(LocalDate.now())) {
            todayCustomers.clear();
            dailyProfit = 0.0;
            currentDate = LocalDate.now();
        }

        isOpen = true;
        System.out.println("宠物店开始营业！当前日期: " + currentDate);
    }

    // Getter方法
    public double getBalance() {
        return balance;
    }

    public List<Animal> getAnimalList() {
        return new ArrayList<>(animalList);
    }

    public List<Customer> getCustomerList() {
        return new ArrayList<>(customerList);
    }

    public boolean isOpen() {
        return isOpen;
    }
}