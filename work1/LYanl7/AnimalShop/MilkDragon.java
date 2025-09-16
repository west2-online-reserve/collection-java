/**
 * 奶龙类
 *
 * @author LYanl7
 * @since 2025-9-14
 */
public class MilkDragon extends Animal {
    public static final double MILK_DRAGON_PRICE = 114514.0;  // 奶龙价格
    public static final String TYPE = "奶龙"; // 动物类型

    /**
     * 全参构造方法
     * @param name    奶龙的名称
     * @param age     奶龙的年龄
     * @param gender  奶龙的性别
     */
    public MilkDragon(String name, int age, Gender gender) {
        super(name, age, gender, MILK_DRAGON_PRICE);
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
