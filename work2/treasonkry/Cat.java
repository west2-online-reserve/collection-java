public class Cat extends Animal {

    @Override
    public double getPrice() {
        return 200;

    }



    @Override
    public String toString() {
        return "Cat:"+"\n"+"name:"+name+"\n"+"age:"+age+"\n"+"sex"+sex+"\n"+"price:200";
    }
}
