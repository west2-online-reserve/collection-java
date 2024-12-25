package Test;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        try {
            // 创建动物店
            MyAnimalShop shop = new MyAnimalShop(2000, LocalDate.of(2024, 12, 10));

            // 宠物店的狗狗
            Animal Ken = new Dog("Ken", 2, "boy", true);
            Animal Carl = new Dog("Carl", 3, "boy", true);
            Animal Lisa = new Dog("Lisa", 1, "girl", true);
            Animal Alice = new Dog("Alice", 2, "girl", true);
            // 宠物店的猫猫
            Animal John = new Cat("John", 1, "boy");
            Animal Kevin = new Cat("kevin", 2, "boy");
            Animal Julie = new Cat("Julie", 1, "girl");
            Animal Rose = new Cat("Rose", 2, "girl");
            // 顾客
            Customer Biden = new Customer("A", 1, LocalDate.of(2024, 12, 10));
            Customer Trump = new Customer("B", 1, LocalDate.of(2024, 12, 11));
            Customer Harris = new Customer("C", 1, LocalDate.of(2024, 12, 12));
            // 买入动物
            shop.buyNewPet(Ken);
            shop.buyNewPet(Carl);
            shop.buyNewPet(Lisa);
            shop.buyNewPet(Alice);
            shop.buyNewPet(John);
            shop.buyNewPet(Kevin);
            shop.buyNewPet(Julie);
            shop.buyNewPet(Rose);
            // 接待顾客
            shop.receiveGuests(Biden);
            shop.receiveGuests(Trump);
            shop.receiveGuests(Harris);
            // 关门
            shop.close();
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
