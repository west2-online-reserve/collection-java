package Animal;

/**
 * @author  wjord
 */
public class Parrot extends Animals {
    public Parrot() {
    }

    public Parrot(String name, int age, String gender,
                  String distinctiveFeatures) {
        super(name, age, gender, 50, distinctiveFeatures);
    }

    @Override
    public String toString() {
        return "\n宠物姓名为：" + getName() +
                "\n年龄为：" + getAge() +
                "\n性别为：" + getGender() +
                "\n价格为:" + getPrice() +
                "\n该宠物有以下特点：" + getDistinctiveFeatures() +
                "\n";
    }
}
