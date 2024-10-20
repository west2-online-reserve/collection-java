package animalShop;

import animal.Animal;
import animal.CRDog;
import animal.Cat;
import customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        //设置店里的余额
        myAnimalShop.setMoney(500);
        myAnimalShop.setCustomerList(new ArrayList<>());
        List<Animal> animalList = new ArrayList<>();
        //加入宠物
        animalList.add(new CRDog("阿黄", 1, "公", true));
        animalList.add(new CRDog("小白", 3, "公", true));
        animalList.add(new CRDog("Husky", 2, "母", false));
        animalList.add(new CRDog("蛋糕", 6, "母", true));
        animalList.add(new CRDog("一绝", 2, "公", false));
        animalList.add(new Cat("饼干", 3, "公"));
        animalList.add(new Cat("布偶", 1, "公"));
        animalList.add(new Cat("卡卡", 5, "公"));
        animalList.add(new Cat("安达", 1, "公"));
        animalList.add(new Cat("二二", 2, "公"));
        myAnimalShop.setAnimalList(animalList);


        //买入一只宠物
        myAnimalShop.buyNewAnimal(new CRDog("66", 2, "母", false));
        System.out.println("店内的余额：" + myAnimalShop.getMoney());


        //开业
        myAnimalShop.open();

        //卖出一只宠物
        Customer customer = new Customer("张三");
        String welcome = myAnimalShop.welcome(customer);
        System.out.println(welcome);
        String sale = myAnimalShop.sale(customer, "卡卡");
        System.out.println(sale);


        Customer customer1 = new Customer("张三");
        String welcome1 = myAnimalShop.welcome(customer1);
        System.out.println(welcome1);
        String sale1 = myAnimalShop.sale(customer1, "蛋糕");
        System.out.println(sale1);


        Customer customer2 = new Customer("李四");
        String welcome2 = myAnimalShop.welcome(customer2);
        System.out.println(welcome2);
        String sale2 = myAnimalShop.sale(customer2, "66");
        System.out.println(sale2);


        //抛出异常测试
//        Customer customer3 = new Customer("王五");
//        String welcome3 = myAnimalShop.welcome(customer3);
//        System.out.println(welcome3);
//        String sale3 = myAnimalShop.sale(customer3 , "77");
//        System.out.println(sale3);


        //输出剩余动物
        System.out.println("店里剩余的动物:");
        List<Animal> animalList1 = myAnimalShop.getAnimalList();
        for (Animal animal : animalList1) {
            System.out.println(animal);
        }
        System.out.println(myAnimalShop.getMoney());

        //输出今天的客户信息
        System.out.println(customer);


        //歇业
        myAnimalShop.stop();


    }
}
