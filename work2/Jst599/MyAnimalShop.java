package PetShop;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * MyAnimalShop类表示自己的宠物店
 *
 * 该类包含宠物店的基本信息和操作
 * @author Jst599
 * @date 2023/10/17
 */
public class MyAnimalShop implements PetShop.AnimalShop {
    private String shopName;
    private double leftMoney;
    private ArrayList<PetShop.PetShop.Animal> storeAnimal = new ArrayList<>();
    private ArrayList<PetShop.Customer> storeCustomer = new ArrayList<>();
    // 用来判断宠物店是否营业
    private boolean isClosed;
    private int profit;

    public MyAnimalShop() {
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public ArrayList<PetShop.PetShop.Animal> getStoreAnimal() {
        return storeAnimal;
    }

    public void setStoreAnimal(ArrayList<PetShop.PetShop.Animal> storeAnimal) {
        this.storeAnimal = storeAnimal;
    }

    public ArrayList<PetShop.Customer> getStoreCustomer() {
        return storeCustomer;
    }

    public void setStoreCustomer(ArrayList<PetShop.Customer> storeCustomer) {
        this.storeCustomer = storeCustomer;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public MyAnimalShop(double leftMoney, boolean isClosed, String shopName) {
        this.leftMoney = leftMoney;
        this.isClosed = isClosed;
        this.shopName = shopName;
    }

    @Override
    public void buyAnimal(PetShop.Animal animal) {
        try{
                if (leftMoney >= animal.getInputPrice()){
                    leftMoney -= animal.getInputPrice();
                    System.out.println(this.shopName + "购买" + animal.getName());
                    storeAnimal.add(animal);

                } else {
                    throw new InsufficientResourcesException("余额不足");
                }
        }catch (InsufficientResourcesException e){
            System.out.println(e.toString());
          }
        }

    @Override
    public void serveCustomer(PetShop.Customer customer) {
        try{
            if (isClosed){
                System.out.println(this.shopName + "关门了,下次再来");
            }else {
                System.out.println("欢迎光临" + this.shopName);
                if (!storeAnimal.isEmpty()){
                    Random r = new Random();
                    storeCustomer.add(customer);
                    PetShop.Animal animal = storeAnimal.get(r.nextInt(storeAnimal.size()));
                    storeAnimal.remove(r.nextInt(storeAnimal.size()));
                    profit += animal instanceof PetShop.Cat ? 50 : 75;
                    System.out.println(customer.getName() + "买入" + animal.name + ","+  animal.name + "的具体信息为" + animal.toString());
                    customer.getFrequency() += 1;
                    customer.getTimeNew() = LocalDate.now();
                    if (animal instanceof PetShop.ChineseDog){
                        if (!((PetShop.ChineseDog) animal).getisVaccineInjected()){
                            System.out.println("尊敬的" + customer.getName() + "顾客,您买的狗狗没有注射狂犬疫苗,为了您的安全请自行到宠物医院注射");
                        }
                    }
                    // 首先买的动物是随机的,不能直接用猫或狗的对象,Animal也不能直接创建对象,
                    // 唯一能找到Animal类的东西就是存放Animal类的数组,直接取数组里面的...
                    // 要判断是不是猫(狗)得用上instanceof,此时可以用if判断加上getPrice,因为
                    // 只有猫和狗,所以我可以用上? :,利用判断直接得结果
                }else {
                    throw new PetShop.AnimalNotFoundException("店内没有动物");
                }
            }
        }catch (PetShop.AnimalNotFoundException e){
            System.out.println(e.toString());
        }
    }


    @Override
    public void OutOfWork() {
        // 判断顾客最新到店时间与当天日期一致,因为可能不止一个顾客,所以需要通过循环遍历整个列表
        // 时间都是LocalDate类,不能直接用==号判断,得用equals
        for (PetShop.Customer customer : this.storeCustomer){
            if (customer.getTimeNew().equals(LocalDate.now())){
                System.out.println("当天来光临的顾客" + customer.toString());
            }
        }
        System.out.println(this.shopName + "当天利润为:" + profit);
        leftMoney += profit;
        profit = 0;
    }
}
