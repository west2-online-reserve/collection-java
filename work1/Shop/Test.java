package Shop;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop Shop = new MyAnimalShop(200);
        ;
        try {
            Shop.buy(new Dog("Kindred", 2, "male", 150));
            Shop.buy(new Cat("Ahri", 1, "female", 150));
            Shop.buy(new Parrot("Xayah", 1, "female", 150));
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        Customer customer1 = new Customer("LeBlanc");


        try {
            Shop.reception(customer1);

        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Shop.close();


    }


}
