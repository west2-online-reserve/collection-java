/**
 * Test类用于测试所写代码的功能是否正确
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(500);
        Dog dog1 = new Dog("狗子1", 5, 1, true);
        Cat cat1 = new Cat("猫猫1", 3, 2);
        Rabbit rabbit1 = new Rabbit("兔兔1", 3, 2);
        Dog dog2 = new Dog("狗子2", 5, 1, true);
        Cat cat2 = new Cat("猫猫2", 3, 2);
        Rabbit rabbit2 = new Rabbit("兔兔2", 3, 2);
        Customer c1 = new Customer("小黑子");
        Customer c2 = new Customer("小黑");
        Customer c3 = new Customer("黑子");
        // 测试AnimalNotFoundException是否能被正常抛出及捕获
        try {
            shop.openShop();
            shop.receiptCustomer(c1);
        } catch (AnimalNotFountException e) {
            e.printError();
        }
        shop.closeShop();
        try {
            shop.buyNewAnimal(dog1);
            shop.buyNewAnimal(cat1);
            shop.buyNewAnimal(rabbit1);
        } catch (InsufficientBalanceException e) {
            e.printError();
        }
        try {
            shop.cheat(10000);
            shop.openShop();
            shop.buyNewAnimal(dog2);
            shop.buyNewAnimal(cat2);
            shop.buyNewAnimal(rabbit2);
            shop.receiptCustomer(c1);
            shop.receiptCustomer(c1);
            shop.receiptCustomer(c1);
            shop.receiptCustomer(c2);
            shop.receiptCustomer(c3);
            shop.receiptCustomer(c2);
        } catch (AnimalNotFountException e) {
            e.printError();
        }
        shop.closeShop();
        // 查看顾客信息是否被成功更新
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c3.toString());
    }
}
