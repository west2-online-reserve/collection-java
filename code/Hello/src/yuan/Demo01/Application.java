package yuan.Demo01;

public class Application {
    public static void main(String[] args) {
        A a=new A();
        B b=new B();
        B b1=new A();
        a.test();
        b.test();
        b1.test();
    }
}
