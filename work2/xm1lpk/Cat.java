public class Cat extends Animal{
    public Cat(String animalname, int age, int gentle, double price) {
        super(animalname, age, gentle, 200);
    }

    public String toString() {
       return "name:"+animalname+" "+"age:"+age+" "+"gentle:"+gentle+" "+"price:200";
    }

}
