package petStore;

import java.time.LocalDate;

public class TestMyAnimalShop {
    public static void main(String[] args) {
        MyAnimalShop mas = new MyAnimalShop();
        Cat c1 = new Cat("huahua", 5, "Female");
        Cat c2 = new Cat("Abao", 7, "Male");
        Cat c3 = new Cat("XiaoQi", 4, "Female");
        Rabbit r1 = new Rabbit("xiaobai", 5, "Male");
        Rabbit r2 = new Rabbit("wusqqi", 3, "male");
        ZhonghuatianyuanDog d1 = new ZhonghuatianyuanDog("tiantian", 5, "Male", true);
        ZhonghuatianyuanDog d2 = new ZhonghuatianyuanDog("xiaohua", 3, "Female", false);
        mas.boughtAnimal(mas, c1);
        mas.boughtAnimal(mas, c2);
        mas.boughtAnimal(mas, c3);
        mas.boughtAnimal(mas, r1);
        mas.boughtAnimal(mas, r2);
        mas.boughtAnimal(mas, d1);
        mas.boughtAnimal(mas, d2);
        // System.out.println(c1.getPrice()+"  "+r1.getPrice());

        Customer ct0 = new Customer("ZhangSan", 2, "2024-10-19", c1);
        Customer ct1 = new Customer("Wangwu", 1, "2024-10-20", r1);
        Customer ct2 = new Customer("Lisi", 1, "2024-10-20", c2);
        Customer ct3 = new Customer("zhangLiu", 5, "2024-10-20", d2);
        //System.out.println(mas.getAnimalList().toString());
        mas.service(ct0);
        mas.service(ct1);
        mas.service(ct2);
        mas.service(ct3);
        ct3.setWantBuy(d1);
        mas.service(ct3);
        mas.checkCash(mas, LocalDate.of(2024, 10, 20));
        System.out.println(mas.getAnimalList());
        System.out.println("========================");
        System.out.println(mas.getCustomerList());
        System.out.println(ct2.toString());
        //System.out.println(ct3.getPay());
    }
}
