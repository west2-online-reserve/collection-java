package Test;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // 创建动物店实例
        MyAnimalShop shop = new MyAnimalShop();

        /**
         * 创建一个集合来储存对象
         * 然并卵，我本想按照动物名字进行操作，但是这似乎太复杂了，我做不来，于是我只用cat dog操作
         * 但我这精心挑选的表头我还是舍不得删，就放在这里了
         */

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Ken", 2, "boy", true));
        animals.add(new Dog("Carl", 3, "boy", true));
        animals.add(new Dog("Lisa", 1, "girl", true));
        animals.add(new Dog("Alice", 2, "girl", true));
        animals.add(new Cat("John", 1, "boy"));
        animals.add(new Cat("kevin", 2, "boy"));
        animals.add(new Cat("Julie", 1, "girl"));
        animals.add(new Cat("Rose", 2, "girl"));

        System.out.println("以下是商店可以购买的狗狗和猫猫，输入名字和个数让商店购买，输入“购买完毕”商店就会开店接待顾客：");
        // 打印表头
        System.out.printf("%-5s %-10s %-5s %-6s %-10s %-10s %-5s%n",
                "Type", "Name", "Age", "Gender", "Price", "Cost", "Vaccine");
        System.out.println("-------------------------------------------------------------");
        // 打印动物信息
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        // 调用buyNewPet方法，让商店购买之后开店
        System.out.println();
        shop.buyNewPet();

        // 调用receiveGuests方法，商店开店日期为2025.1.1，迎接顾客
        shop.receiveGuests();
    }
}
