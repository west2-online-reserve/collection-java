package chongwudian;

public class Cat extends Animal {
    public Cat() {
        setPrice(100.0);
    }

    public Cat(String name, int age, String gender, Double price) {
        super(name, age, gender, price);
        this.kind=1;
    }

    @Override
    public String toString() {
        return "猫猫 "+"name:"+getName()+" price:"+getPrice()+" gender:"+getGender()+" age:"+getAge();
    }
}
