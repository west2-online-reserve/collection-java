import java.time.LocalDate;

public class Test {
    static void main(String[] args) {
        MyAnimalShop Shop = new MyAnimalShop(1000);
        Cat mimi = new Cat("喵喵", 2, "雌", 1);
        ChineseDog wangwang = new ChineseDog("汪汪", 5, "雄", 1, true);
        Customer me = new Customer("小洪", 3, LocalDate.now());
        Customer he = new Customer("小希", 6, LocalDate.now());
        try {
            Shop.bugNewAnimal(mimi);
            Shop.bugNewAnimal(wangwang);
            Shop.serveCustomer(me);
            Shop.serveCustomer(he);
            Shop.closeShop();
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }
}
