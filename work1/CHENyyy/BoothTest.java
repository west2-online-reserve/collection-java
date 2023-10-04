package work1;

public class BoothTest {
    public static void main(String[] args){
        Booth[] booths = new Booth[3];
        for(int i = 0; i < booths.length; i++){
            booths[i] = new Booth();
            booths[i].setId(i+1);
        }
        //get set toString() test
        booths[0].setName("HappyHappyHappy~");
        booths[0].setIsClosed(false);
        booths[0].setTota(187);
        booths[1].setName("Cloud");
        booths[1].setIsClosed(false);
        booths[1].setTota(168);
        booths[2].setName("rowrowrowyouboat~");
        booths[2].setIsClosed(true);
        booths[2].setTota(10);
        System.out.println("Id: " + booths[0].getId() + "\nname:" + booths[0].getName() + "\nisClosed: " + booths[0].getIsClosed() + "\nonSale: " + booths[0].getTota());
        System.out.println(booths[0].toString());
        //Customer purchase
        Booth.purchase(booths[0], 200);
        System.out.println(booths[0].getTota());
        Booth.purchase(booths[1], 100);
        System.out.println(booths[1].getTota());
        Booth.purchase(booths[2], 10);
        System.out.println(booths[2].getTota());
        //Merchant purchase
        booths[0].restock(2);
        System.out.println(booths[0].getTota());
        booths[1].restock(200);
        System.out.println(booths[1].getTota());
        booths[2].restock(-1);
        System.out.println(booths[2].getTota());
        //About trimming
        for(int i = 0; i < booths.length; i++){
            System.out.println(booths[i].toString());
        }
        System.out.println("***************************");
        Booth.closeBooths(booths);
        System.out.println("***************************");
        for(int i = 0; i < booths.length; i++){
            System.out.println(booths[i].toString());
        }
    }
}
