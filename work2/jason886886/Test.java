package work2;

import java.util.ArrayList;

/**
 * @author jason
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<AbstractAnimal> animals = new ArrayList<>();
        MyAnimalShop myAnimalShop = new MyAnimalShop(100, customers, animals, true);
        AbstractAnimal dog1 = new Dog("旺财", 3, "雄性", true);
        AbstractAnimal cat1 = new Cat("小白", 4, "雄性");
        AbstractAnimal rabbit1 = new Rabbit("萝卜", 2, "雌性", 50);
        ArrayList<AbstractAnimal> marketOfAnimals = new ArrayList<>();
        marketOfAnimals.add(dog1);
        marketOfAnimals.add(cat1);
        marketOfAnimals.add(rabbit1);
        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");
        Customer customer3 = new Customer("王五");
        while (true) {
            try {
                myAnimalShop.buyNewAnimal(marketOfAnimals);
                myAnimalShop.buyNewAnimal(marketOfAnimals);
                break;
            } catch (InsufficientBalanceException e) {
                e.printStackTrace();
                boolean isBalanceEnough = false;
                for (AbstractAnimal marketOfAnimal : marketOfAnimals) {
                    if (myAnimalShop.getBalance() >= marketOfAnimal.price) {
                        isBalanceEnough = true;
                        break;
                    }
                }
                if (!isBalanceEnough) {
                    System.out.println("余额不足以买入任何动物");
                    break;
                }
                System.out.println("请选择价格合适的动物购买");
            }
        }
        AbstractAnimal dog2 = new Dog("大黑", 4, "雄性", true);
        animals.add(dog2);
        try {
            myAnimalShop.serveCustomer(customer1);
            myAnimalShop.serveCustomer(customer2);
            myAnimalShop.serveCustomer(customer3);
        } catch (AnimalNoFoundException e) {
            e.printStackTrace();
            myAnimalShop.closeBusiness();
        }
        myAnimalShop.closeBusiness();
    }
}
