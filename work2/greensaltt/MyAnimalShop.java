package animalshop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 买入动物、招待客户、歇业
 */
public class MyAnimalShop implements AnimalShop{

    // 店内初始余额（用于计算利润）
    public double begin;

    // 营业时余额
    public double rest;

    // 店内拥有的动物列表
    ArrayList<Animal> hasAnimals = new ArrayList<Animal>();

    // 光顾本店的顾客列表留作纪念
    ArrayList<Customer> customers = new ArrayList<Customer>();

    // 是否关店
    public boolean isClosed = false;

    /**
     * 购买猫猫类
     *
     * @param cat 猫猫对象
     */
    @Override
    public void buyNewAnimal(Cat cat) {
        // 方法实现购买猫猫
        System.out.println("========正在购买猫猫========");
        try {
            if(begin < cat.price){
                // 余额不足抛出InsufficientBalanceException异常
                throw new InsufficientBalanceException();
            }else{
                // 余额足够，购买猫猫
                // 购买成功后加入hasAnimals
                hasAnimals.add(cat);
                // 余额改变
                rest = begin - cat.price;
                // 输出购买成功的猫猫信息
                System.out.println("购买"+cat.animalName+"成功，"+"余额为："+rest);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
    }

    /**
     * 购买狗狗类
     * @param dog
     */
    @Override
    public void buyNewAnimal(ChineseRuralDog dog) {
        // 实现方法购买狗狗
        System.out.println("========正在购买中华田园犬========");
        try {
            if(begin < dog.price){
                // 余额不足抛出InsufficientBalanceException异常
                throw new InsufficientBalanceException();
            }else if(dog.isVaccineInjected == false) {
                // 如果这只狗狗没有接种疫苗就不购买
                System.out.println("该中华田园犬未接种疫苗，不能购买");
            }else {
                // 余额足够，购买狗狗
                // 购买成功后加入hasAnimals
                hasAnimals.add(dog);
                // 余额改变
                rest = begin - dog.price;
                // 输出购买的狗狗信息
                System.out.println("购买" + dog.animalName + "成功，余额为：" + rest);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
    }

    /**
     * 购买可爱不能吃类
     * @param rabbit
     */
    @Override
    public void buyNewAnimal(Rabbit rabbit) {
        // 实现方法购买兔兔
        System.out.println("========正在购买兔兔========");
        try {
            if(begin < rabbit.price){
                // 余额不足抛出InsufficientBalanceException异常
                throw new InsufficientBalanceException();
            }else{
                // 余额足够，购买兔兔
                // 购买成功后加入hasAnimals
                hasAnimals.add(rabbit);
                // 余额改变
                rest = begin - rabbit.price;
                // 输出购买的狗狗信息
                System.out.println("购买"+rabbit.animalName+"成功，"+"余额为："+rest);
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
    public void treatCustomer(Customer customer) {
        // 实现方法接待顾客
        System.out.println("========正在接待顾客========");
        // Scanner接受顾客信息
        Scanner in = new Scanner(System.in);
        System.out.println("请输入顾客姓名：");
        customer.customerName = in.nextLine();
        System.out.println("请输入顾客到店次数：");
        customer.times = in.nextInt();
        // 顾客最新到店时间为当下
        customer.newestTime = LocalDate.now();
        try {
            // 在顾客列表加入该顾客
            customers.add(customer);
            if(hasAnimals.size() == 0){
                // 店内没有动物，抛出AnimalNotFoundException异常
                throw new AnimalNotFoundException();
            }else{
                // 输出店内已有动物信息
                hasAnimals.get(0).toString();
                // 余额改变
                // 不管什么动物利润都为300
                rest = rest + hasAnimals.get(0).sell;
                // 输出购买成功信息
                System.out.println("出售"+hasAnimals.get(0).animalName+"成功，余额为："+rest);
                // 从动物列表移除售出动物
                hasAnimals.remove(0);
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void Closed(){
        // 实现方法歇业
        System.out.println("=========歇业========");

        /**
         * 遍历顾客列表内容
         *
         * 输出今日光顾的顾客信息
         *
         * 备注：这里localdate判断不太懂判断什么
         * 如果有错的话请学长学姐告诉我
         * 拜托！
         */
        for(int i = 0;i <= customers.size()-1;i++){
            // LocalDate判断是否当天
            System.out.println("以下为今日光顾的顾客：");
                if(customers.get(i).newestTime.isEqual(LocalDate.now())){
                    // 输出顾客信息
                    customers.get(i).toString();
                }
            }

        /**
         * 计算并输出一天的利润
         */
        System.out.println("今日的利润为："+(rest - begin));

        /**
         * 收工！！！
         */
        if(isClosed == false){
            isClosed = true;
        }
    }
}
