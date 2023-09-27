package hh;

public class Booth {

    long id;String name;int total;boolean isClosed;

    public Booth(long id,String name,int total,boolean isClosed) {

        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        System.out.println();
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }


    public void restock(int i)
    {
        if(i>=0&&i<=200)
        {
            this.total+=i;
        }
            else {

            System.out.println("进货失败");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}



