public class Cat extends Animal{
    protected boolean isVaccineInjected;//是否打过疫苗
    public Cat(String name, double cost, String gender, int age,boolean isVaccineInjected){
        super(name,cost,gender,age);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "这是一只猫，名字叫"+this.name+",价格为："+this.cost+",性别为:"+this.gender+",年龄为:"+this.age+"，是否打过疫苗:"+this.isVaccineInjected;
    }
}
