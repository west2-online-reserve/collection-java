public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop1 = new MyAnimalShop(500);
        Dog dog1 = new Dog("黑豹", 1, "公", true);
        Dog dog2 = new Dog("母老虎", 2, "母", true);
        Cat cat1 = new Cat("coke小猫", 3, "母");

        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");


        //测试buyAnimal方法
        System.out.println("测试buyAnimal方法:");
        shop1.buyNewAnimal(cat1);
        shop1.buyNewAnimal(dog1);
        shop1.buyNewAnimal(dog2);
        System.out.println("--------------------------------");
        shop1.showAnimal();
        shop1.showbalance();


        //测试entertainCustomer方法
        System.out.println("--------------------------------");
        System.out.println("测试entertainCustomer方法:");
        shop1.entertainCustomer(customer1);
        shop1.showCustomer();
        System.out.println("--------------------------------");
        shop1.showAnimal();
        shop1.showProfit();

        shop1.entertainCustomer(customer1);
        shop1.entertainCustomer(customer2);

        shop1.showCustomer();

        //测试close方法
        System.out.println("--------------------------------");
        System.out.println("测试close方法:");
        shop1.closeShop();


        MyAnimalShop shop2 = new MyAnimalShop(200);
        Dog newdog1 = new Dog("大狗", 1, "公", true);
        Dog newdog2 = new Dog("二狗", 2, "母", true);
        Cat newcat1 = new Cat("大猫", 3, "母");

        Customer newcustomer1 = new Customer("帅哥");
        Customer newcustomer2 = new Customer("美女");


        //测试buyAnimal方法
        System.out.println("测试buyAnimal方法:");
        shop2.buyNewAnimal(newcat1);
        shop2.buyNewAnimal(newdog1);
        shop2.buyNewAnimal(newdog2);
        System.out.println("--------------------------------");
        shop2.showAnimal();
        shop2.showbalance();


        //测试entertainCustomer方法
        System.out.println("--------------------------------");
        System.out.println("测试entertainCustomer方法:");
        shop2.entertainCustomer(newcustomer1);
        shop2.showCustomer();
        System.out.println("--------------------------------");
        shop2.showAnimal();
        shop2.showProfit();

        shop2.entertainCustomer(newcustomer1);
        shop2.entertainCustomer(newcustomer2);

        shop2.showCustomer();

        //测试close方法
        System.out.println("--------------------------------");
        System.out.println("测试close方法:");
        shop2.closeShop();

    }
}
