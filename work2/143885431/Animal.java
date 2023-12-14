public abstract class Animal {

    protected String name;
    protected int age;
    protected String gender;
    protected double price;


    public Animal(){
    }
    public Animal(String name,int age,String gender,double price){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;

    }

    //get,set方法

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }



    @Override
    public abstract String toString();
}
