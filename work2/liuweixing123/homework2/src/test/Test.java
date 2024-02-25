package test;
// try catch 需要修改
import bean.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cat cat2 = new Cat("喵喵", 2, "公");
        Cat cat1 = new Cat("武斗酷猫", 7, "母");
        ChineseRuralDog dog2 = new ChineseRuralDog("音速战犬", 2, "母", true);
        ChineseRuralDog dog1 = new ChineseRuralDog("大黄", 2, "公", false);
        ArrayList<Animal> List = new ArrayList<Animal>();
        List.add(dog1);
        List.add(dog2);
        List.add(cat1);
        List.add(cat2);

        //创建我的宠物店对象
        MyAnimalShop myAnimalShop1 = new MyAnimalShop(150, List, new ArrayList<Customer>(), true, 400, 300);
        myAnimalShop1.showMyPetShop();

        //添加宠物的测试
        Animal animal1 = new ChineseRuralDog("卡蒂狗", 4, "公", true);
        Animal animal2 = new Cat("哆啦a梦", 3, "小女孩");
        myAnimalShop1.buyPet(animal1);
        myAnimalShop1.buyPet(animal2);
        myAnimalShop1.showMyPetShop();

        //招待顾客的方法
        myAnimalShop1.entertainCustomer(new Customer("小诗诗",0,LocalDate.now()));
        myAnimalShop1.entertainCustomer(new Customer("小慧慧",0,LocalDate.now()));
    
        //歇业
        myAnimalShop1.closeShop();
    }
}

