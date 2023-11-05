public class Dog extends animal {
    boolean isVaccineInjected = true;

    public Dog(String n, int a, String s, double p) {
        super(n, a, s, p);
    }

    public String toString() {
        return "名字是：" + name +
                "\n年龄是：" + age +
                "\n性别是：" + sex +
                "\n价格是：" + price +
                "\n狂犬疫苗注射情况为：" + isVaccineInjected+
                "\n";
    }

}
