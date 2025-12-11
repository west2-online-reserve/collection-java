import com.Anna_west2_work01.animalShop.Animal;
import com.Anna_west2_work01.animalShop.MyAnimalShop;
import com.Anna_west2_work01.animalShop.animals.Birds;
import com.Anna_west2_work01.animalShop.animals.CRDogs;
import com.Anna_west2_work01.animalShop.animals.Cats;
import com.Anna_west2_work01.animalShop.customer.Customer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //初始化动物、顾客、商店基本信息
        System.out.println("[Hint:正在尝试初始化您的宠物店……]");
        ArrayList<Animal> AnimalList = new ArrayList<>();
        Birds bird1 = new Birds("黄鹂", 1, "公", 50, 100);
        Cats cat1 = new Cats("金渐层", 1, "母", 200, 400);
        CRDogs dog1 = new CRDogs("土狗", 2, "公", 100, 180, true);
        AnimalList.add(bird1);
        AnimalList.add(cat1);
        AnimalList.add(dog1);
        ArrayList<Customer> CustomerList = new ArrayList<>();
        Customer customer1 = new Customer("小陈", 3, LocalDate.now());
        Customer customer2 = new Customer("小李", 5, LocalDate.now());
        Customer customer3 = new Customer("小张", 2, LocalDate.now());
        Customer customer4 = new Customer("小王", 1, LocalDate.now());
        MyAnimalShop AnimalShop = new MyAnimalShop(10000.0, AnimalList, CustomerList, true, 0.0);

        System.out.println("\n" + "[Hint:宠物店信息初始化成功！]");
        showAnimalShopInformation(AnimalList, AnimalShop);

        //测试购买动物功能
        System.out.println("\n" + "[Hint:请为您的宠物商店购入一批宠物！]");
        System.out.println("当前宠物店余额为" + AnimalShop.getBalance() + "元！");
        System.out.println("当前有这些宠物可供购买：");
        Birds bird2 = new Birds("老鹰", 1, "公", 50, 100);
        Cats cat2 = new Cats("银渐层", 1, "公", 200, 400);
        CRDogs dog2 = new CRDogs("哈士奇", 2, "公", 100, 180,true);
        System.out.println("bird2——老鹰、1岁、公、50元");
        System.out.println("cat2——银渐层、1岁、公、200元");
        System.out.println("dog2——哈士奇、2岁、公、100元");
        System.out.println("\n" + "[Hint:尝试购买三只动物中……]" + "\n");
        AnimalShop.buyAnimal(bird2);
        AnimalShop.buyAnimal(cat2);
        AnimalShop.buyAnimal(dog2);
        //输出采购宠物后宠物店现有的宠物
        showAnimalShopInformation(AnimalList, AnimalShop);

        //测试服务顾客功能
        System.out.println("\n" + "[Hint:您的商店正在招待顾客！]");
        System.out.println("\n" + "[Hint:尝试售出三只动物中……]" + "\n");
        AnimalShop.serveCustomer(customer1, cat1);
        AnimalShop.serveCustomer(customer2, bird2);
        AnimalShop.serveCustomer(customer3, bird1);

        //测试商店歇业功能
        System.out.println("\n" + "[Hint:您的商店正尝试停止今日份营业中……]");
        AnimalShop.close();
    }

    private static void showAnimalShopInformation(ArrayList<Animal> animalList, MyAnimalShop animalShop) {
        System.out.println("- - - - - - - - - - - - - - - ");
        System.out.println("当前宠物店有" + animalList.size() + "只宠物！");
        for (int i = 0; i < animalList.size(); i++) {
            System.out.println("第" + (i + 1) + "只小动物的信息为：");
            System.out.println(animalList.get(i).toString());
        }
        System.out.println("当前宠物店余额为" + animalShop.getBalance() + "元！");
        System.out.println("- - - - - - - - - - - - - - - ");
    }
}
