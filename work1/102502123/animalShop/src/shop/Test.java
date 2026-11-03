package shop;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(2000.0);

        try {
            shop.open();
            System.out.println("测试买入动物");

            Animal dog = new Dog("狗", 2, "公",true);
            Animal cat = new Cat("猫", 1, "母");

            shop.buyNewAnimal(dog, 2);
            shop.buyNewAnimal(cat, 1);
            //再买一次，触发余额不足
            System.out.println("\n余额不足情况");
            shop.buyNewAnimal(dog, 5);

        } catch (InsufficientBalanceException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("\n测试招待顾客");
            Customer c1 = new Customer("张三");
            Customer c2 = new Customer("李四");
            Customer c3 = new Customer("王五");

            shop.serveCustomer(c1);
            shop.serveCustomer(c2);
            shop.serveCustomer(c3);
            //触发没有动物的异常
            System.out.println("\n无动物情况");
            Customer c4 = new Customer("赵六");
            shop.serveCustomer(c4);

        } catch (AnimalNotFoundException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("测试歇业");
        shop.closeShop();

        System.out.println("测试结束");
    }
}