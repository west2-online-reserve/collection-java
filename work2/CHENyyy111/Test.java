package AnimalShop;

public class Test {
    public static void main(String[] args) {

        Customer customer1 = new Customer("Amy", 2);
        Customer customer2 = new Customer("John", 5);

        MyAnimalShop myAnimalShop1 = new MyAnimalShop(false, 1000);
        myAnimalShop1.buy(new ChineseFieldDog("ChineseFieldDog", 1, "boy", true));
        myAnimalShop1.buy(new GoldenRetriever("GoldenRetriever", 3, "girl", true));
        myAnimalShop1.buy(new Cat("Cat", 1, "girl", false));
        myAnimalShop1.treatCustomer(customer1);
        myAnimalShop1.treatCustomer(customer2);
        myAnimalShop1.rest();

        MyAnimalShop myAnimalShop2 = new MyAnimalShop(true, 1000);
        myAnimalShop2.buy(new ChineseFieldDog("ChineseFieldDog", 1, "boy", true));
        myAnimalShop2.buy(new GoldenRetriever("GoldenRetriever1", 3, "girl", true));
        myAnimalShop2.buy(new GoldenRetriever("GoldenRetriever2", 2, "boy", false));
        myAnimalShop2.buy(new Cat("Cat", 1, "girl", false));
        myAnimalShop2.buy(new GoldenRetriever("GoldenRetriever3", 1, "girl", true));
        myAnimalShop2.treatCustomer(customer1);
        myAnimalShop2.treatCustomer(customer2);
        myAnimalShop2.rest();
    }
}
