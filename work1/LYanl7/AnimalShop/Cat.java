/**
 * 猫猫类
 *
 * @author LYanl7
 * @since 2025-9-14
 */
public class Cat extends Animal {
    public static final double CAT_PRICE = 200.0;  // 猫猫价格
    public static final String TYPE = "猫猫"; // 动物类型

    /**
     * 全参构造方法
     * @param name      猫猫的名称
     * @param age       猫猫的年龄
     * @param gender    猫猫的性别
     */
    public Cat(String name, int age, Gender gender) {
        super(name, age, gender, CAT_PRICE);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("类型: ").append(TYPE).append("\n");
        sb.append("名称: ").append(name).append("\n");
        sb.append("年龄: ").append(age).append("岁").append("\n");
        sb.append("性别: ").append(gender.toString()).append("\n");
        sb.append("价格: ").append(price).append("元").append("\n");
        return sb.toString();
    }
}
