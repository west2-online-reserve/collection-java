
import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    //余额
    private double money;

    //是否营业
    private boolean isOnBusiness = true;
    //动物的列表
    private ArrayList<animal> animal = new ArrayList<animal>();
    //顾客的列表
    private ArrayList<Customer> customer = new ArrayList<Customer>();

    public MyAnimalShop(double m) {
        this.money = m;
    }

    public void add(animal a) {
        try {
            if (a.getPrice() > money){
                throw new InsufficientBalanceException(a.getName(),money);
            }
            else{
                money -= a.getPrice();
                animal.add(a);
            }

        }

        catch (InsufficientBalanceException e) {
            System.out.println(e);
        }

    }
    public void treat(Customer c,int i) {
        int index = 0;
        // 当前顾客是否已经存在
        boolean customerNotExist = true;
        // 搜索当前顾客是否存在
        for (int x=0; x<customer.size(); x++) {
            if (customer.get(x) == c) {
                // 位置改为表中位置
                index = x;
                customerNotExist = false;
            }
        }
        // 若不存在则加入列表
        if (customerNotExist){
            customer.add(c);
            index = customer.size()-1;
        }
        c.setTimes(c.getTimes() + 1);
        c.setNow(LocalDate.now());
        //判断是否有宠物
        try {
            if (animal.size() != 0){
                System.out.println("买第" + i + "只宠物"+"\n");
                System.out.println(animal.get(i-1));
                money += animal.get(i-1).getPrice();
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
