package Work2;

public abstract class Animal {
    public String name;
    public int age;
    public String gender;
    public double price;
    public Animal(){
    }

    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;


    }

    public  String getName(){
        return name;
    }
    public int getAgege(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public double getPrice(){
        return price;
    }




    public abstract String toString();

}

