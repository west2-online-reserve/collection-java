public class Rabbit extends Animal{
    public Rabbit(){
    }
    public Rabbit(String name,int age,String gender){
        super(name,age,gender,523);
    }

    @Override
    public String toString() {
        return " Rabbit{" +
                "name:" + getName() +
                ",age:" + getName() +
                ",gender" + getGender() +
                ",price" +getPrice() +
                '}';
    }

}
