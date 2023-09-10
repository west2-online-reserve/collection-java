package Work2complete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

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
    public MyAnimalShop(double m) {
        this.money = m;
    }

    //这里的test1用于add函数，判断是否错误
    public void detectExceptionForAdd(Animal a, double price) throws InsufficientBalanceException {
        if (price > money) {
            throw new InsufficientBalanceException(a.name, money);
        }
        money -= price;
        animal.add(a);
    }

    public void add(Animal a) {
        try {
            detectExceptionForAdd(a, a.price);
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }

    }

    //////////////////////////////////////////
    public void treat(Customer c,int i) {
        customer.add(c);
        c.setTimes(c.getTimes() + 1);
        c.setNow(LocalDate.now());
        //判断是否有库存宠物
        try {
            if (animal.size() != 0){
                System.out.println("买第" + i + "只宠物");
                System.out.println(animal.get(i - 1));
                money += animal.get(i - 1).price;
                animal.remove(i - 1);
            }else {
                throw new AnimalNotFoundException(animal.size(),c);
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }

    //以下函数用于顾客购买宠物
   /* public void shopping(int i) {
        System.out.println("买第" + i + "只宠物");
        System.out.println(animal.get(i - 1));
        money += animal.get(i - 1).price;
        animal.remove(i - 1);
    }
*/
    public void close() {
        this.isOnBusiness = false;
        for (int i = 0; i < customer.size(); i++) {
            System.out.println("第" + (i + 1) + "的顾客的信息为 " + customer.get(i));
        }

        System.out.println("今日的总金额为" + money);
    }

}
