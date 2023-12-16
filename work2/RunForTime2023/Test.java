import java.time.LocalDate;

public class Test {
    public static void main(String[] argv) {
        //以下假定宠物的进货价与售价相同

        MyAnimalShop shop = new MyAnimalShop(500, LocalDate.of(2022, 11, 27));
        Dog[] doggy = {new Dog("Jack", 4, 'M'), new Dog("Peter", 5, 'F')};
        Cat[] catt = {new Cat("Kitty", 3, 'F'), new Cat("Tom", 4, 'M'), new Cat("Rick", 3, 'M')};
        Pig piggy = new Pig("peppa pig", 2, 'M');

        shop.buyAnimals(doggy[1]);
        shop.buyAnimals(catt[0]);
        shop.buyAnimals(piggy);
        doggy[1].setVaccineInjected();
//        shop.buyAnimals(catt[1]); //购买超支
        Customer c1 = new Customer("张三", LocalDate.of(2023, 5, 31));
//        shop.receiveCustomers(c1,catt[2]); //购买未进货的宠物
        shop.receiveCustomers(c1, doggy[1]);
//        shop.receiveCustomers(c1,doggy[1]); //重复购买
        shop.closeShop();

        LocalDate Day1 = LocalDate.of(2023, 8, 17);
        shop.openShop(Day1);
        Customer c2 = new Customer("王五", Day1);
        c1.updateArrival(Day1);
        shop.receiveCustomers(c1, piggy);
        shop.receiveCustomers(c2, catt[0]);
        shop.closeShop();

        LocalDate Day2 = LocalDate.of(2023, 9, 6);
        shop.openShop(Day2);
        shop.buyAnimals(catt[2]);
        c2.updateArrival(Day2);
        shop.receiveCustomers(c2, catt[2]);
        Cockroach cockroach1 = new Cockroach("小强", 3, 'M');
        shop.buyAnimals(cockroach1);
        shop.closeShop();

        LocalDate Day3 = LocalDate.of(2023, 9, 30);
        shop.openShop(Day3);
        Customer c3 = new Customer("老八", Day3);
        shop.receiveCustomers(c3, cockroach1);
        shop.closeShop();
    }
}