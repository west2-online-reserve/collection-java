package westwork2;

//猫类
class Cat extends Animal {
 Cat(String name, int age, char gender) {
     super(name, age, gender, 200.0); // 价格固定为200元
 }

 @Override
 public String toString() {
     return "猫猫: " + this.name;
 }
}
