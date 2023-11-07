package Xuxiiii;
public class Cat extends Animal{
    public Cat(String name, int age, String sex, double price) {
        super(name, age, sex, price);
        this.setPrice(200);
    }


    @Override
    public String toString() {

        return "A cat "+'\n'+
                " name is "+name+
                " age is "+age+
                " sex is "+sex+
                " price is 200 ";
    }

}





