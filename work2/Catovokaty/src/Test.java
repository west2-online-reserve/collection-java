import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // 创建宠物店实例
        MyAnimalShop animalShop = new MyAnimalShop();

        // 设置余额
        animalShop.balance = 1000.0;

        // 初始化动物列表
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new LyingCat("Max", 2, "Male", 500.0));
        animalList.add(new Giraffe("Luna", 1, "Female", 300.0));
        // 添加更多动物到列表...

        // 设置动物列表
        animalShop.animalList = animalList;

        // 初始化顾客列表
        List<Customer> customerList = new ArrayList<>();
        // 添加顾客到列表...

        // 设置顾客列表
        animalShop.customerList = customerList;

        try {
            // 测试买入动物
            Animal animal = new ChinesePastoralDog("Buddy", 3, "Male", 400.0);
            animalShop.buyAnimal(animal);
            System.out.println("Animal purchased: " + animal.toString());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        // 测试招待顾客
        LocalDate arriveTime = LocalDate.parse("2023-07-17");
        Customer customer = new Customer("John",9,arriveTime);
        animalShop.serveCustomer(customer);
        System.out.println("Customer served: " + customer.toString());

        // 测试歇业
        LocalDate date = LocalDate.now();
        animalShop.closeShop(date);

        // 添加更多测试代码...

        // 测试余额是否正确
        System.out.println("Balance: " + animalShop.balance);

        // 测试动物列表是否已清空
        System.out.println("Animal list: " + animalShop.animalList);

        // 测试顾客列表是否已清空
        System.out.println("Customer list: " + animalShop.customerList);
    }
}
