import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(200,false);
        shop.setProfit(0.0);

        Animal cat1 = new Cat("Jerry",3,"女");
        Animal cat2 = new Cat("Sara",3,"女");
        Animal cat3 = new Cat("Lili",3,"女");
        Animal dog1 = new ChineseRuralDog("field",2,"男",true);

        shop.getAnimalArrayList().add(cat2);
        shop.getAnimalArrayList().add(cat3);

        try {
            shop.buy(cat1);//进货成功。
            shop.buy(dog1);//InsufficientBalanceException: 余额不足！
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(true);

        Customer customer1 = new Customer("Jack",0,LocalDate.now());

        try {
            shop.reception(customer1);//店铺已经歇业。
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(false);
        Customer customer2 = new Customer("Rare",1,LocalDate.now());
        shop.getCustomerArrayList().add(customer2 );
        try {
            shop.reception(customer2);//欢迎再次光临！
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }


        try {
            shop.sell(dog1);//AnimalNotFountException: 店里没有该动物
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat2);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }

        shop.close();

        for (int i = 0; i < shop.getCustomerArrayList().size(); i++) {
            System.out.println(shop.getCustomerArrayList().get(i));
        }
        for (int i = 0; i < shop.getAnimalArrayList().size(); i++) {
            System.out.println(shop.getAnimalArrayList().get(i));
        }

        shop.reopen();
    }
}
