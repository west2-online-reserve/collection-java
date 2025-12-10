package P;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(500.0);

        try {
            shop.buyAnimal(new Chinesedog("DaHuang", 2, "male", true));
        } catch (InsufficientBalanceException e) {
            System.out.println("can't buy" + e.getMessage());
        }
        try {
            Customer c1 = new Customer("Rick");
            shop.serveCustomer(c1);
        } catch (AnimalNotFoundException e) {
            System.out.println("failed: " + e.getMessage());
        }
        shop.closeShop();
    }
}
