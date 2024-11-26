import java.util.ArrayList;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        //初始化动物、顾客、商店基本信息
        ArrayList<Animal> AnimalList = new ArrayList<>();
        Bird bird1 = new Bird("黄鹂", 1, "公", 50, true);
        Cat cat1 = new Cat("金渐层", 1, "母", 200, true);
        ChineseRuralDog dog1 = new ChineseRuralDog("土狗", 2, "公", 100, true);
        Rabbit rabbit1 = new Rabbit("折耳兔", 1, "母", 30, true);
        AnimalList.add(bird1);
        AnimalList.add(cat1);
        AnimalList.add(dog1);
        AnimalList.add(rabbit1);
        ArrayList<Customer> CustomerList = new ArrayList<>();
        Customer customer1 = new Customer("小陈", 3, LocalDate.now());
        Customer customer2 = new Customer("小李", 5, LocalDate.now());
        Customer customer3 = new Customer("小张", 2, LocalDate.now());
        Customer customer4 = new Customer("小王", 1, LocalDate.now());
        MyAnimalShop AnimalShop = new MyAnimalShop(10000.0, AnimalList, CustomerList, true, 0.0);

        //测试购买动物功能
        System.out.println("———————————————————————————");
        System.out.println("请为您的宠物商店购入一批宠物！");
        //输出采购宠物前宠物店的原有宠物
        System.out.println("当前宠物店有" + AnimalList.size() + "只宠物！");
        for (int i = 0; i < AnimalList.size(); i++) {
            System.out.println("第" + (i + 1) + "只小动物的信息为：");
            System.out.println(AnimalList.get(i).toString());
        }
        System.out.println("当前宠物店余额为" + AnimalShop.balance + "元！");
        System.out.println("当前有这些宠物可供购买：");
        Bird bird2 = new Bird("老鹰", 1, "公", 50, true);
        Cat cat2 = new Cat("银渐层", 1, "公", 200, true);
        ChineseRuralDog dog2 = new ChineseRuralDog("哈士奇", 2, "公", 100, true);
        Rabbit rabbit2 = new Rabbit("北极兔", 2, "公", 30, true);
        System.out.println("bird2——老鹰、1岁、公、50元");
        System.out.println("cat2——银渐层、1岁、公、200元");
        System.out.println("dog2——哈士奇、2岁、公、100元");
        System.out.println("rabbit2——北极兔、2岁、公、30元");
        AnimalShop.buyAnimal(bird2);
        AnimalShop.buyAnimal(cat2);
        AnimalShop.buyAnimal(dog2);
        AnimalShop.buyAnimal(rabbit2);
        //输出采购宠物后宠物店现有的宠物
        System.out.println("当前宠物店有" + AnimalList.size() + "只宠物！");
        for (int i = 0; i < AnimalList.size(); i++) {
            System.out.println("第" + (i + 1) + "只小动物的信息为：");
            System.out.println(AnimalList.get(i).toString());
        }
        System.out.println("当前宠物店余额为" + AnimalShop.balance + "元！");

        //测试服务顾客功能
        System.out.println("———————————————————————————");
        System.out.println("您的商店正在招待顾客！");
        AnimalShop.serveCustomer(customer1, cat1);
        AnimalShop.serveCustomer(customer2, bird2);
        AnimalShop.serveCustomer(customer3, bird1);
        AnimalShop.serveCustomer(customer4, rabbit2);

        //测试商店歇业功能
        System.out.println("———————————————————————————");
        System.out.println("您的商店正准备歇业！");
        AnimalShop.closeShop();
        System.out.println("———————————————————————————");


    }
}
