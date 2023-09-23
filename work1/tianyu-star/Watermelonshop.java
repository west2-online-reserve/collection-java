import java.util.*;
public class Watermelonshop{
    public static void main(String[] args){
        Booth watermelon=new Booth(9527,"xqx",0,false);

        Scanner scanner=new Scanner(System.in);
        System.out.print("please type the total:");
        int t=scanner.nextInt();
        System.out.print("please type the isClosed:");
        boolean i=scanner.nextBoolean();
        System.out.print("please type the buying:");
        int b=scanner.nextInt();
        System.out.printf(Booth.purchase(watermelon,b));
        System.out.print("please type the getting:");
        int g=scanner.nextInt();
        System.out.printf(watermelon.restock(g));
        watermelon.setTotal(t);
        watermelon.setIsClosed(i);
        Booth.purchase(watermelon,b);
        watermelon.restock(g);
        System.out.printf(watermelon.toString());


    }
}

class Booth{
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth(long id,String name,int total,boolean isClosed) {
        this.id=id;
        this.name=name;
        this.isClosed=isClosed;
        this.total=total;
    }
    public void setName(String name) {

        this.name=name;
    }
    public void setId(long id) {

        this.id=id;
    }
    public void setTotal(int total) {

        this.total=total;
    }
    public void setIsClosed(boolean isClosed) {

        this.isClosed=isClosed;
    }
    public String getName() {

        return this.name;
    }
    public long getId() {

        return this.id;
    }
    public int getTotal() {

        return this.total;
    }
    public boolean getIsClosed() {

        return this.isClosed;
    }
    public String toString() {

        return "Name="+this.name+" id="+this.id+" Total="+this.total+" IsClosed="+this.isClosed;
    }

    public static String purchase(Booth watermelon,int buying) {
        if (buying<=0 || watermelon.isClosed==true || buying>watermelon.total) {
            return "what you buy is wrong.";
        } else {
            watermelon.total=watermelon.total-buying;
            return "thank you for your buying.";
        }

    }
    public String restock(int getting){
        if (getting<=200 && getting>=0) {
            this.total=this.total+getting;
            return "thank you for your getting.";
        } else {
            return "what you get is wrong.";
        }

    }
    public static void closeBooths(Booth[] booths) {
        for (Booth i : booths) {
            if (i.isClosed==false) {
                i.isClosed=true;
            }
            System.out.printf(i.toString());
        }

    }

}



