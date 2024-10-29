package work1;



public class TestMyShop {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000);
        ChineseRuralDog dog = new ChineseRuralDog("旺财", 3, "Male");
        Cat cat = new Cat("喵喵", 2, "Female");
        Pig pig = new Pig("啾啾", 1, "Female");


        shop.buyAnimal(dog);
        shop.buyAnimal(cat);
        shop.buyAnimal(pig);

        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");

        try {
            shop.serveCustomer(customer1);
            shop.serveCustomer(customer2);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        shop.closeShop();
    }
}

