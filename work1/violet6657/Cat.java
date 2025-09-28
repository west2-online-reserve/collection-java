public class Cat extends Animal {
    static final double CAT_PURCHASE_PRICE=200;


    public Cat() {
    }

    public Cat (String name,int age,int weight,String gender,double price){
        super(name,age,gender, price, weight);
    }
    @Override
    public String toString() {
        return ("该猫咪名为"+getName()+"  年龄:"+getAge()
                +"  性别为"+getGender()+"  体重为"+getWeight()+"  价格为"+getPrice());
    }
}
