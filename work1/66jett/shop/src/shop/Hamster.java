package shop;

/**
 * 仓鼠类
 */
public class Hamster extends Animal {

    /**
     * 品种
     */
    private String breed;

    /**
     * 构造方法
     */
    public Hamster(String name, int age, String gender, String breed) {
        super(name, age, gender, 50.0);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return String.format("仓鼠 [名字: %s, 年龄: %d岁, 性别: %s, 价格: %.2f元, 品种: %s]",
                getName(), getAge(), getGender(), getPrice(), breed);
    }
}