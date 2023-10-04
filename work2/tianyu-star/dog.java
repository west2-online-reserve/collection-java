public class dog extends Animal{
    protected boolean isVaccineInjected;

    public dog(String name,int age,String sex,double price,String species,boolean isVaccineInjected) {
        super(name,age,sex,price,species);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "\nname:"+name+" age:"+age+" sex:"+sex+" price:"+100+" isVaccineInjected:"+isVaccineInjected+" species"+species;
    }

}
