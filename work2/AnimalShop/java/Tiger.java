package collection

public class Tiger extends Animal {
    @Override
    public String toString() {
        return "Tiger name:"+name+" age:"+age
                +" sex:"+sex+" 剩余数量： "+amount+" 价格：" +price;
    }
    public Tiger(String name,int age,char sex,int amount){
        super(name,age,sex,400);
    }
}
