package CongWork2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
//102301416姚敏聪
public class TestWork {
    public static void main(String[] args) {
        Animals dog = new ChineseDog("中华田园犬", 1, "雄性", 500, true);
        dog.setCost(300);//进价
        Animals cat1 = new Cat("加菲猫", 1, "雄性", 1200);
        cat1.setCost(800);//进价
        Animals cat2 = new Cat("波比猫", 2, "雌性", 500);
        cat2.setCost(400);
        Animals fox = new Fox("北极狐", 1, "雄性", 2000);
        fox.setCost(1600);
        MyAnimalShop shop = new MyAnimalShop();
        boolean a = true;
        int b = 0;
        shop.listAnimals.add(dog);
        shop.listAnimals.add(cat1);
        shop.listAnimals.add(cat2);
        shop.arrivalTime = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        while(a) {
            shop.close(a);//判断是否关店
            if (shop.isOpen) {
                System.out.println("您的姓名");
                String name1 = scanner.next();
                LocalDate time1 = LocalDate.now();
                Customer2 customer1=new Customer2(name1);
                shop.careCustomer(customer1);//添加顾客并进行购买宠物
                System.out.println("是否购进宠物？输入“1”为是");
                b=scanner.nextInt();
                if (b==1){
                    System.out.println("请选择购进的宠物：1.中华田园犬 2.加菲猫 3.波比猫 4.北极狐");
                    int c= scanner.nextInt();
                    switch (c) {
                        case 1:
                            shop.buyAnimals(dog);
                            break;
                       case 2:
                            shop.buyAnimals(cat1);
                            break;
                       case 3:
                            shop.buyAnimals(cat2);
                            break;
                       case 4:
                            shop.buyAnimals(fox);
                            break;
                       default:
                            throw new AnimalNotFountException("没有该种宠物，请重新选择");
                        }
                    }
                //添加顾客
                System.out.println("是否继续营业？输入‘1’为是，否则为否");
                int e= scanner.nextInt();
                if(e==1){
                    a=true;
                } else {
                    a=false;
                }
            }else {//不处于营业时间，结束循环
                a=false;
            }
        }
        if (shop.isOpen){
            shop.close(a);
        }
    }
}
