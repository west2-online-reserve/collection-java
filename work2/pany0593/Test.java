import java.time.LocalDate;

/**
 * 测试类
 * 程序主入口
 * 用于测试各类功能
 *
 * @author pany0593
 * @date 2023/10/30
 */
public class Test {
    public static void main(String[] args) {
        Cat mm = new Cat("mm", 1, "雄性");
        Dog ww = new Dog("ww", 2, "雄性", true);
        Rabbit tt = new Rabbit("tt", 1, "雌性", "白色");
        MyAnimalShop shop = new MyAnimalShop(200);
        //测试购买宠物
        try {
            shop.buyNewAnimal(mm);
            shop.buyNewAnimal(ww);
            //第三只余额不足
            shop.buyNewAnimal(tt);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        //测试招待顾客
        Customer z3 = new Customer("z3", 5, LocalDate.of(2023, 9, 1));
        Customer l4 = new Customer("l4", 1, LocalDate.of(2023, 1, 1));
        Customer w5 = new Customer("w5", 3, LocalDate.of(2023, 10, 10));
        try {
            shop.serveCustomer(l4);
            shop.serveCustomer(z3);
            shop.serveCustomer(w5);
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        shop.closeShop();
    }
}
