public class Cat extends Animal {
    public Cat(char sex,int age,String name){
        super(200,sex,age,name);
    }
    @Override
    public String toString() {
        return "Cat:"+"\n"+"name:"+name+"\n"+"age:"+age+"\n"+"sex"+sex+"\n"+"price:200";
    }
}
