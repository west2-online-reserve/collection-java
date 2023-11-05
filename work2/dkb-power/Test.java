import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat("小红", 2, "male", 200);
        Cat cat2 = new Cat("小黑", 1, "female",200);
        Customer jack = new Customer("jack", 0);
        Customer john = new Customer("john", 0);
        ChineseRuralDog dog1 = new ChineseRuralDog("小蓝", 3, "male", 100,false);
        ChineseRuralDog dog2 = new ChineseRuralDog("小绿", 4, "female", 100,true);
        Tiger tiger1 = new Tiger("小橙", 1000, "helicopter", 999);
        Tiger tiger2 = new Tiger("小黄", 10, "male", 99);
        System.out.println("==========day1============");
        MyAnimalShop day1 = new MyAnimalShop(1000, true);
        day1.buyAnimal(cat1);
        day1.Customer(jack);
        day1.buyAnimal(tiger1);
        day1.Customer(john);
        day1.closedoor();
        System.out.println("===========day2=========");
        MyAnimalShop day2 = new MyAnimalShop(10000, true);
        day2.buyAnimal(cat2);
        day2.buyAnimal(dog1);
        day2.buyAnimal(tiger2);
        day2.Customer(john);
        day2.closedoor();
        System.out.println("============day3=========");
        MyAnimalShop day3 = new MyAnimalShop(10000, false);
        day3.buyAnimal(cat2);
        day3.buyAnimal(dog1);
        day3.buyAnimal(tiger2);
        day3.Customer(john);
        day3.closedoor();
    }
}
