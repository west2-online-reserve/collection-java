package chongwudian;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog() {
        super.setPrice(100.0);
    }

    public Dog(String name, int age, String gender, Double price, boolean isVaccineInjected) {
        super(name, age, gender, price);
        this.isVaccineInjected = isVaccineInjected;
        this.kind=2;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬 Name:"+super.getName()+" Age:"+getName()+" gender:"+getGender()+" price:"+getPrice()+" 疫苗："+isVaccineInjected;
    }
}
