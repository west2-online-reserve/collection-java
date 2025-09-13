import javax.naming.Name;
import javax.sound.midi.SysexMessage;

public class Zhonghuadog extends Animal{

    boolean isVaccineInjected;

    public Zhonghuadog(String name, int age, char sex) {
        super(name, age, sex, 100);
        this.isVaccineInjected = false;
    }

    public Zhonghuadog(String name, int age, char sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Zhonghuadog [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", price=" + getPrice() +
                "元, isVaccineInjected=" + isVaccineInjected + "]";
    }
}
