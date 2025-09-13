public class Bird extends Animal{
    public Bird(String animalname,int age,int gentle,double price)
    {
        super(animalname,age,gentle,75);
    }

    @Override
    public String toString() {
        return "name"+animalname+" "+"age:"+age+" "+"gentle:"+gentle+"price:100";
    }
}
