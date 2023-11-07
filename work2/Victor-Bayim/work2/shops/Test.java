import java.time.LocalDate;
import java.util.regex.Pattern;

public class Test {

    private static boolean isValidName(String name) {
        return Pattern.matches("[A-Za-z\\s]+", name);
    }

    private static boolean isValidAge(int age) {
        return age > 0 && age < 20;
    }

    private static boolean isValidPrice(double price) {
        return price >= 50.0;
    }

    private static boolean isValidVisits(int visits) {
        return visits > 0;
    }

    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(300.0); // 设置较低的初始余额以测试余额不足情况

        // 测试买入动物 - 余额不足
        buyAnimalTest(shop, new Cat("Molly", 2, "female")); // 价格200，余额300

        // 测试买入动物 - 正常情况
        buyAnimalTest(shop, new ChineseRuralDog("Buddy", 3, "male", true)); // 价格100，余额100

        // 测试买入动物 - 再次余额不足
        buyAnimalTest(shop, new Cat("Lucy", 4, "female")); // 价格200，余额不足

        // 测试招待顾客 - 正常情况
        serveCustomerTest(shop, new Customer("John Doe", 1, LocalDate.now()));

        // 测试歇业情况
        shop.closeShop();

        // 测试宠物店歇业后的行为
        System.out.println("尝试在宠物店歇业后买入动物...");
        buyAnimalTest(shop, new ChineseRuralDog("Max", 2, "male", false));

        System.out.println("尝试在宠物店歇业后招待顾客...");
        serveCustomerTest(shop, new Customer("Alice Smith", 2, LocalDate.now()));

        // 测试 Customer 类 - 不同到访次数
        testCustomerVisits();
    }

    private static void buyAnimalTest(MyAnimalShop shop, Animal animal) {
        if (isValidName(animal.getName()) && isValidAge(animal.getAge()) && isValidPrice(animal.getPrice())) {
            try {
                System.out.println("尝试买入动物: " + animal);
                shop.buyAnimal(animal);
                System.out.println("买入成功，动物列表: " + shop.getAnimalList());
            } catch (InsufficientBalanceException e) {
                System.out.println("买入动物失败：" + e.getMessage());
            }
        } else {
            System.out.println("无效的动物信息: " + animal);
        }
    }

    private static void serveCustomerTest(MyAnimalShop shop, Customer customer) {
        if (isValidName(customer.getName()) && isValidVisits(customer.getVisits())) {
            try {
                System.out.println("尝试招待顾客: " + customer);
                shop.serveCustomer(customer);
                System.out.println("招待成功，顾客列表: " + shop.getCustomerList());
            } catch (AnimalNotFoundException e) {
                System.out.println("招待顾客失败：" + e.getMessage());
            }
        } else {
            System.out.println("无效的顾客信息: " + customer);
        }
    }

    private static void testCustomerVisits() {
        Customer frequentCustomer = new Customer("Emily Johnson", 5, LocalDate.now());
        Customer newCustomer = new Customer("Michael Brown", 1, LocalDate.now());

        System.out.println("测试顾客 - 频繁访问者: " + frequentCustomer);
        System.out.println("测试顾客 - 新访问者: " + newCustomer);
    }
}
