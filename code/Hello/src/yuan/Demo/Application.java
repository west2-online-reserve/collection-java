package yuan.Demo;

public class Application {
    public static void test(int a) throws MyException {
        if (a>10){
            throw new MyException(a);
        }
        System.out.println("OK");
    }

    public static void main(String[] args) {

        try {
            test(10);
        } catch (MyException e) {
            System.out.println("MyException=>"+e);
        } finally{
            System.out.println("finally");
        }
    }
}
