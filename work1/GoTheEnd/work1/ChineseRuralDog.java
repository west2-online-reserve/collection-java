public class ChineseRuralDog extends Animal {
    protected boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String gender, double price, double cost, String temperament, boolean isVaccineInjected) {
        super(name, age, gender, price, cost, temperament);
        this.isVaccineInjected = isVaccineInjected;
    }
    @Override
    public void feed(){
        System.out.println("【你喂食了ChineseRuralDog " + this.name + "】");
        System.out.println("\"woof!\"\n");
    }
    @Override
    public String toString(){
        return "ChineseRuralDog" + "\n" +
                "-----------\n" +
                "name: " + name + "\n" +
                "age: " + age + "\n" +
                "gender: " + gender + "\n" +
                "price: " + price + "\n" +
                "temperament: " + temperament + "\n" +
                "isVaccineInjected: " + isVaccineInjected ;
    }
}
