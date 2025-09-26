public class Chicken extends Animal {
    static final double CHICKEN_PURCHASE_PRICE=6657;
    public Chicken() {}
    public Chicken (String name,int age,int weight,String gender,double price){
        super(name,age,gender,price, weight);
    }
    @Override
    public String toString() {
        return ("该小鸡名为"+getName()+"  年龄:  "+getAge()
                +"  性别为"+getGender()+"  体重为"+getWeight()+"  价格为"+getPrice());
    }
}
