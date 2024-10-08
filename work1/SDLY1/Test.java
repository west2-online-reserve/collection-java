package west2;
public class Test {
    public static void main(String[] args) {
        try {
            MyAnimalShop shop = new MyAnimalShop(1000.0);
            shop.buyAnimal(new ChineseFarmDog("旺财", 3, "male", true));
            shop.buyAnimal(new Cat("咪咪", 2, "female"));

            Customer customer1 = new Customer("张三");
            shop.serveCustomer(customer1);

            shop.closeShop();

        } catch (InsufficientBalanceException | AnimalNotFoundException e) {
            e.printStackTrace();
        }
    }
}
