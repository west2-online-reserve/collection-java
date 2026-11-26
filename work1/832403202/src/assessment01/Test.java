package assessment01;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(500);  // 初始化宠物店，余额为500元

        // 创建几只动物
        Animal dog = new ChineseVillageDog("旺旺", 3, "公", true);
        Animal cat = new Cat("喵喵", 2, "母");

        // 买入动物
        try {
            shop.buyAnimal(dog);
            shop.buyAnimal(cat);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        // 招待顾客
        Customer customer1 = new Customer("张三");
        shop.attendCustomer(customer1);

        // 歇业
        shop.closeShop();
    }
}
