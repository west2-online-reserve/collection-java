package animalShop;

public class cat extends Animal {
    public cat(String name, int age, String sex) {
        super(name,age,sex,200.0);
    }
    @Override
    public String toString() {
        return "猫猫" +
                "名字："+name+"\n"+
                "年龄："+age+"\n"+
                "性别："+sex+"\n"+
                "价格："+price+"\n";
    }
}
