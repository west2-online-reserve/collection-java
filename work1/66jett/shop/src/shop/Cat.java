package shop;

/**
 * 猫猫类
 */
public class Cat extends Animal {

    /**
     * 毛色
     */
    private String furColor;

    /**
     * 构造方法
     */
    public Cat(String name, int age, String gender, String furColor) {
        super(name, age, gender, 200.0);
        this.furColor = furColor;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    @Override
    public String toString() {
        return String.format("猫咪 [名字: %s, 年龄: %d岁, 性别: %s, 价格: %.2f元, 毛色: %s]",
                getName(), getAge(), getGender(), getPrice(), furColor);
    }
}