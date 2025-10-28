public class Cat extends Animal {
    // 构造方法
    public Cat(String animalName, int age, String sex) {
        // 调用父类构造方法，设置价格为200元
        super(animalName, age, sex, 200);
    }

    @Override
    public String toString() {
        return "猫{" +
                "名字='" + getAnimalName() + '\'' +
                ", 年龄=" + getAge() +
                ", 性别='" + getSex() + '\'' +
                ", 价格=" + getPrice() +
                "}";
    }


}

