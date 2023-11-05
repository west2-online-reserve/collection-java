package westwork2;

//中华田园犬类
public class ChineseRuralDog extends Animal {
 boolean isVaccineInjected;

 ChineseRuralDog(String name, int age, char gender, boolean isVaccineInjected) {
     super(name, age, gender, 100.0); // 价格固定为100元
     this.isVaccineInjected = isVaccineInjected;
 }

 @Override
 public String toString() {
     return "中华田园犬: " + this.name;
 }
}
