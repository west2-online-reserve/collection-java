public class ChineseTianyuanDog extends Animal{
    boolean isVaccineInjected=false;
    public ChineseTianyuanDog(String animalname,int age,int gentle,double price)
    {
        super(animalname,age,gentle,100);
    }

    @Override
    public String toString() {
        String temp="name"+animalname+" "+"age:"+age+" "+"gentle:"+gentle+"price:100";
        return temp;
    }
}
