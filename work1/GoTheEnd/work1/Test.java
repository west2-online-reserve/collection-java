import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class Test {
    public static void main(String[] args) {
        //创建一些动物
        Animal animal1 = new ChineseRuralDog("神里绫华的狗", 5,
                "雄性", 100, 80, "暴躁！！", true);
        Animal animal2 = new Cat("小猫", 6,
                "雄性", 100, 80, "温和");
        Animal animal3 = new Turtle("忍者神龟", 18,
                "雌性", 200, 160,"？？");
        Animal animal4 = new Cat("小小猫", 18,
                "雌性", 50, 30,"懒惰");

        //开始初始化动物列表
        List<Animal> animalList = new ArrayList<>();
        animalList.add(animal1);
        animalList.add(animal2);
        MyAnimalShop shop = new MyAnimalShop(100, animalList);

        //一些可供选择购买的动物对象
        Animal newAnimal_1 = new Turtle("龟龟", 20,
                "雄性", 200, 140,"多动");
        Animal newAnimal_2 = new ChineseRuralDog("赤坂明你真的狗", 0,
                "雄性", 1, 1,"沙比", false);
        Animal newAnimal_3 = new Cat("想不到名字", 20,
                "雄性", 100, 60,"??");

        //喂几只动物！！
        animalList.get(1).feed();
        animalList.get(0).feed();

        //购买新动物，测试余额足够或不足够的情况
        shop.buyAnimals(newAnimal_1);
        shop.buyAnimals(newAnimal_2);
        shop.buyAnimals(newAnimal_3);

        //【开店前】查看店铺当前状态
        shop.showShopStatus();

        //注意到这不是今天的客户，而在今天输入，认为在补充客户预约信息。
        shop.greetCustomer(new Customer("张三", 5, of(2024,3,10)));

        //顾客来访，但是现在没开店，那么显然无法招待客户
        shop.greetCustomer(new Customer("山田凉", 1, now()));

        //开店！！！！！！
        shop.openShop();

        //招待顾客
        shop.greetCustomer(new Customer("平泽唯", 1, now()));

        //【开店后】查看店铺状态
        shop.showShopStatus();

        //测试有动物或无动物的情况
        shop.greetCustomer(new Customer("GoTheEnd", 3, now()));
        shop.greetCustomer(new Customer("Gone", 3, now()));
        shop.greetCustomer(new Customer("G", 3, now()));

        //关店
        shop.shopClose();

    }
}
