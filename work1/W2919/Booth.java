public class Booth {
    private String name;
    private long id;
    private int total;
    private boolean isClosed;


    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }


    public long getId() {return id;}
    public void setId(long id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }


    public int getTotal()
    {
        return total;
    }
    public void setTotal(int total)
    {
        this.total = total;
    }


    public boolean getisClosed()
    {
        return isClosed;
    }
    public void setisClosed(boolean isclosed)
    {
        this.isClosed = isclosed;
    }


    public String toString() {
        return ("the booth's ID is " + getId() + "\nthe booth's name is " + getName() + "\nthe numbers of watermelon is "
                + getTotal() + "\nBooth's status: " + getisClosed());
    }

    public static void purchase(Booth merchant,int buyNum) {
        if( buyNum<=0 || buyNum> merchant.getTotal() || merchant.getisClosed() ) {
            System.out.println("null purchase");
        }
        else {
            merchant.setTotal(merchant.getTotal() - buyNum);
            System.out.println("success purchase" + "the rest：" + merchant.getTotal());
        }
    }

    public void restock(Booth merchant,int restockNum) {
        if ( restockNum<=200 && restockNum>=0 ) {
            merchant.setTotal(merchant.getTotal()+restockNum);
            System.out.println("success restock");
        }
        else {
            System.out.println("null restock");
        }
    }

    public static void closeBooths(Booth[] Booths) {
        for( int i=0; i< Booths.length; i++ ) {
            if ( Booths[i].getisClosed() ) {System.out.println(Booths[i]);}
            else {Booths[i].setisClosed(true);}
        }
    }

}