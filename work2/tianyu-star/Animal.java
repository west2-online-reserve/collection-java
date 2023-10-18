
public abstract class Animal{
    protected String name;
    protected int age;
    protected String sex;
    protected double price;
    protected String species;
    public Animal(String name,int age,String sex,double price,String species){
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
        this.species=species;
    }
    public abstract String toString();

    public String getName() {
        return name;
    }
//    public boolean equals(Object obj) {
//        if (this == obj) {return true;}
//        if (obj == null || getClass() != obj.getClass()) {return false;}
//        Animal animal = (Animal) obj;
//        return age == animal.age && Object.equals(name, animal.name);
//    }
//    @Override
//    public int hashCode() {
//        return Object.hashCode(name, age);
   //]



}

