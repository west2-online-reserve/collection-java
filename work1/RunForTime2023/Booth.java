public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean IsClosed;

    void setId(long id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setTotal(int total) {
        this.total = total;
    }

    void setClosed(boolean isClosed) {
        IsClosed = isClosed;
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
        String output = "ID:\t\t\t\t\t\t" + Long.toString(id) + "\nName:\t\t\t\t\t" + name + "\nNumber of watermelons:\t" + Integer.toString(total) + "\nIsClosed:\t\t\t\t";
        if (IsClosed) {
            output = output + "Yes\n";
        } else {
            output = output + "No\n";
        }
        return output;
    }

    Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        IsClosed = isClosed;
    }

    public static void purchase(Booth stall, int num) {
        if (num < 0 || stall.IsClosed || stall.total < num){
            System.out.println("Purchase failure!");
        }
        else {
            stall.total -= num;
            System.out.println("Purchased successfully!");
        }
    }

    public void restock(int num) {
        if (num < 0 || num > 200) {
            System.out.println("Fail to restock!");
        }
        else {
            total += num;
            System.out.println("Restocked successfully!");
        }
    }

    public static void closeBooths(Booth[] stall) {
        for (Booth x : stall) {
            if (x.IsClosed) {
                System.out.println(x.toString());
            }
            else {
                x.IsClosed = true;
            }
        }
    }
}