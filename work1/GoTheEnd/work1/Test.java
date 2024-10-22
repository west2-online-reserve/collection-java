import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class Test {
    public static void main(String[] args) {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new ChineseRuralDog("神里绫华的狗", 5,
                "雄性", 100, 80, "暴躁！！", true);
        Animal animal2 = new Cat("小猫", 6,
                "雄性", 100, 80, "温和");
        Animal animal3 = new Turtle("忍者神龟", 18,
                "雌性", 200, 160,"？？");
        Animal animal4 = new Cat("小小猫", 18,
                "雌性", 50, 30,"懒惰");
        animalList.add(animal1);
        animalList.add(animal2);
        MyAnimalShop shop = new MyAnimalShop(100, animalList);

        Animal newAnimal_1 = new Turtle("龟龟", 20,
                "雄性", 200, 140,"多动");
        Animal newAnimal_2 = new ChineseRuralDog("赤坂明你真的狗", 0,
                "雄性", 1, 1,"沙比", false);
        Animal newAnimal_3 = new Cat("想不到名字", 20,
                "雄性", 100, 60,"??");

        shop.buyAnimals(newAnimal_1);
        shop.buyAnimals(newAnimal_2);
        shop.buyAnimals(newAnimal_3);

        //查看商店当前状态
        shop.showShopStatus();

        shop.greetCustomer(new Customer("张三", 5, of(2024,3,10)));
        shop.greetCustomer(new Customer("平泽唯", 1, now()));
        shop.greetCustomer(new Customer("GoTheEnd", 3, now()));
        shop.greetCustomer(new Customer("GoTheEnd", 3, now()));

        shop.shopClose();

    }
}
