import java.time.LocalDate;

class Test {
    public static void main(String[] args) {
        // 创建宠物店实例，初始余额为 1000.0
        MyAnimalShop myShop = new MyAnimalShop(1000.0);

        // 初始化动物列表
        Animal cat1 = new Cat("Tom", 3, "Male", 200.0);
        Animal dog1 = new ChineseDog("Buddy", 2, "Female", true);
        Animal cat2 = new Cat("Mimi", 1, "Female", 200.0);

        // 测试买入动物
        try {
            myShop.buyAnimal(cat1, 2);
            myShop.buyAnimal(dog1, 1);
            myShop.buyAnimal(cat2, 3);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        // 测试招待顾客
        Customer customer1 = new Customer(0, "Alice", LocalDate.now());
        Customer customer2 = new Customer(0, "Bob", LocalDate.now());
        Customer customer3 = new Customer(0, "Charlie", LocalDate.now());

        try {
            myShop.treatCustomer(customer1, "ChineseDog", 0);
            myShop.treatCustomer(customer2, "Cat", 1);
            myShop.treatCustomer(customer3, "Cat", 0);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // 测试歇业
        myShop.shopClose();
    }
}

