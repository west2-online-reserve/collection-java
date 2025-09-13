public class ChineseDog extends Animal{

    boolean isVaccined;
    public ChineseDog(String name, int age, String sex, double sellingPrice,double costPrice, boolean isVaccinated) {
        super(name, age, sex, sellingPrice, costPrice);
        this.isVaccined = isVaccinated;
    }
    //重写tostring方法
    @Override
    public String toString() {
        return "ChineseDog [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", sellingPrice=" + getSellingPrice() + ", isVaccinated=" + isVaccined +"]";
    }

}
