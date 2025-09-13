import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        //用来获取今天日期
        LocalDate today =LocalDate.now();
        //导入宠物到宠物店
        ArrayList<String> animals = new ArrayList<>();
        animals.add("ChineseDog");
        animals.add("Cat");
        animals.add("Sheep");

        //创建一个顾客列表
        ArrayList<Customer>  customers = new ArrayList<>();
        customers.add(new Customer("Tom",1,"Cat"));
        customers.add(new Customer("Jerry",2,"Sheep"));
        customers.add(new Customer("James",3,"Sheep"));

        //创建一个宠物店
        MyAnimalShop myAnimalShop = new MyAnimalShop(animals,1000,customers);
        myAnimalShop.setWantAnimal("Sheep");


        //判断是否营业(2021.01.0、2021.01.02、2021。01.03营业)
        if (myAnimalShop.isOpen(today)) {
            System.out.println("今天是营业日");
            //服务顾客
            try {
                myAnimalShop.serveCustomer(customers.get(0));
                myAnimalShop.serveCustomer(customers.get(1));
                myAnimalShop.serveCustomer(customers.get(2));
            } catch (AnimalNotFountException e) {
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println("今天不是营业日");

        //用来判断余额是否足够
        try {
            myAnimalShop.buyAnimal();
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("当前余额:"+myAnimalShop.getBalance());

    }
}
