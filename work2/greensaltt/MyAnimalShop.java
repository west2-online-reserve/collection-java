package animalshop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 买入动物、招待客户、歇业
 */
public class MyAnimalShop implements AnimalShop {

    /**
     * 店内初始余额（用于计算利润）
     */
    private double begin;

    /**
     * 营业时余额
     */
    private double rest;

    public void setBegin(double begin) {
        this.rest = begin;
        this.begin = begin;
    }

    public double getBegin() {
        return begin;
    }

    /**
     * 店内拥有的动物列表
     */
    private ArrayList<Animal> hasAnimals = new ArrayList<Animal>();

    /**
     * 光顾本店的顾客列表留作纪念
     */
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public ArrayList<Animal> getHasAnimals() {
        return hasAnimals;
    }


    // 是否关店
    private boolean isClosed = false;


    /**
     * 购买宠物
     *
     * @param animal 宠物
     */
    @Override
    public void buyNewAnimal(Animal animal) {
        // 方法实现购买动物
        System.out.println("========正在购买" + animal.getAnimalName() + "========");
        try {
            // 当购买狗，但狗未接种疫苗时
            if (animal instanceof ChineseRuralDog &&
                    ((ChineseRuralDog) animal).isVaccineInjected() == false) {
                System.out.println("该中华田园犬未接种疫苗，不能购买");
                // 终止该方法
                return;
            }
            if (begin < animal.getPrice()) {
                // 余额不足抛出InsufficientBalanceException异常
                throw new InsufficientBalanceException();
            } else {
                // 余额足够，购买动物
                // 购买成功后加入hasAnimals
                hasAnimals.add(animal);
                // 余额改变
                rest -= animal.getPrice();
                // 输出购买成功的猫猫信息
                System.out.println("购买" + animal.getAnimalName() + "成功，" + "余额为：" + rest);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
    }


    /**
     * 招待顾客
     *
     * @param customer 顾客
     */
    @Override
    public void treatCustomer(Customer customer,Animal animal) {
        // 实现方法接待顾客
        System.out.println("========正在接待顾客========");
        // Scanner接受顾客信息
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顾客姓名：");
        customer.setCustomerName(in.nextLine());
        System.out.println("请输入顾客到店次数：");
        customer.setTimes(in.nextInt());
        // 顾客最新到店时间为当下
        customer.setNewestTime(LocalDate.now());
        // 在顾客列表加入该顾客
        customers.add(customer);

        try {
            // 判断顾客需要什么动物
            int j = 0;
            for (int i = 0; i < hasAnimals.size(); i++) {
                if (hasAnimals.get(i).equals(animal)) {
                    // 存在顾客需要的动物
                    // 输出店内已有动物信息
                    System.out.println("店内有" + hasAnimals.get(i).getAnimalName());
                    // 余额改变
                    rest = rest + hasAnimals.get(i).getSell();
                    // 输出购买成功信息
                    System.out.println("出售" + hasAnimals.get(i).getAnimalName()
                            + "成功，余额为：" + rest);
                    // 从动物列表移除售出动物
                    hasAnimals.remove(i);
                    // 用于判断顾客得到了要买的动物
                    j++;
                }
            }
            if (j == 0) {
                // 店内没有顾客需要的动物，抛出AnimalNotFoundException异常
                System.out.println("店内没有" + animal.getAnimalName());
                throw new AnimalNotFoundException();
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void Closed() {
        // 实现方法歇业
        System.out.println("=========歇业========");

        /**
         * 遍历顾客列表内容
         *
         * 输出今日光顾的顾客信息
         *
         */
        for (int i = 0; i <= customers.size() - 1; i++) {
            // LocalDate判断是否当天
            System.out.println("以下为今日光顾的顾客：\n"+customers.get(i));
            if (customers.get(i).getNewestTime().isEqual(LocalDate.now())) {
                // 输出顾客信息
                customers.get(i).toString();
            }
        }

        /**
         * 计算并输出一天的利润
         */
        System.out.println("今日的利润为：" + (rest - begin));

        /**
         * 收工！！！
         */
        if (isClosed == false) {
            isClosed = true;
            System.out.println("是否关店：" + isClosed);
        }
    }
}
