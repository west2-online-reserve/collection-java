/**
 * 动物抽象类，一切动物的父类
 *
 * @author LYanl7
 * @since  2025-9-14
 */
public abstract class Animal {

    /**
     * 性别枚举
     *
     */
    public enum Gender {
        MALE, // 雄性
        FEMALE; // 雌性

        @Override
        public String toString() {
            if (this == MALE) {
                return "雄性";
            } else {
                return "雌性";
            }
        }
    }

    protected String name;
    protected int age;
    protected Gender gender;
    protected double price;

    /**
     * 重写toString方法，返回动物信息
     * <p> 格式要求:
     * <ul>
     *     <li>类型: 动物的具体类型</li>
     *     <li>名称: 动物名称</li>
     *     <li>年龄: 动物年龄</li>
     *     <li>性别: 动物性别</li>
     *     <li>价格: 动物价格</li>
     *     <li>其他信息: 其他特有信息</li>
     * </ul>
     *
     * @return 格式化动物信息
     */
    public abstract String toString();

    /**
     * 全参构造方法
     * @param name          动物的名称
     * @param age           动物的年龄
     * @param gender        动物的性别
     * @param price         动物的价格
     */
    public Animal(String name, int age, Gender gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }
}
