package Xuxiiii;
import java.time.LocalDate;

public class TEST {
    public static void main(String[] args) {
        MyAnimalShop a = new MyAnimalShop("穷店", 100, true);
        MyAnimalShop b = new MyAnimalShop("豪华的宠物店", 90000, true);
        MyAnimalShop c = new MyAnimalShop("啥也不是", 0, false);

        Dog dog1=new Dog("kunkun",1,"male",100,true);
        Dog dog2=new Dog("durant",10000,"male",100,true);
        Dog dog3=new Dog("le",114,"female",100,false);
        Cat cat0=new Cat("xiaomao",514,"male",200);
        Cat cat1=new Cat("tgct",45,"female",200);
        Pig pig1=new Pig("ggbond",15,"male",500);
        Customer c0=new Customer("Kobe",1,LocalDate.now());
        Customer c1=new Customer("HH",2,LocalDate.now());

        a.buy(dog1);
        b.buy(pig1);
        b.buy(cat1);
        b.buy(cat0);


        a.entertain(c0,1);
        b.entertain(c1,1);
        b.entertain(c1,2);

        a.close();
        b.close();
    }
}
