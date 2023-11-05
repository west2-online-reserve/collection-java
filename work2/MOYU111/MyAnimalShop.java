
import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    //余额
    private double money;

    //是否营业
    private boolean isOnBusiness = true;
    //动物的列表
    ArrayList<animal> animal = new ArrayList<animal>();
    //顾客的列表
    ArrayList<Customer> customer = new ArrayList<Customer>();

    //初始化余额和
    public MyAnimalShop(double m) {
        this.money = m;
    }

    public void add(animal a) {
        try {
            if (a.price > money){
                throw new InsufficientBalanceException(a.name,money);
            }
            else{
                money -= a.price;
                animal.add(a);
            }

        }

        catch (InsufficientBalanceException e) {
            System.out.println(e);
        }

    }
    public void treat(Customer c,int i) {
        customer.add(c);
        c.setTimes(c.getTimes() + 1);
        c.setNow(LocalDate.now());
        //判断是否有宠物
        try {
            if (animal.size() != 0){
                System.out.println("买第" + i + "只宠物"+"\n");
                System.out.println(animal.get(i-1));
                money += animal.get(i-1).price;
                animal.remove(i-1);
            }else {
                throw new AnimalNotFountException(animal.size(),c);
            }
        }

        catch (AnimalNotFountException e) {
            System.out.println(e);
        }
    }

    public void close() {
        this.isOnBusiness = false;
        for (int i = 0; i < customer.size(); i++) {
            System.out.println("第" + (i + 1) + "位顾客的信息为 " + customer.get(i));
        }

        System.out.println("今日商店剩余总金额为" + money + "\n");
    }

}
