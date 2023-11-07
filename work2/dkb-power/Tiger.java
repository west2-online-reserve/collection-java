public class Tiger extends Animal {

    public Tiger(String name, int age, String gender, double price) {
        super(name, age, gender, 500);
    }

    @Override
    public String toString() {
        return "老虎姓名为" + getName() + " 年龄" + getAge() + " 性别" + getGender() + " 价格" + getPrice();
    }
}
