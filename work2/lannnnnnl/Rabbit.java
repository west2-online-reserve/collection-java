package westwork2;

//兔子类
public class Rabbit extends Animal {
 Rabbit(String name, int age, char gender) {
     super(name, age, gender, 50.0); // 假设兔子的价格为50元
 }

 @Override
 public String toString() {
     return "兔子: " + this.name;
 }
}
