package com.west2.work2;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test类用于测试数据
 *
 * @author Chen Shining
 * @Date 2023/11/4
 */
public class Test {
    public static void main(String[] args) {
        //初始化动物列表和顾客列表
        ArrayList<Animal> pets = new ArrayList<>();
        Cat cat01 = new Cat("波斯因", 1, 2500, "雄");
        Cat cat02 = new Cat("来可盈", 3, 2588, "雄");
        ChineseRuralDog dog01 = new ChineseRuralDog("瑞士卷", 5, 3720, "雄", true);
        ChineseRuralDog dog02 = new ChineseRuralDog("哈士奇", 2, 4620, "雌", true);
        pets.add(cat01);
        pets.add(cat02);
        pets.add(dog01);
        pets.add(dog02);
        ArrayList<Customer> customers = new ArrayList<>();

        //创建一个宠物店实例
        MyAnimalShop petShop = new MyAnimalShop(5000, pets, customers, true, 0.0);

        //买入动物
        System.out.println("--------宠物店正在购买新的宠物--------");
        Cat cat03 = new Cat("裁鼎", 3, 4622, "雄");
        ChineseRuralDog dog03 = new ChineseRuralDog("忘才", 2, 3822, "雄", true);
        try {
            petShop.buyNewAnimal(cat03);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
        try {
            petShop.buyNewAnimal(dog03);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
        System.out.println("----------------------------------------");
        System.out.println();

        System.out.println("--------正在查看宠物店内的所有宠物：--------");
        System.out.println("姓名，性别，年龄，价格");
        petShop.showPets();
        System.out.println("----------------------------------------");
        System.out.println();

        System.out.println("--------现在开始招待顾客--------");
        LocalDate time = LocalDate.of(2023, 5, 8);
        Customer customer01 = new Customer("马里奥", 0, time);
        try {
            petShop.treatCustomer(customer01);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("--------查看商店的余额和剩余的宠物--------");
        System.out.println("余额：" + petShop.getBalance());
        if (!petShop.getPetsList().isEmpty()) {
            System.out.println("姓名，性别，年龄，价格");
            petShop.showPets();
        } else {
            System.out.println("店里没有宠物了噢，要及时进货咯！");
        }
        System.out.println("----------------------------------------");
        System.out.println();

        System.out.println("--------宠物店歇业--------");
        petShop.outOfBusiness();
        System.out.println("----------------------------------------");
        System.out.println();
    }
}
