public class hamster extends Animal{
    public hamster(String name,int age,String sex,String species){
        super(name,age,sex,200,species);
    }
    @Override
    public String toString() {
        return "\nname:"+name+" age:"+age+" sex:"+sex+" price:"+200+" species:"+species;
    }

}