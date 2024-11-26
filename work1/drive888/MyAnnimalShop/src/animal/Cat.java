package animal;

public class Cat extends Animal{

    //重写父类方法
    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200);

    }

    public String toString(){
        return "猫名字:" + getName() + ",年龄:" + getAge() + ",价格:" + getPrice();
    }
}
