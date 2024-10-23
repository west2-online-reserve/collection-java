public class ChineseDog extends Animal {
    private boolean isVaccineInjected;
    public ChineseDog(){
        super();
    }
    public ChineseDog(String name , int age , String gender ,double price, boolean isVaccineInjected){
        super(name , age , gender , price);
        this.isVaccineInjected = isVaccineInjected;
    }
    @Override
    public String toString() {
        return "姓名"+getName()+"   年龄"+getAge()+"  性别"+getGender()+"    价格"+getPrice()+"    是否有病"+isVaccineInjected;
    }
}
