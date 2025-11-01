import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop mas = new MyAnimalShop(700, new ArrayList<>(), new ArrayList<>(), true, LocalDate.now(), 0);
        //System.out.println(mas.getAnimalList().get(0).price);
        try {
            mas.buyNewAnimal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mas.trateCustomer(mas.createCustomer());
        mas.closeShop();
    }
}
