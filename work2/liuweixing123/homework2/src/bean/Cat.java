package bean;

public class Cat extends Animal{
    public Cat(String name, int age, String gender) {
        super(name, age, gender, 200);
    }

    public Cat() {
        this.setPrice(200);//猫猫200元
    }

    @Override
    public String toString() {
        System.out.println("猫猫叫"+this.getName()+" 年龄为"+this.getAge()+" 性别为"+this.getGender()+" 价格为200元");
        return null;
    }
}
