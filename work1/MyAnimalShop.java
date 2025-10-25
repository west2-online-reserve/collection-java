import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    //店的余额 double
    //一个存放动物的列表 (使用 ArrayList、LinkedList 或其他你喜欢的 List 实现)
    //一个顾客列表留作纪念
    //是否正在营业
    private double balance;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;
    //买入动物 -> 买入一只动物，记得在动物列表中添加，
    //如余额不足则抛出异常 InsufficientBalanceException
    //招待客户 -> 接受客户参数，在顾客列表中加入新顾客，
    //出售动物，如果店内没有动物，抛出 AnimalNotFoundException。
    //通过 toString 输出动物信息，并把钱入账，将动物移除列表
    //歇业 -> (LocalDate 判断) 输出当天光顾的客户的列表信息，计算并输出一天的利润
    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true; // 默认开业状态
    }
    @Override
    public void buyNewAnimal(Animal animal) {
        // 买入新动物的逻辑
        // 检查余额是否足够
        if (!isOpen) {
            System.out.println("店铺已歇业，无法买入动物");
            return;
        }
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，当前余额：" + balance + "，需要：" + animal.getPrice());
        }
        // 买入动物，更新余额和动物列表
        balance -= animal.getPrice();
        animalList.add(animal);
        System.out.println("成功买入：" + animal.getAnimalName());
    }

    @Override
    public void serveCustomer(Customer customer) {
        // 招待客户的逻辑
        if (!isOpen) {
            System.out.println("店铺已歇业，无法招待客户");
            return;
        }

        // 检查是否有动物可卖
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可卖");
        }

        // 加入顾客列表
        customerList.add(customer);

        // 出售动物
        // 打印动物列表
        System.out.println("当前动物：");
        for (int i = 0; i < animalList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + animalList.get(i));
        }
        // 用户输入动物序号
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要出售的动物序号：");
        int animalIndex = scanner.nextInt() - 1;

        // 检查动物序号是否有效
        if (animalIndex < 0 || animalIndex >= animalList.size()) {
            throw new InvalidAnimalIndexException("无效的动物序号");
        }

        // 出售动物
        Animal soldAnimal = animalList.remove(animalIndex);

        // 钱入账
        balance += soldAnimal.getPrice();

        // 输出动物信息
        System.out.println("出售动物：" + soldAnimal);
        System.out.println("顾客" + customer.getCustomerName() + "购买成功");
        //用户到店次数+1
        customer.setVisitCount(customer.getVisitCount() + 1);
        //更新最新到店时间
        customer.setLatestVisitDate(LocalDate.now());
    }

    @Override
    public void closeShop() {
        // 歇业的逻辑
        isOpen = false;
        System.out.println("店铺今日营业结束！");
        System.out.println("今日光顾的顾客数量：" + customerList.size());
        // 输出顾客列表
        System.out.println("今日光顾的顾客列表：");
        for (Customer customer : customerList) {
            System.out.println("  " + customer);
        }
        System.out.println("今日利润：" + balance);
    }
    // getter和setter方法
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
