package chongwudian;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
public class Test {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        //创建宠物和顾客菜单，并实例化宠物店
        ArrayList<Animal> animals=new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();

        Animal a1=new Cat("猫1",2,"man",100.0);
        Animal a2=new Cat("猫2",1,"woman",200.0);
        Animal a3=new Dog("狗1",3,"man",250.0,true);
        Animal a4=new Cat("猫3",1,"man",50.0);
        animals.add(a1);
        animals.add(a2);
        animals.add(a3);
        animals.add(a4);

        Customer c1=new Customer("顾客1",0,LocalDate.parse("2024-10-19"));
        Customer c2=new Customer("顾客2",0,LocalDate.parse("2024-10-20"));
        Customer c3=new Customer("顾客3",0,LocalDate.parse("2024-10-20"));
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        System.out.println("请输入店铺初始金额：");
        double money=sc.nextDouble();
        MyAnimalShop shop=new MyAnimalShop(money,animals,customers);

        //尝试购买宠物
        try{
            Animal a5=new Dog("狗2",1,"man", 10.0,false);
            shop.buyAnimal(a5);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        //尝试顾客消费
        try{
            shop.comeCustomer(c1);
        }catch (AnimalNotFountException e){
            System.out.println(e.getMessage());
        }

        //歇业
        shop.closed(LocalDate.parse("2024-10-19"));

        /* TODO
        *   增加时间和当天利润的关系，从而可以查看每天的利润*/
    }
}
