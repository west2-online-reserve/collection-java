public class Cat extends Animal {

    public Cat(String name, int age, String sex,  double sellingPrice,double costPrice) {
        super(name, age, sex, sellingPrice, costPrice);
    }
    @Override
    public String toString() {
        return "Cat [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", price=" + getSellingPrice() + "]";
    }
}
