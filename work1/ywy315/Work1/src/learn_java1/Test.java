package learn_java1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {
            ChineseLuralDog sample1 = new ChineseLuralDog("小六", 4, '公', true, 100, 10);
            System.out.println(sample1.toString());
            KittyCat sample2 = new KittyCat("阿项", 5, '母', 200, 14);
            System.out.println(sample2.toString());
            Dinosaur sample3 = new Dinosaur("佬机", 30, '公', 500, 80);
            System.out.println(sample3.toString());

            LocalDate date1 = LocalDate.of(2020, 1, 1);
            Customer person1 = new Customer("张三", 0, date1);
            Customer person2 = new Customer("李四", 0, date1);
            Customer person3 = new Customer("王五", 0, date1);

            LocalDate date2 = LocalDate.of(2020, 1, 2);
            Customer person21 = new Customer("叶六", 0, date2);
            Customer person22 = new Customer("钟七", 0, date2);
            Customer person23 = new Customer("张三", 1, date2);

            ArrayList<Animal> sample4 = new ArrayList<>();
            sample4.add(sample1);
            sample4.add(sample2);
            sample4.add(sample3);

            ArrayList<Customer> sample5 = new ArrayList<>();
            MyAnimalShop myAnimalShop = new MyAnimalShop(true, 50, sample4, sample5);
            myAnimalShop.entertainCustomer(person1);
            myAnimalShop.open();
            myAnimalShop.entertainCustomer(person1);
            myAnimalShop.entertainCustomer(person2);

            ChineseLuralDog sample6 = new ChineseLuralDog("小十", 2, '母', true, 100, 15);
            KittyCat sample7 = new KittyCat("阿咯", 7, '公', 200, 14);
            myAnimalShop.purchaseAnimal(sample6);
            myAnimalShop.purchaseAnimal(sample7);
            myAnimalShop.entertainCustomer(person3);

            myAnimalShop.CloseShop(date1);

            myAnimalShop.entertainCustomer(person21);
            myAnimalShop.open();
            myAnimalShop.entertainCustomer(person21);
            myAnimalShop.entertainCustomer(person22);
            myAnimalShop.entertainCustomer(person23);


        } catch (AnimalNotFountException e) {
            e.get_Message();
        } catch (InsufficientBalanceException e) {
            e.get_Message();
        }

    }
}
