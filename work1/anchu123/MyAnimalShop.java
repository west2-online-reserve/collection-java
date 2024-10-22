/*自己的宠物店*/

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    protected double balance;//余额
    protected ArrayList<Animal> AnimalList = new ArrayList<>();//宠物列表
    protected ArrayList<Customer> CustomerList = new ArrayList<>();//顾客列表
    protected boolean isOpen;//是否营业
    protected double profit;//利润=售出宠物的价格-购买宠物的成本

    public MyAnimalShop(double balance, ArrayList<Animal> AnimalList, ArrayList<Customer> CustomerList, boolean isOpen, double profit) {
        this.balance = balance;
        this.AnimalList = AnimalList;
        this.CustomerList = CustomerList;
        this.isOpen = isOpen;
        this.profit = profit;
    }

    public void buyAnimal(Animal animal) {
        if (this.balance < animal.price) {
            throw new InsufficientBalanceException("您的余额不足以购买该宠物！");
        } else {
            System.out.println("您成功购买该宠物！");
            System.out.println("该宠物的具体信息是：");
            System.out.println(animal.toString());
            AnimalList.add(animal);
            balance -= animal.price;
            profit -= animal.price;
        }
    }

    public void serveCustomer(Customer customer, Animal animal) {
        if (AnimalList.isEmpty()) {
            throw new AnimalNotFountException("非常抱歉，宠物商店内暂无宠物,欢迎下次光临！");
        }
        if (!isOpen) {
            System.out.println("非常抱歉，我们宠物商店今天已经歇业了，欢迎下次光临！");
        }
        CustomerList.add(customer);
        System.out.println("恭喜你成功售出宠物！");
        System.out.println("该宠物的信息为：");
        System.out.println(animal.toString());
        AnimalList.remove(animal);
        profit += animal.price;
        balance += animal.price;

    }

    public void closeShop() {
        this.isOpen = false;
        System.out.println("今天的营业结束啦！");
        System.out.println("今天一共接待了" + CustomerList.size() + "名顾客！");
        System.out.println("下面为今天接待顾客的信息：");
        for (int i = 0; i < CustomerList.size(); i++) {
            System.out.println("第" + (1 + i) + "名顾客的信息为:" + "\n" + CustomerList.get(i));
        }
        System.out.println("今天商店的利润为" + profit + "元！");
        System.out.println("商店的总余额为" + balance + "元！");
        System.out.println("明天也要继续努力工作哦！");
    }
}
