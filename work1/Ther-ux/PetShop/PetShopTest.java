import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 抽象动物类
abstract class Animal {
    protected String name;    // 动物名称
    protected int age;        // 动物年龄
    protected String gender;  // 动物性别
    protected double price;   // 动物价格

    // 构造方法：初始化动物基本信息
    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract String getType();
    public abstract String toString();
}

// 中华田园犬类
class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;  // 是否注射狂犬疫苗

    public ChineseRuralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String getType() { return "中华田园犬"; }

    @Override
    public String toString() {
        return String.format("中华田园犬{名字='%s', 价格=%.2f元, 疫苗=%s}",
                name, price, isVaccineInjected ? "已打" : "未打");
    }
}

// 猫类
class Cat extends Animal {
    private String breed;  // 猫的品种

    public Cat(String name, int age, String gender, String breed) {
        super(name, age, gender, 200.0);
        this.breed = breed;
    }

    @Override
    public String getType() { return "猫"; }

    @Override
    public String toString() {
        return String.format("猫{名字='%s', 品种='%s', 价格=%.2f元}",
                name, breed, price);
    }
}

// 鸟类
class Bird extends Animal {
    private boolean canSpeak;  // 是否会说话

    public Bird(String name, int age, String gender, boolean canSpeak) {
        super(name, age, gender, 150.0);
        this.canSpeak = canSpeak;
    }

    @Override
    public String getType() { return "鸟"; }

    @Override
    public String toString() {
        return String.format("鸟{名字='%s', 价格=%.2f元, 是否会说话=%s}",
                name, price, canSpeak ? "是" : "否");
    }
}

class Customer {
    private String name;  // 顾客姓名

    // 获取顾客姓名
    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    // 重写toString：展示顾客信息
    @Override
    public String toString() {
        return "顾客：" + name;
    }
}

// 余额不足异常
class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) { super(message); }
}

// 动物未找到异常
class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) { super(message); }
}

// 宠物店类
class MyAnimalShop {
    private String shopName;    // 店铺名称
    private double balance;     // 店铺余额
    private List<Animal> animals;  // 店内动物列表
    private boolean isOpen;     // 店铺是否营业
    private double dailyProfit; // 当日利润

    // 构造方法：初始化店铺信息
    public MyAnimalShop(String shopName, double initialBalance) {
        this.shopName = shopName;
        this.balance = initialBalance;
        this.animals = new ArrayList<>();
        this.isOpen = true;// 初始状态为营业中
        this.dailyProfit = 0.0;// 初始利润为0
    }
    // 获取店铺名称
    public String getShopName() { return shopName; }
    // 获取当前动物列表
    public List<Animal> getAnimals() { return new ArrayList<>(animals); }

    // 业务方法
    public void buyAnimal(Animal animal) {
        if (!isOpen) {
            throw new RuntimeException(shopName + "已歇业，无法买入动物");
        }
        // 检查余额是否足够
        if (animal.getPrice() > balance) {
            throw new InsufficientBalanceException(
                    String.format("余额不足！当前余额%.2f元，买入%s需%.2f元",
                            balance, animal.getName(), animal.getPrice()));
        }
        // 添加动物到列表，扣减余额
        animals.add(animal);
        balance -= animal.getPrice();
        System.out.printf("成功买入：%s，花费%.2f元，当前余额%.2f元%n",
                animal.getName(), animal.getPrice(), balance);
    }

    // 招待顾客方法
    public void serveCustomer(Customer customer) {
        if (!isOpen) {
            throw new RuntimeException(shopName + "已歇业，无法招待顾客");
        }
        // 检查是否有动物可卖
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内无动物可卖！");
        }
        // 卖出列表中第一只动物，更新余额和利润
        Animal soldAnimal = animals.remove(0);
        balance += soldAnimal.getPrice();
        dailyProfit += soldAnimal.getPrice();
        System.out.printf("顾客%s购买了：%s，当前余额%.2f元%n",
                customer.getName(), soldAnimal, balance);
    }

    public void closeShop() {
        if (!isOpen) {
            System.out.println(shopName + "已歇业！");
            return;
        }
        isOpen = false;
        System.out.printf("%n===== %s 今日营业结束 =====%n", shopName);
        System.out.println("今日日期：" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        System.out.printf("今日利润：%.2f元%n", dailyProfit);
        System.out.printf("当前余额：%.2f元%n", balance);
        System.out.print("店内剩余动物：");
        if (animals.isEmpty()) {
            System.out.println("无");
        } else {
            for (Animal a : animals) {
                System.out.print(a.getName() + " ");
            }
            System.out.println();
        }
        System.out.println("=======================");
    }
}

// 测试类
public class PetShopTest {
    // 主方法
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop("福大宠物店", 2000.0);
        System.out.println("===== 欢迎来到" + myAnimalShop.getShopName() + " =====");

        System.out.println("\n----- 买入动物 -----");
        try {
            ChineseRuralDog chineseRuralDog1 = new ChineseRuralDog("大黄", 2, "公", true);
            Cat cat1 = new Cat("咪咪", 1, "母", "英短");
            Bird bird1 = new Bird("翠翠", 1, "母", true);
            myAnimalShop.buyAnimal(chineseRuralDog1);
            myAnimalShop.buyAnimal(cat1);
            myAnimalShop.buyAnimal(bird1);
        } catch (Exception e) {
            System.out.println("买入失败：" + e.getMessage());
        }

        System.out.println("\n----- 招待顾客 -----");
        try {
            Customer customerZhang = new Customer("张三");
            Customer customerLi = new Customer("李四");
            Customer customerWang = new Customer("王五");
            myAnimalShop.serveCustomer(customerZhang);
            myAnimalShop.serveCustomer(customerLi);
            myAnimalShop.serveCustomer(customerWang);
        } catch (Exception e) {
            System.out.println("招待失败：" + e.getMessage());
        }

        System.out.println("\n----- 营业结束 -----");
        myAnimalShop.closeShop();
    }
}