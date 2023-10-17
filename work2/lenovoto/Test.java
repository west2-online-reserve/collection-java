package homework.work2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        RuralDog D1=new RuralDog("中华田园犬",2,"公",100,true,50);


        Cat C1=new Cat("猫猫",1,"母",200,60);


        Fish F1=new Fish("泡泡",1,"公",500,70);

        System.out.println("========================================================");

        Customer Piper=new Customer();
        MyAnimalShop bill=new MyAnimalShop(1000,1000);

        //买入动物
        bill.buyAnimals(D1);
        bill.buyAnimals(F1);
        bill.buyAnimals(C1);

        //招待顾客，出售动物
        bill.treatCustomers(F1,Piper);
        bill.isOpenShop();





    }
}
