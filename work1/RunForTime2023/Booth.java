public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean IsClosed;
    void setId(long ID) {
        id=ID;
    }
    void setName(String NAME) {
        name=NAME;
    }
    void setTotal(int TOTAL) {
        total=TOTAL;
    }
    void setClosed(boolean ISCLOSED) {
        IsClosed=ISCLOSED;
    }
    long getId() {
        return id;
    }
    String getName() {
        return name;
    }
    int getTotal() {
        return total;
    }
    public String toString() {
        String Output="ID:\t\t\t\t\t\t"+Long.toString(id)+"\nName:\t\t\t\t\t"+name+"\nNumber of watermelons:\t"+Integer.toString(total)+"\nIsClosed:\t\t\t\t";
        if(IsClosed) Output=Output+"Yes\n";
        else Output=Output+"No\n";
        return Output;
    }
    Booth(long ID,String NAME,int TOTAL,boolean iScLOSED) {
        id=ID;
        name=NAME;
        total=TOTAL;
        IsClosed=iScLOSED;
    }
    public static void purchase(Booth stall,int num) {
        if(num<0||stall.IsClosed||stall.total<num)
            System.out.println("Purchase failure!");
        else {
            stall.total-=num;
            System.out.println("Purchased successfully!");
        }
    }
    public void restock(int num) {
        if(num<0||num>200) System.out.println("Fail to restock!");
        else {
            total+=num;
            System.out.println("Restocked successfully!");
        }
    }
    public static void closeBooths(Booth[] stall) {
        for(Booth x:stall) {
            if(x.IsClosed) System.out.println(x.toString());
            else x.IsClosed = true;
        }
    }
}