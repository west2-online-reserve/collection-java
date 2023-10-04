package szw.test2;

public class Test {
    public static void main(String[] args) {
        // 创建宠物店实例
        MyAnimalShop animalShop = new MyAnimalShop();

        // 初始化宠物店的余额
        animalShop.setBalance(10000.0);

        // 初始化动物列表
        Animal dog1 = new Dog("小黑", 2, "雄性",false);
        Animal dog2 = new Dog("旺财", 3, "雄性",true);
        Animal dog3 = new Dog("小白", 2, "雌性",true);
        Animal cat1 = new Cat("小花", 1, "雌性");
        Animal cat2 = new Cat("小迪", 2, "雄性");
        animalShop.getAnimalList().add(dog1);
        animalShop.getAnimalList().add(dog2);
        animalShop.getAnimalList().add(dog3);
        animalShop.getAnimalList().add(cat1);
        animalShop.getAnimalList().add(cat2);
        // 测试购买动物
        try {
            animalShop.buyAnimal(cat1);
        } catch (InsufficientBalanceException e) {
            System.out.println("购买动物失败：" + e.getMessage());
        }

        // 测试招待顾客
        Customer customer1 = new Customer("张三");
        animalShop.serveCustomer(customer1);

        Customer customer2 = new Customer("李四");
        animalShop.serveCustomer(customer2);

        // 测试歇业
        animalShop.close();
    }
}
