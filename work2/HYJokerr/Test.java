public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop1 = new MyAnimalShop(300);
        Dog dog1 = new Dog("Doggo", 1, "公", true);
        Dog dog2 = new Dog("Doggy", 2, "母", true);
        Cat cat1 = new Cat("mewww", 3, "母");

        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");


        //测试buyAnimal方法
        System.out.println("测试buyAnimal方法:");
        shop1.buyAnimal(cat1);
        shop1.buyAnimal(dog1);
        shop1.buyAnimal(dog2);
        shop1.showAnimal();
        shop1.showMoney();


        //测试entertainCustomer方法
        System.out.println("--------------------------------");
        System.out.println("测试entertainCustomer方法:");
        shop1.entertainCustomer(customer1);
        shop1.showCustomer();
        shop1.showAnimal();
        shop1.showMoney();

        shop1.entertainCustomer(customer1);
        shop1.entertainCustomer(customer2);

        shop1.showCustomer();

        //测试close方法
        System.out.println("--------------------------------");
        System.out.println("测试close方法:");
        shop1.close();


    }
}
