package yuan.Demo;

public class Student extends Person{
    protected String name="B";
    public void test1(String name){
        System.out.println(name);
        System.out.println(this.name);
        System.out.println(super.name);
    }
    public void test2(){
        print();
        this.print();
        super.print();
    }
    public void print(){
        System.out.println("Student");
    }
}