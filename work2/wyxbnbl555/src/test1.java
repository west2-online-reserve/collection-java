import java.util.List;

public class test1 {
    public static void main(String[] args) {
        Animal cat = new Cat("小白",2,"男",200);
        Animal dog = new ChinesePastoralDog("小黑",3,"女",true,100);
        Animal husky = new Husky("大怨种",3,"男",300);

        Customer cust1 = new Customer("张三",2);
        Customer cust2 = new Customer("李四",3);
        Customer cust3 = new Customer("王五",4);
        Customer cust4 = new Customer("冯二",5);

        MyAnimalShop shop = new MyAnimalShop(500,true);
        shop.buyNewAnimal(cat);
        shop.buyNewAnimal(dog);
        shop.buyNewAnimal(husky);

        shop.entertainCustomer(cust1);
        shop.entertainCustomer(cust2);
        shop.entertainCustomer(cust3);
        shop.entertainCustomer(cust4);

        List<Customer> customerList = shop.getCustomerList();

        shop.setBusiness(false);

        shop.outOfBusiness(customerList);

    }
}
