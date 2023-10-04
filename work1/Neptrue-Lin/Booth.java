public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth(long id, String name, int total, boolean isClosed) {
        this.setId(id);
        this.setName(name);
        this.setTotal(total);
        this.setClosed(isClosed);
    }

    public static void purchase(Booth booth, int count) {
        if(booth.getClosed()){
            System.out.println("Purchase failed: Booth is closed!");
            return;
        }

        if (count < 0 || count > booth.getTotal()) {
            System.out.println("Purchase failed: Count is out of bound!");
        } else {
            booth.total -= count;
            System.out.println("Purchase successfully!");
        }
    }

    public void restock(int count) {
        if (count < 0 || count > 200) {
            System.out.println("Restock failed: Count is out of bound!");
        } else {
            this.total += count;
        }
    }

    public static void closeBooths(Booth[] booths) {
        for (int i = 0; i < booths.length; i++) {
            if (!booths[i].isClosed) {
                booths[i].isClosed = true;
                System.out.println(booths[i].toString());
            }
        }
    }

    public String toString() {
        return String.join("; ",
                "Id: " + this.getId(),
                "Name: "+this.getName(),
                "Total: "+this.getTotal(),
                "IsClosed: "+this.getClosed());
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean getClosed() {
        return this.isClosed;
    }

}
