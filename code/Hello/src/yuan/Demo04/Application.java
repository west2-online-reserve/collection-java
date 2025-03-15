package yuan.Demo04;

public class Application {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.new Inner();
        Outer.Inner inner = outer.new Inner();
    }
}
