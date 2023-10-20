package Animal;

/**
 * @author  wjord
 */
public class ChinesePastoralDogs extends Animals {
    protected boolean isVaccineInjected;

    public ChinesePastoralDogs() {
    }

    public ChinesePastoralDogs(String name, int age, String gender,
                               String distinctiveFeatures) {
        super(name, age, gender, 100, distinctiveFeatures);
        this.isVaccineInjected = false;
        // 我想先让狗固定没打疫苗到时候如果客户要求要打疫苗再涨价
    }

    public boolean getIsVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "\n宠物姓名为：" + getName() +
                "\n年龄为：" + getAge() +
                "\n性别为：" + getGender() +
                "\n价格为:" + getPrice() +
                "\n是否注射过疫苗：" + getIsVaccineInjected() +
                "\n该宠物有以下特点：" + getDistinctiveFeatures() +
                "\n";
    }
}
