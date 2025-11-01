import java.util.Scanner;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    Scanner sc = new Scanner(System.in);

    public Animal() {
    }

    
    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public double getPrice() {
        return price;
    }

    public abstract String toString();

    public void inputPet() {
        System.out.println("名字?");
        this.name = sc.next();
        System.out.println("年龄?");
        this.age = sc.nextInt();
        System.out.println("性别?");
        this.gender = sc.next();
    }

}
