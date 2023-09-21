/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: Dog
 * @description（类描述）: 猫猫类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class Cat extends Animal {
    double catPrice = super.price;

    public Cat(String name, int age, double price, boolean gender) {
        super(name, age, 200, gender);
    }

    @Override
    public String toString() {
        String msg = "the style of animal:cat\n" + "name:" + name + "\nage:" + age + "\ngender" + this.getGender() + "\nprice:" + price+"\n";
        return msg;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return (this.gender ? "雄" : "雌性");
    }


}
