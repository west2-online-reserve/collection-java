public class Test {
    public static void main(String[] args) {
        // 初始化一些客户
        Customer customer1 = new Customer("Alice", 0);
        Customer customer2 = new Customer("Bob", 5);

        // 初始化一些动物
        Animal dog = new ChineseFieldDog("Buddy", 3, "Male", 100, true);
        Animal cat = new Cat("Whiskers", 2, "Male",200);
        Animal lion = new Lion("Simba", 5, "Male", 6666.6); // 创建一只狮子

        // 创建宠物店实例1
        MyAnimalShop petShop1 = new MyAnimalShop(1000, false);
        //购买猫咪，余额充足
        petShop1.purchaseAnimal(cat);
        //购买小狗，余额充足
        petShop1.purchaseAnimal(dog);
        // 测试购买狮子，余额不足,抛出异常
        petShop1.purchaseAnimal(lion);
        // 测试招待客户，一只狗，被买走
        petShop1.treatCustomer(customer1);
        //测试招待客户，一只猫，被买走
        petShop1.treatCustomer(customer1);
        // 测试招待客户，此时无宠物，抛出异常
        petShop1.treatCustomer(customer2);
        // 关门歇业
        petShop1.setClosed(true);
        // 再次测试招待客户，店已经关门
        petShop1.treatCustomer(customer1);
        // 打印一天的顾客信息和利润
        petShop1.rest();
    }
}
