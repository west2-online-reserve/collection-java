package java_work2;

/**
 * Cat类
 * 继承于抽象Animal类
 *
 * @author formrc
 * @date 2023/10/23
 */
class Cat extends Animal {
    private static final double defaultPrice = 200.0;

    public Cat(String name, int age, String gender) {
        super(name, age, gender, defaultPrice);
    }

    @Override
    public String toString() {
        return  "Cat: " + "\n" +
                "name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Price: " + price + "\n" ;
    }
}
