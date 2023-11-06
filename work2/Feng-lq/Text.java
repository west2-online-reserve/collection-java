package work2;

/**
 * @author FAN
 */
public class Text {
    public static void main(String[] args) {
        ChineseRuralDog dog1 = new ChineseRuralDog("小狗a", 1, 0);
        ChineseRuralDog dog2 = new ChineseRuralDog("小狗b", 3, 1);
        ChineseRuralDog dog3 = new ChineseRuralDog("小狗c", 2, 0);
        ChineseRuralDog dog4 = new ChineseRuralDog("小狗d", 1, 1);
        Bird bird1 = new Bird("小鸟a", 1, 0);
        Bird bird2 = new Bird("小鸟b", 2, 1);
        Cat cat1 = new Cat("小猫a", 2, 0);
        Cat cat2 = new Cat("小猫b", 1, 0);

        Customer customer1 = new Customer("小米", 1);
        Customer customer2 = new Customer("小了", 3);
        Customer customer3 = new Customer("小和", 0);
        Customer customer4 = new Customer("小能", 4);
        Customer customer5 = new Customer("小福", 3);
        Customer customer6 = new Customer("小想", 0);
        Customer customer7 = new Customer("小一", 0);


        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.buyNewAnimal(dog1);

        myAnimalShop.isOpen();

        myAnimalShop.buyNewAnimal(dog1);
        myAnimalShop.buyNewAnimal(dog2);
        myAnimalShop.buyNewAnimal(bird1);
        myAnimalShop.buyNewAnimal(bird2);
        myAnimalShop.buyNewAnimal(cat1);
        myAnimalShop.buyNewAnimal(cat2);

        myAnimalShop.entertainClients(customer1);
        myAnimalShop.entertainClients(customer2);
        myAnimalShop.entertainClients(customer3);
        myAnimalShop.entertainClients(customer4);
        myAnimalShop.entertainClients(customer5);
        myAnimalShop.entertainClients(customer6);
        myAnimalShop.entertainClients(customer7);

        myAnimalShop.buyNewAnimal(dog3);
        myAnimalShop.buyNewAnimal(dog4);

        myAnimalShop.isClose();

        myAnimalShop.entertainClients(customer1);

        System.out.println();
        Bonus bonus = new Bonus();
        System.out.println(bonus.isValidMail("100862023@qq.com"));
        System.out.println(bonus.isValidMail("100862023qq@.com"));
        System.out.println(bonus.isValidMail("100862023@qq222.com"));

    }
}
