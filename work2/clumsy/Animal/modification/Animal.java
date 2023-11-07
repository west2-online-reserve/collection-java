package modification;

import java.util.Objects;

public abstract class Animal {
     private String name;
     private int age;
     private String gender;
     private double price;
     //全参构造
     public Animal(String name,int age,String gender, double price){
          this.name=name;
          this.age=age;
          this.gender=gender;
          this.price=price;
     }

    public Animal() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return age == animal.age && Objects.equals(name, animal.name);

    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age,gender,price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //抽象方法
    public abstract String toString();//这个是输出价格给顾客看
    public abstract String toString(String mi);//toString 方法重载用来输出进价给店长看
}
