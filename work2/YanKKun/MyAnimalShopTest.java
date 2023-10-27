package Animal;

import java.time.LocalDate;

/**
 *
 * @author 12080
 *用于测试宠物店功能
 *
 **/

public class MyAnimalShopTest {
    public static void main(String[] args) {
        Animal dog1 = new Dog("好狗",2,"公",100,true);
        Animal dog2 = new Dog("坏狗",1,"母",100,true);
        Animal cat1 = new Cat("好猫",3,"公",200,"加菲猫");
        Animal cat2 = new Cat("坏猫",4,"母",200,"蓝猫");

        MyAnimalShop first =new MyAnimalShop(1000,true);
        MyAnimalShop second =new MyAnimalShop(500,false);
        MyAnimalShop third =new MyAnimalShop(-100,true);
        //余额不能为负值

        Customer c1 = new Customer("张三",0, LocalDate.now());
        Customer c2 = new Customer("李四",2, LocalDate.now());
        Customer c3 = new Customer("王五",1, LocalDate.now());

        first.buyAnimals(dog1,2);
        //买入2只Dog{Name='好狗', Age=2, Gender='公', Price=100.0, Species='中华田园犬'}成功,还剩下800.0元
        //second.buyAnimals(dog2,10);
        //Exception in thread "main" Animal.InsufficientBalanceException: 余额不足无法购买

        first.serverCustomers(c1,dog1);
        //成功出售Dog{Name='好狗', Age=2, Gender='公', Price=100.0, Species='中华田园犬'}

        second.serverCustomers(c2,cat1);
        //商店尚未营业！

        second.open();
        //宠物店开业
        //second.serverCustomers(c2,cat1);
        //Exception in thread "main" Animal.AnimalNotFountException: 找不到对应动物

        first.buyAnimals(cat2,3);
        //买入3只Cat{Name='坏猫', Age=4, Gender='母', Price=200.0, Species='蓝猫'}成功,还剩下310.0元
        first.buyAnimals(dog2,2);
        //买入2只Dog{Name='坏狗', Age=1, Gender='母', Price=100.0, Species='中华田园犬'}成功,还剩下110.0元
        first.serverCustomers(c3,cat2);
        //成功出售Cat{Name='坏猫', Age=4, Gender='母', Price=200.0, Species='蓝猫'}
        first.serverCustomers(c2,dog2);
        //成功出售Dog{Name='坏狗', Age=1, Gender='母', Price=100.0, Species='中华田园犬'}

        first.close();
        //宠物店歇业
        //今日利润为40.0元
        //今日光临的顾客如下
        //Customer{name='张三', arrivalTimes=1, lastestArrivalTime=2023-10-26}
        //Customer{name='王五', arrivalTimes=2, lastestArrivalTime=2023-10-26}
        //Customer{name='李四', arrivalTimes=3, lastestArrivalTime=2023-10-26}

        first.buyAnimals(dog1,3);
        first.open();
        first.serverCustomers(c1,dog1);
        first.close();
        //买入3只Dog{Name='好狗', Age=2, Gender='公', Price=100.0, Species='中华田园犬'}成功,还剩下130.0元
        //宠物店开业
        //成功出售Dog{Name='好狗', Age=2, Gender='公', Price=100.0, Species='中华田园犬'}
        //宠物店歇业
        //今日利润为10.0元
        //今日光临的顾客如下
        //Customer{name='张三', arrivalTimes=2, lastestArrivalTime=2023-10-26}
    }
}
