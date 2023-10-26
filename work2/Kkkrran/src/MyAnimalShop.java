/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */

import java.time.LocalDate;
import java.util.HashMap;

public class MyAnimalShop implements AnimalShop {
    private final double rateOfCostAndPrice = 0.4;
    private double balance = 0.0;
    private double profit = 0.0;
    private MyLinkedList<Animal> animals = new MyLinkedList<>();
    private boolean isOpen = false;
    private boolean ifPrintAnimals;
    private double totalSales;
    private int totalAnimalSoldNum;
    private HashMap<Animal, Customer> ownerList = new HashMap<>();
    private HashMap<LocalDate, MyLinkedList<Customer>> customers = new HashMap<>();


    @Override
    public void buyIn(Animal animalIn) {
        if (!isOpen) {
            System.out.println("本店歇业。");
            return;
        }

        double cost = animalIn.price * rateOfCostAndPrice;
        if (balance < cost) {
            throw new InsufficientBalanceException("余额不足，无法购买新动物。");
        }
        balance -= cost;
        animals.add(animalIn);
        //System.out.println("买入成功！");

    }

    @Override
    public void customerReception(Customer c, String typeOfAnimal) {
        if (!isOpen) {
            System.out.println("本店歇业，请谅解。");
            return;
        }


        helloToCustomer(c);

        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("抱歉，本店没有宠物在售。");
        }
        if(ifPrintAnimals){
            printAnimals();
        }

        selling(c, typeOfAnimal);
    }

    public void selling(Customer c, String typeOfAnimal) {
        boolean found = false;
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            if (a.getClass().getSimpleName()
                    .equalsIgnoreCase(typeOfAnimal)) {
                found = true;
                sellAnimalTo(a, c);

                balance += a.getPrice();
                animals.remove(a);
                // fix_bug: miss "break;"
                break;

            }
        }
        if (!found) {
            System.out.println("抱歉，本店没有属于该物种的宠物。");
        }
    }

    public void sellAnimalTo(Animal a, Customer c) {
        animals.remove(a);
        ownerList.put(a, c);
        System.out.println("顾客 " + c.getName()
                + " 成功购买一只 " + a.getClass().getSimpleName()
                + "\n 其信息：" + a.toString());
    }


    @Override
    public void close() {
        isOpen = false;
        // profit内保存着开业的余额数据
        profit = balance - profit;
        System.out.println("本店歇业，今天是 " + LocalDate.now());
        System.out.println("今天光顾的顾客有\n" + customers.get(LocalDate.now()).toString());
        System.out.println("本店余额为 " + balance);
        System.out.println("今天的利润是 " + profit);

    }

    public void open() {
        isOpen = true;
        // 把开业的余额数据暂存进profit
        profit = balance;
        System.out.println("本店开业，今天是 " + LocalDate.now());
        customers.put(LocalDate.now(), new MyLinkedList<Customer>());

    }

    public void getMoney(double x) {
        balance += x;
    }

    public void printAnimals() {
        System.out.println("本店在售宠物如下");
        System.out.println(animals.toString());
    }

    public void helloToCustomer(Customer c) {
        c.setVisitNum(c.getVisitNum() + 1);
        if (!customers.get(LocalDate.now()).contains(c)) {
            customers.get(LocalDate.now()).add(c);
        }
        System.out.println("您好， " + c.getName() + " ，" + "欢迎光临！");
        System.out.println("这是您第 " + c.getVisitNum() + " 次光临。");
        if (c.getVisitNum() > 1) {
            System.out.println("您上次的光临时间是 " + c.getLatestArrivalDate());
        }
        c.setLatestArrivalDate(LocalDate.now());
    }

    public boolean isOpen() {
        return isOpen;
    }
    public void setIfPrintAnimals(boolean b){
        ifPrintAnimals = b;
    }


}
