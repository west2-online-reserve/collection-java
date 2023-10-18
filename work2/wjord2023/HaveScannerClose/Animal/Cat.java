package Animal;

public class Cat extends Animals {
    public Cat() {
    }

    public Cat(String name, int age, String gender,
               String distinctiveFeatures) {
        super(name, age, gender, 200, distinctiveFeatures);
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
