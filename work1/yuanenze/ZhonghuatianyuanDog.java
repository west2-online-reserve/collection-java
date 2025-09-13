package petStore;

public class ZhonghuatianyuanDog extends Animal {
    private boolean isVaccineInjected;
    private static double PRICE = 100;

    public ZhonghuatianyuanDog() {
        super();
        this.setName("ZhonghuatianyuanDog");
        this.isVaccineInjected = false;
    }

    public ZhonghuatianyuanDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender);
        this.isVaccineInjected = isVaccineInjected;
    }

    public double getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("[" + this.getName() + "-" + this.getAge() + "-"
                + "-" + PRICE + "-"
                + this.getGender() + "-" + this.isVaccineInjected + "]");
    }

}
