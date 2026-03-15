package assessment01;

public class Cat extends Animal{
    // 构造方法
    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200); // 价格固定为200元
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "猫猫 [名字=" + getName() + ", 年龄=" + getAge() + ", 性别=" + getGender() + ", 价格=" + getPrice() + "]";
    }
}
