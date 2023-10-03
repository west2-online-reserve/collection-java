public class Test {
    public static void main(String[] args) {
//        Booth booth=new Booth();
//        Booth booth=new Booth(123,"pany",100,false);
//        System.out.println(booth.toString());
//        Booth.purchase(booth,100);
//        Booth.purchase(booth,-10);
//        Booth.purchase(booth,101);
//        Booth.purchase(booth,50);
//        booth.restock(100);
//        booth.restock(300);
//        booth.restock(-100);
//        System.out.println(booth.toString());
        Booth booth1=new Booth(1,"z3",100,false);
        Booth booth2=new Booth(2,"l4",100,false);
        Booth booth3=new Booth(3,"w5",100,false);
        Booth.closeBooths(booth1,booth2,booth3);
    }
}
