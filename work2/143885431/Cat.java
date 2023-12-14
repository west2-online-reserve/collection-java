public class Cat extends Animal{
    public Cat(){
    }
    public Cat(String name,int age,String gender){
        super(name,age,gender,200.);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name:" + getName() +
                ",age:" + getAge() +
                ",gender:" + getGender() +
                ",price:" +getPrice() +
                '}';
    }
}
