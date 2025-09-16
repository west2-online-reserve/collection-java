/**
 * 中华田园犬类
 *
 * @author LYanl7
 * @since 2025-9-14
 */
public class RuralDog extends Animal {

    private boolean isVaccineInjected; // 是否注射狂犬疫苗

    public static final double CHINESE_RURAL_DOG_PRICE = 100.0;  // 中华田园犬价格
    public static final String TYPE = "中华田园犬"; // 动物类型

    /**
     * 全参构造方法
     * @param name                  中华田园犬的名称
     * @param age                   中华田园犬的年龄
     * @param gender                中华田园犬的性别
     * @param isVaccineInjected     中华田园犬是否注射狂犬疫苗
     */
    public RuralDog(String name, int age, Gender gender, boolean isVaccineInjected) {
        super(name, age, gender, CHINESE_RURAL_DOG_PRICE);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("类型: ").append(TYPE).append("\n");
        sb.append("名称: ").append(name).append("\n");
        sb.append("年龄: ").append(age).append("岁").append("\n");
        sb.append("性别: ").append(gender.toString()).append("\n");
        sb.append("价格: ").append(price).append("元").append("\n");
        sb.append("是否注射疫苗: ").append(isVaccineInjected ? "是" : "否").append("\n");
        return sb.toString();
    }


}
