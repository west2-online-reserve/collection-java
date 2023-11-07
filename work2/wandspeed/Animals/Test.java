import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000);
        // 测试InsufficientBalanceException
        Animal dog = new Dog("最贵的狗", 3, "Male", 10000, true);
        shop.buyAnimal(dog);
        // 测试AniamlNotFoundException
        Customer customer1 = new Customer("张三", 0, LocalDate.now());
        shop.serveCustomer(customer1, 1);
        // 买入
        Animal dog1 = new Dog("旺财", 3, "Male", 100, true);
        shop.buyAnimal(dog1);
        Animal cat1 = new Cat("咪咪", 2, "Female", 200);
        shop.buyAnimal(cat1);
        Animal cat2 = new Cat("Tom", 12, "Female", 200);
        shop.buyAnimal(cat2);
        Animal dog2 = new Dog("Jack", 5, "Male", 100, false);
        shop.buyAnimal(dog2);
        // 卖
        Customer customer2 = new Customer("李四", 0, LocalDate.now());
        shop.serveCustomer(customer2, 0);
        Customer customer3 = new Customer("王五", 0, LocalDate.now());
        shop.serveCustomer(customer3, 0);
        Customer customer4 = new Customer("Scott", 0, LocalDate.now());
        shop.serveCustomer(customer4, 1);
        shop.serveCustomer(customer3, 0);
        // 歇业
        shop.closeShop();
        // 测试关门了这种情况
        Customer customer6 = new Customer("Wand", 0, LocalDate.now());
        shop.serveCustomer(customer6, 0);

    }
}
