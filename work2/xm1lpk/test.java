import java.util.Scanner;
import java.time.LocalDate;
public class test {
    public static void main(String[] args) {
        MyanimalShop myanimalShop = new MyanimalShop();
        Scanner scanner = new Scanner(System.in);

        System.out.println("输入1购买dog，输入2购买cat");
        int temp = scanner.nextInt();
        if (temp == 1) {
            ChineseTianyuanDog chineseTianyuanDog = new ChineseTianyuanDog("John", 3, 1, 20);
            myanimalShop.addanimal(chineseTianyuanDog);
        } else if (temp == 2) {
            Cat cat = new Cat("Alice", 4, 1, 60);
            myanimalShop.addanimal(cat);
        }

        Customer c1 = new Customer("LeBron", 100, LocalDate.now());
        Customer c2 = new Customer("James", 0, LocalDate.now());

        myanimalShop.dealCustomers(c1);
        myanimalShop.dealCustomers(c2);

        myanimalShop.Isopened();
    }
}
