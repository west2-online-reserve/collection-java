import java.time.LocalDate;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //创建一个宠物店实例，给定余额，初始化动物列表，一个空的顾客列表
        // 测试买入动物，招待顾客，歇业
        //输出“欢迎光顾小店”
        System.out.println("欢迎光顾小店");
        //输出“当前余额为：1000”
        System.out.println("当前余额为：1000");
        //获取实参信息
        System.out.println("输入你的名字！");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();

        try {
            // 创建宠物店
            MyAnimalShop shop = new MyAnimalShop(1000);

            // 使用Cat类创建动物对象
            shop.buyNewAnimal(new Cat("小猫", 2, "雌性"));
            // 或者使用ChineseVillageDog类创建动物对象
            shop.buyNewAnimal(new ChineseVillageDog("小狗", 1, "雄性", true));

            // 创建顾客对象，保存引用以便后续跟踪光顾次数
            Customer customer = new Customer(customerName, 0, LocalDate.now());
            shop.serveCustomer(customer);

            shop.closeShop();
        } catch (InsufficientBalanceException | AnimalNotFoundException | InvalidAnimalIndexException e) {
            System.out.println("发生错误：" + e.getMessage());
        } finally {
            scanner.close(); // 确保Scanner被关闭
        }
    }
}
