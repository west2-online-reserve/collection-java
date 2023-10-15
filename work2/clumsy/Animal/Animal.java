package Animal;

public abstract class Animal {
     protected String name;
     protected int age;
     protected String gender;
     protected double price;
     //全参构造
     public Animal(String name,int age,String gender, double price){
          this.name=name;
          this.age=age;
          this.gender=gender;
          this.price=price;
     }

    public Animal() {}

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
    public abstract String toString();
    public abstract String toString(String mi);//输入相关
}
