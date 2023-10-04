package watermelon;

public class boothText {
    public static void main(String[] args){
        Booth booth = new Booth(888, "西瓜摊",50, false );
        System.out.println(booth);

        Booth.purchase(booth,20);
        //Booth.restock(150);
        System.out.println(booth);

        watermelon.Booth.purchase(booth,80);
        //Booth.restock(300);
        System.out.println(booth);

        booth = new Booth(888, "西瓜摊",50, true );
        System.out.println(booth);

        booth.restock(150);
        System.out.println();


    }
}
