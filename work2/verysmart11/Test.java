package work2;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        Animal animal1 = new ChinesePastoralDog("WangCai", 2, 'M', true);
        Animal animal2 = new ChinesePastoralDog("LaiFu", 4, 'F', true);
        Animal animal3 = new Rabbit("BaiTu", 1, 'F');
        Animal animal4 = new Cat("XiaoBai", 3, 'F');
        Animal animal5 = new Cat("XiaoHei", 1, 'M');
        Animal animal6 = new Rabbit("HeiTu", 2, 'F');

        ArrayList<Animal> animalArrayList = new ArrayList<Animal>();
       // animalArrayList.add(animal1);
        animalArrayList.add(animal3);
        animalArrayList.add(animal4);

        ArrayList<Customer> customerArrayList = new ArrayList<Customer>();

        MyAnimalShop store = new MyAnimalShop(150, animalArrayList, customerArrayList);

        Customer c1 = new Customer("XiaoMei", 2, LocalDate.of(2023, 1, 20));
        Customer c2 = new Customer("XiaoMing", 6, LocalDate.of(2023, 4, 2));
        Customer c3 = new Customer("XiaoQiang", 0, LocalDate.of(2023, 5, 7));
        Customer c4 = new Customer("DaZhuang", 3, LocalDate.of(2023, 10, 20));
        Customer c5 = new Customer("MieMie", 1, LocalDate.of(2023, 9, 3));

        store.close(LocalTime.of(7, 0));
        store.close(LocalTime.of(17, 0));

        try {
            store.buyAnimal(animal2);
        } catch (InsufficientBalanceException e) {
            System.out.println("balance is not enough");
        }

        try {
            store.treatCustomer(c1, sc);
            store.treatCustomer(c2, sc);
            store.treatCustomer(c3, sc);
            store.treatCustomer(c4, sc);
        } catch (AnimalNotFountException e) {
            System.out.println("there is no animal in the store");
        }

        try {
            store.buyAnimal(animal5);
        } catch (InsufficientBalanceException e) {
            System.out.println("balance is not enough");
        }


        store.close(LocalTime.of(23, 23));
        try {
            store.buyAnimal(animal6);
        } catch (InsufficientBalanceException e) {
            System.out.println("balance is not enough");
        }

        try {
            store.treatCustomer(c5, sc);
        } catch (AnimalNotFountException e) {
            System.out.println("there is no animal in the store");
        }

        sc.close();
    }
}
