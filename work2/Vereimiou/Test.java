import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        LinkedList<Customer> customers = new LinkedList<>();
        MyAnimalShop myAnimalShop = new MyAnimalShop(300.0, false);
        //开店
        myAnimalShop.open();
        //初始化动物列
        Animal puppy1 = new ChineseRuralDog("修狗", 2, "雌性", true);
        animals.add(puppy1);
        Animal cat1 = new MewMewCat("咪咪", 2, "雄性");
        animals.add(cat1);
        Animal puppy2 = new ChineseRuralDog("旺旺", 3, "雄性", true);
        animals.add(puppy1);
        //购买动物
        for (Animal animal : animals) {
            try {
                myAnimalShop.buy(animal);
            } catch (InsufficientBalanceException e) {
                System.out.println(e.toString());
            }
        }
        //初始顾客列
        LocalDate todayDate = LocalDate.now();
        Customer customer1 = new Customer("张三", 2, todayDate.minusWeeks(2));
        Customer customer2 = new Customer("张五", 0, todayDate);
        Customer customer3 = new Customer("李四", 1, todayDate.minusDays(4));
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        //顾客购买
        for (Customer customer : customers) {
            try {
                myAnimalShop.serve(customer);
            } catch (AnimalNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        //宠物店关门
        myAnimalShop.close();
        //关门时购买
        myAnimalShop.serve(customer1);
        myAnimalShop.open();
        for (Animal animal : animals) {
            try {
                myAnimalShop.buy(animal);
            } catch (InsufficientBalanceException e) {
                System.out.println(e.toString());
            }
        }
        for (Customer customer : customers) {
            try {
                myAnimalShop.serve(customer);
            } catch (AnimalNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        for (Customer customer : customers) {
            try {
                myAnimalShop.serve(customer);
            } catch (AnimalNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        myAnimalShop.close();
    }
}
