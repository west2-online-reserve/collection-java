package Work2complete;

import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    //余额
    private double money;

    //是否营业
    private boolean isOnBusiness = true;
    //动物的列表
    ArrayList<Animal> animal = new ArrayList<Animal>();
    //顾客的列表
    ArrayList<Customer> customer = new ArrayList<Customer>();
    //初始化余额和
    public MyAnimalShop (double m){
        this.money = m;
    }
    //这里的test1用于add函数，判断是否错误
    public void test1(Animal a, double price) throws InsufficientBalanceException {
        if (price > money) {
            throw new InsufficientBalanceException(a.name, money);
        }
        money -= price;
        animal.add(a);
    }

    public void add(Animal a) {
        try {
            test1(a, a.price);
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }

    }

    //////////////////////////////////////////
    //这里的test2用于判断宠物是否售空
    public void test2(int a,Customer c) throws AnimalNotFoundException {
        if (a == 0) {
            throw new AnimalNotFoundException(a,c);
        }
    }
    public void treat(Customer c) {
            customer.add(c);
            c.setTimes(c.getTimes()+1);
        //判断是否有库存宠物
        try {
            test2(animal.size(),c);
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }
    //以下函数用于顾客购买宠物
    public void shopping(int i,Animal a){
        System.out.println("买第"+i+"只宠物");
        System.out.println(animal.get(i-1));
        animal.remove(i-1);
        money += a.price;
    }

    public void close() {
        this.isOnBusiness = false;
        for (int i =0 ;i<customer.size();i++){
            System.out.println("第"+(i+1)+"的顾客的信息为 "+customer.get(i));
        }

        System.out.println("今日的总金额为"+money);
    }

}
