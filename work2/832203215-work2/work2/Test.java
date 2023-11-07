package work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Animal> Animals = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        Animal a1 = new Dog("狗狗1",1,"母",1,false);
        Animal a2 = new Cat("猫猫1",2,"公",1);
        Animal a3 = new Rabbit("兔兔1",3,"母",1);
        Animals.add(a1);
        Animals.add(a2);
        Animals.add(a3);
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println(Animals.get(i));
        }
        System.out.println("------------------------------------------------------");
        Customer c1 = new Customer("张三",1, LocalDate.parse("2023-11-01"));
        Customer c2 = new Customer("李四",2, LocalDate.parse("2023-11-01"));
        Customer c3 = new Customer("王五",3, LocalDate.parse("2023-11-03"));
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        System.out.println("------------------------------------------------------");
        System.out.println("请输入店铺剩余金额");
        double money = sc.nextDouble();
        MyAnimalShop shop = new MyAnimalShop(money,Animals,customers);
        Customer c4 = new Customer();
        Animal aa = shop.entertainCustomer(c4);
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        System.out.println("------------------------------------------------------");
        try{
            Animal a4 = new Rabbit("兔兔2",5,"公",1);
            shop.buyAnimal(a4);
        }catch(InsufficientBalanceException e){
            e.printStackTrace();
        }
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println(Animals.get(i));
        }
        shop.setMoney(shop.getMoney() - aa.price);
        System.out.println("购买宠物花费"+aa.price+"元，"+"店铺剩余金额："+shop.getMoney());
        System.out.println("------------------------------------------------------");
        double price = 0;
        for (int i = 0; i < Animals.size(); i++) {
            Animal aa1 = Animals.get(i);
            price = aa1.price;
            if(aa.age == aa1.getAge()&&aa.name.equals(aa1.getName())&&aa.gender.equals(aa1.getGender())){
                Animals.remove(i);
                shop.setMoney(shop.getMoney() + aa1.price);
            }
        }
        for (int i = 0; i < Animals.size(); i++) {
            System.out.println(Animals.get(i));
        }
        System.out.println("出售宠物获得"+price+"元，"+"店铺剩余金额："+shop.getMoney());
        System.out.println("------------------------------------------------------");
        System.out.println("请输入查询利润时间");
        String t = sc.next();
        LocalDate time = LocalDate.parse(t);
        MyAnimalShop shop1 = new MyAnimalShop(0,Animals,customers);
        shop1.close(time,customers,Animals,shop1);
    }
}
