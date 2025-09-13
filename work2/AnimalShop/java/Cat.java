package collection

public class Cat extends Animal {
    @Override
    public String toString() {
        return "Cat name:"+name+" age:"+age
                +" sex:"+sex+" 剩余数量： "+amount+" 价格：" +price;
    }


    public Cat(String name,int age,char sex,int amount){
        super(name,age,sex,200);
    }
}
