package bean;

public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;

    @Override
    public String toString() {
        System.out.println("中华田园犬叫"+this.getName()+" 年龄为"+this.getAge()+" 性别为"+this.getGender()+" 价格为100元");
        return null;
    }


    public ChineseRuralDog(String name, int age, String gender,  boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public ChineseRuralDog() {
        setPrice(100);
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
