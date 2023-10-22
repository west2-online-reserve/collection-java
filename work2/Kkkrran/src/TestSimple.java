import java.time.LocalDate;

/**
 * Date 2023/10/21  21:16
 *
 * @author Kkkrran
 * @version 1.0
 */
public class TestSimple {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop();
        Customer customer1 = new Customer("YC", 9
                , LocalDate.of(2020, 6, 29));
        Customer customer2 = new Customer("CYX", 1
                , LocalDate.of(2023, 7, 1));
        Customer customer3 = new Customer("WT", 0, null);
        Animal dog1 = new Dog("Wang", 3, "male", 200, true);
        Animal dog2 = new Dog("Teddy", 1, "male", 1000, true);
        Animal cat1 = new Cat("Siamese", 1, "female", 3000);
        Animal cat2 = new Cat("Muppet", 2, "female", 8000);
        Animal mouse1 = new Mouse("Bally", 1, "male", 80, 40);
        Animal mouse2 = new Mouse("Roller", 1, "female", 100, 45);

        // 必须先getMoney再open，否则profit无法正常计算
        shop.getMoney(20000.00);
        shop.open();

        //buyIn
        {
            shop.buyIn(dog1);
            shop.buyIn(dog2);
            shop.buyIn(cat1);
            shop.buyIn(cat2);
            shop.buyIn(mouse1);
            shop.buyIn(mouse2);
        }

        shop.customerReception(customer1, "mOuSe");
        shop.customerReception(customer2, "cat");
        shop.customerReception(customer3, "Dog");
        shop.customerReception(customer1, "Dog");
        shop.customerReception(customer3, "Mouse");

        shop.close();
    }


}
