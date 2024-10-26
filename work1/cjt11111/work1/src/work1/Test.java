package work1;

import java.time.LocalTime;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        MyAnimalShop shop=new MyAnimalShop();
        Animal dog=new ChineseVillageDogs("中华田园犬",1 ,"male",true);
        Animal cat=new Cat("银渐层",1,"male",200.0);
        Animal hamster=new Hamster("仓鼠",1,"female");
        shop.t= LocalTime.now();
        LocalTime tc=LocalTime.now();
        Customer c1=new Customer("customerNo1",1,tc);
        shop.animalList.add(dog);
        shop.animalList.add(cat);
        shop.customerArrayList.add(c1);
        shop.setBalance(10000.0);

          boolean a=true;



              //判断是否营业
              shop.Close(a);
            if (shop.isOpen()) {



                  //购买宠物
                  System.out.println("购买仓鼠：");
                  shop.buyAnimal(hamster);
                  System.out.println("招待顾客：");
                  //招待顾客
                  System.out.println("请输入您的姓名：");
                  String name1 = scanner.next();
                  LocalTime time1 = LocalTime.now();
                  Customer customer1 = new Customer(name1);
                  shop.EntertainingCustomer(customer1);


              }else{
                  a=false;
              }
             //歇业信息
                shop.Close(false);

    }
}
