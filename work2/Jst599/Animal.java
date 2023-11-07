package PetShop;
/**
 * Animal类表示宠物店中的宠物
 * 该类包含动物的名字,年龄,性别,价格...以及将这些内容输出的方法
 * @author Jst599
 * @date 2023/10/17
 */
 public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected double inputPrice;

    public Animal() {
    }

    public Animal(String name, int age, String gender, double price, double inputPrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.inputPrice = inputPrice;
    }


    public double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public abstract String toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
