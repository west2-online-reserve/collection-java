package yuan.Demo04;

public class Outer {
    private int id;
    public void out(){
        System.out.println("A");
    }
    public class Inner {
        public void in(){
            System.out.println("B");
        }
    }
}
