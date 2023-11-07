import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 102301617
 */
@SuppressWarnings("ALL")
public class MyAnimalShop implements AnimalShop {


    /**
     * 用于规范输入
     */
    private static String male = "公";
    private static String female = "母";
    private final double assets = 1500;
    /**
     * 动物列表
     */
    private ArrayList list = new ArrayList();
    /**
     * 顾客列表
     */
    private ArrayList costom = new ArrayList();
    /**
     * 是否营业
     */
    private Boolean run = true;
    private double profits;
    private Animal animal;
    private double balance = 1500;

    public static String getMale() {
        return male;
    }

    public static void setMale(String male) {
        MyAnimalShop.male = male;
    }

    public static String getFemale() {
        return female;
    }

    public static void setFemale(String female) {
        MyAnimalShop.female = female;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public ArrayList getCostom() {
        return costom;
    }

    public void setCostom(ArrayList costom) {
        this.costom = costom;
    }

    public Boolean getRun() {
        return run;
    }

    public void setRun(Boolean run) {
        this.run = run;
    }

    public double getProfits() {
        return profits;
    }

    public void setProfits(double profits) {
        this.profits = profits;
    }

    public double getAssets() {
        return assets;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void perchase(Animal animal) {

        try {
            if (!run) {
                System.out.println("宠物店未在运营状态\n");
                return;
            }
            if (balance >= animal.cost) {
                balance -= animal.cost;
                list.add(animal);
                System.out.println("购买成功");
                System.out.println("余额：" + balance);

            } else {
                throw new InsufficientBalanceException();

            }


        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());

        }
    }

    @Override
    public void entertaining(Customer customer) {
        Scanner scanner = new Scanner(System.in);


        try {
            if (!run) {
                System.out.println("宠物店已打烊\n");


            }
            if (!list.isEmpty()) {
                costom.add(customer);
                System.out.println("顾客购买的宠物为");
                int number = (int) (Math.random() * list.size());
                Animal animal = (Animal) list.get(number);
                list.remove(number);
                balance += animal.price * number;
                System.out.println(animal);
                int i = 0;
                customer.setVisit(i++);
                customer.setTime(LocalDate.now());

            } else {
                throw new AnimalNotFountException();
            }
        } catch (AnimalNotFountException e) {
            System.out.println(e.toString());
        }


    }

    @Override
    public void close() {
        profits = assets - balance;
        System.out.println("今天赚了" + profits + "元");
        System.out.println("有以下顾客光顾");
        System.out.println(costom);
        System.out.println("以下是宠物店剩余动物");
        System.out.println(list);
        LocalDate date = LocalDate.now();
        int dayOfWeekValue = date.getDayOfWeek().getValue();
        if (dayOfWeekValue<= 5) {
            run = false;
        }
    }
}

