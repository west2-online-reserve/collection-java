public class Test {
    public static void main(String[] args) {
        Booth booth=new Booth(222200231,"youjunchao",100,false);
        System.out.printf(booth.toString());
        booth.restock(-1);
        booth.restock(201);
        booth.restock(100);
        Booth.purchase(booth,300);
        Booth.purchase(booth,-1);
        booth.setIsClosed(true);
        Booth.purchase(booth,100);
        booth.setIsClosed(false);
        Booth.purchase(booth,100);
    }
}