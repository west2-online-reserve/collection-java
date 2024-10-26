package Work2;

public class Cat extends Animal{
    public Cat(String name, int age, String gender, double price){
        super(name, age, gender, 200);

    }

    public Cat(String lily, String number, String female, String number1) {
    }

    @Override
    public String toString() {
        return "名字"+name+",年龄"+age+",性别"+gender+",价格"+price;
    }
}
