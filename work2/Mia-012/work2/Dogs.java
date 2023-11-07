package work2;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class Dogs extends Animal {
    private boolean isVaccineInjected;

    public Dogs() {
    }


    public Dogs(String name, int age, String sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "这只狗狗的姓名为：" + name + "   年龄为：" + age + "   性别为：" + sex + "   价格为：" + price + "   " + isVaccineInjected();
    }

    public String isVaccineInjected() {
        if (isVaccineInjected) {
            return "已注射疫苗";
        } else {
            return "未注射疫苗";
        }
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
