import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(100000,false,80);

        Cat cat1 = new Cat("Jerry",3,"女",100);
        Cat cat2 = new Cat("Sara",3,"女",100);
        Cat cat3 = new Cat("Lili",3,"女",100);
        ChineseRuralDog dog1 = new ChineseRuralDog("field",2,"男",100,true);

        shop.animalArrayList.add(cat2);
        shop.animalArrayList.add(cat3);


        //余额不足
        shop.buy(cat1,900);
        //购买成功,购买了1只Cat{name:猫猫,age:3,gender:女,price:200.0}
        shop.buy(cat1,1);
        //购买成功,购买了1只ChineseRuralDog{name:field,age:2,gender:男,price:100.0,isVaccineInjected:true}
        shop.buy(dog1,1);


        ArrayList<Customer> customerArrayList = new ArrayList<>();
        Customer customer1 = new Customer("Jack",0,LocalDate.now());
        customerArrayList.add(customer1);
        shop.reception(customer1);


        //已移除出列表的宠物：ChineseRuralDog{name:中华田园犬,age:2,gender:男,price:100.0,isVaccineInjected:true}
        //卖出了ChineseRuralDog{name:中华田园犬,age:2,gender:男,price:100.0,isVaccineInjected:true}
        shop.sell(dog1,1);
        shop.sell(cat1,1);
        shop.sell(cat2,1);
        //已过零点，我们歇业啦！
        //今日利润为：310.0
        shop.isClosed();
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println(customerArrayList.get(i));
        }
        for (int i = 0; i < shop.animalArrayList.size(); i++) {
            System.out.println(shop.animalArrayList.get(i));
        }
    }
}
