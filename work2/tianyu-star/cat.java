

public class cat extends Animal{
    public cat(String name,int age,String sex,double price,String species){
        super(name,age,sex,price,species);
    }
    @Override
    public String toString() {
        return "\nname:"+name+" age:"+age+" sex:"+sex+" price:"+200+" species"+species;
    }

}