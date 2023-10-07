package watermelon;


public class Test {
    public static void main(String[] args){

        Booth booth1=new Booth(1,"Lin",100,false);
        Booth booth2=new Booth(2,"Chen",150,true);
        Booth booth3=new Booth(3,"Lee",30,false);
        Booth booth4=new Booth(4,"Su",70,false);
        watermelon.Booth[] booths = {booth1,booth2,booth3,booth4};
        booth1.toString();
        //摊主进西瓜

        booth1.restock(100);
        booth1.restock(150);
        booth1.restock(200);
        booth1.restock(500);

        //商家购西瓜
        booth1.purchase(booth1,100);

        watermelon.Booth.closeBooths(booths);
    }
}



