import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 测试类
 * @author 1293978818
 */
public class Test {
    public static void main(String[] args) {

        MyAnimalShop myAnimalShop = new MyAnimalShop(100, new ArrayList<>(), new ArrayList<>(), false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
        Dogs dog1 = new Dogs("二蛋", 3, "雄", true);
        Cat cat1 = new Cat("咪咪", 3, "雌");
        Customer c1 = new Customer("张三",1,LocalDate.parse("2023年1月1日",formatter));
        Customer c2 = new Customer("李四",1,LocalDate.parse("2023年1月2日",formatter));

        //未购买任何动物时售出       
        try{
            myAnimalShop.treatCustomers(c1, dog1, 200);
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }

        //现购买咪咪，因为余额不足，所以购买失败
        try {
            myAnimalShop.buy(cat1);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        //现购买二蛋，因为余额充足，所以购买成功
        try {
            myAnimalShop.buy(dog1);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        //顾客使用低于成本价购买二蛋，购买失败
        myAnimalShop.treatCustomers(c1, dog1, 0.01);

        //顾客购买成功，此时余额为300
        myAnimalShop.treatCustomers(c1, dog1, 300);

        //顾客购买咪咪成功，，此时余额为400
        try {
            myAnimalShop.buy(cat1);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        myAnimalShop.treatCustomers(c2, cat1, 300);

        //歇业
        myAnimalShop.getClose();
    }
}
