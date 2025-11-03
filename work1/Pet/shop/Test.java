package Pet.shop;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000);

        shop.buyNewAnimal(new ChineseRuralDog("来财",2,"公",true));
        shop.buyNewAnimal(new Cat("好运",1,"母"));
        shop.buyNewAnimal(new Rabbit("滚滚",3,"母",true));

        LocalDate today = LocalDate.now();
        shop.setToday(today);

        Customer Jennie = new Customer("Jennie");
        Customer Rose = new Customer("Rose");

        shop.serveCustomer(Jennie);
        shop.serveCustomer(Rose);

        try {
            shop.buyNewAnimal(new Rabbit("昂贵",1,"母",true));
        }catch (InsufficientBalanceException e){
            System.out.println("异常：" + e.getMessage());
        }

        shop.serveCustomer(new Customer("Lisa"));
        try {
            shop.serveCustomer(new Customer("Jisoo"));
        }catch (AnimalNotFoundException e){
            System.out.println("异常：" + e.getMessage());
        }

        shop.close();
    }
}
