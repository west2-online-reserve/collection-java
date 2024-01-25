import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(200,false);

        Animal cat1 = new Cat("Jerry",3,"女");
        Animal cat2 = new Cat("Sara",3,"女");
        Animal cat3 = new Cat("Lili",3,"女");
        Animal dog1 = new ChineseRuralDog("field",2,"男",true);

        shop.getAnimalList().add(cat2);
        shop.getAnimalList().add(cat3);

        try {
            shop.buyAnimal(cat1);//进货成功。
            shop.buyAnimal(dog1);//InsufficientBalanceException: 余额不足！
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }

        shop.setOpen(true);

        Customer customer1 = new Customer("Jack",0,LocalDate.now());

        try {
            shop.serveCustomer(customer1);//店铺已经歇业。
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }

        shop.setOpen(false);
        Customer customer2 = new Customer("Rare",1,LocalDate.now());
        shop.getCustomerList().add(customer2 );
        try {
            shop.serveCustomer(customer2);//欢迎再次光临！
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }


        try {
            shop.sell(dog1);//AnimalNotFountException: 店里没有该动物
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat1);
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat2);
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }

        shop.close();

        for (int i = 0; i < shop.getCustomerList().size(); i++) {
            System.out.println(shop.getCustomerList().get(i));
        }
        for (int i = 0; i < shop.getAnimalList().size(); i++) {
            System.out.println(shop.getAnimalList().get(i));
        }

    }
}