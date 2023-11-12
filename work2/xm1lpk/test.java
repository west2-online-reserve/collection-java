package peikailv;


import java.time.LocalDate;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        MyanimalShop myanimalShop = new MyanimalShop();
        Scanner scanner = new Scanner(System.in);
        //  设置初始余额
        myanimalShop.setNum(160.0);

        try {
            ChineseTianyuanDog chineseTianyuanDog1 = new ChineseTianyuanDog("John", 3, 1, 50);
            myanimalShop.addanimal(chineseTianyuanDog1);

            ChineseTianyuanDog chineseTianyuanDog2 = new ChineseTianyuanDog("Jack", 4, 0, 50);
            myanimalShop.addanimal(chineseTianyuanDog2);

            Cat cat1 = new Cat("Alice", 4, 1, 60);
            myanimalShop.addanimal(cat1);

            //  购买第四个动物时余额不足，抛出异常，并输出无法购买的动物的信息
            Cat cat2 = new Cat("Tom", 5, 0, 60);
            myanimalShop.addanimal(cat2);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage()+"\n");
        }

        try {
            Customer c1 = new Customer("LeBron", 100, LocalDate.now());
            Customer c2 = new Customer("James", 0, LocalDate.now());
            // 顾客1购买时店铺处于关闭状态，无法购买
            myanimalShop.dealCustomers(c1);
            //  开启店铺
            myanimalShop.setIsopen(true);
            myanimalShop.dealCustomers(c2);
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
    }

}
