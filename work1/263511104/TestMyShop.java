package work1;

public class TestMyShop {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(500);
        shop.buyAnimal(new ChineseRuralDog("旺财", 3, "male", 100));
        shop.buyAnimal(new Cat("喵喵", 2, "female",200));
        shop.buyAnimal(new Pig("Peggy",2,"male",400));

        try {
            shop.serveCustomer(new Customer("张三"));
            shop.serveCustomer(new Customer("李四"));
            shop.serveCustomer(new Customer("王五"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        shop.closeShop();
    }
}
