package java_work2;

/**
 * Parrot类
 * 继承于抽象Animal类
 * 成员变量isAbleToTalk记录是否会说话
 *
 * @author formrc
 * @date 2023/10/29
 */
class Parrot extends Animal {
     protected boolean isAbleToTalk;
     protected static final double defaultPrice = 150.0;
     public Parrot(String name, int age, String gender, boolean isAbleToTalk) {
         super(name, age, gender, defaultPrice);
         this.isAbleToTalk = isAbleToTalk;
     }
     @Override
     public String toString() {
         return  "Parrot: " + name + "\n" +
                 "Age: " + age + "\n" +
                 "Gender: " + gender + "\n" +
                 "Ability TO Talk: " + isAbleToTalk + "\n" +
                 "Price: " + price + "\n" ;
     }
}
