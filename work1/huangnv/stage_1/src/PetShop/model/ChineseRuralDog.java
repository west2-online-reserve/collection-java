package PetShop.model;

public class ChineseRuralDog extends Animal {
    private final boolean isVaccineInjected;
    public  ChineseRuralDog(String name,int age, boolean isMale, boolean isVaccineInjected){
        super(name, age, isMale, 100,200);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬 {" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + isMale + '\'' +
                ", 购入价格=" + buyPrice +
                ", 售出价格=" + sellPrice +
                ", 是否注射疫苗=" + isVaccineInjected +
                '}';
    }
}
