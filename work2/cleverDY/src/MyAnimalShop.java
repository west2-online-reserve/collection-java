import jdk.internal.icu.text.UnicodeSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    // 成员变量
    protected double balance;
    protected List<Animal> animalList;
    protected List<Customer> customerList;
    protected boolean isOpen;

    public double profit = 0;

    // 构造方法


    public double getProfit() {
        return profit;
    }

    public MyAnimalShop(double balance,boolean isOpen) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = isOpen;
    }

    // 实现接口中的方法
    public void buyAnimal(Animal a) {
        try {
            double BuyTotal =  a.getPrice();
            if (BuyTotal > this.balance) {
                throw new InsufficientBalanceException("余额不足");
            }
            this.balance -= BuyTotal;
            animalList.add(a);
            System.out.println("购买成功!目前宠物店中有" + animalList.size() + "只宠物，购买的新宠物是：" +a.name);
        } catch (InsufficientBalanceException i) {
            System.out.println(i.toString());
        }
    }



    public void serveCustomer(Customer customer) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法招待客户。");
            return;
        }
        customerList.add(customer);
        customer.times++;
        customer.latestArrivedTime = LocalDate.now();
        if (animalList.isEmpty()) {
            System.out.println("抱歉，宠物店暂时没有动物可供出售。");
            return;
        }

        // 出售动物给客户
        Animal soldAnimal = animalList.remove(0);
        System.out.println("成功出售动物：[" + soldAnimal.toString() + "] ,  购买者：" + customer.name);


        // 将动物的价格入账
        balance += soldAnimal.getPrice();
        profit=profit+ soldAnimal.getPrice();//利润是卖宠物的钱

    }

    public void closeShop() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Today's customers:");
            customerList.forEach(System.out::println);
            System.out.println("Profit for today: " + getProfit());

        }
    }
}
