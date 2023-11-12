package work2;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop petShop = new MyAnimalShop(5000.0);

        Dog dog1 = new Dog("浩男", 3, "Male", true);
        Dog dog2 = new Dog("浩", 3, "Male", false);
        Cat cat1 = new Cat("祖豪", 2, "Female");
        Cat cat2 = new Cat("祖", 12, "Male");
        Fish fish1 = new Fish("若辰", 1, "Female");
        Fish fish2 = new Fish("若", 12, "Male");

        Customer customer1 = new Customer("a", 3);
        Customer customer2 = new Customer("b", 2);
        Customer customer3 = new Customer("c", 1);

        try {
            petShop.buyAnimal(dog1);
            petShop.buyAnimal(cat1);
            petShop.buyAnimal(fish1);
            petShop.buyAnimal(dog2);
            petShop.buyAnimal(cat2);
            petShop.buyAnimal(fish2);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        //宠物店开始营业
        petShop.isOpen();
        System.out.println("----------------------");

        //招待顾客
        try {
            petShop.serveCustomer(customer1);
            petShop.serveCustomer(customer2);
            petShop.serveCustomer(customer3);
            petShop.serveCustomer(customer1);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        petShop.close();
    }
}
