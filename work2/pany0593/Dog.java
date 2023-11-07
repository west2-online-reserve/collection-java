/**
 * 中华田园犬类
 * 继承于抽象动物类
 *
 * @author pany0593
 * @date 2023/10/30
 */
public class Dog extends AbstractAnimal {
    private boolean isVaccineInjected;

    public void setVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean getIsVaccineInjected() {
        return this.isVaccineInjected;
    }


    Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.setVaccineInjected(isVaccineInjected);
    }

    @Override
    public String toString() {
        return "姓名：" + this.getName() + "\n" +
                "年龄：" + this.getAge() + "\n" +
                "性别：" + this.getGender() + "\n" +
                "价格：" + this.getPrice() + "\n" +
                "是否注射狂犬病疫苗：" + (this.getIsVaccineInjected() ? "是" : "否") + "\n";
    }
}
