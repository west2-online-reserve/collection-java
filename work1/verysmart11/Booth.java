public class Booth {
    private long id;
    private String name;
    private  int total;
    private  boolean isClosed;

    public Booth(){
        id=0;
        name="q";
        total=1;
        isClosed=false;
    }

    public Booth(long id,String name,int total,boolean isClosed){
        this.id=id;
        this.isClosed=isClosed;
        this.total=total;
        this.name=name;
    }

    public int getTotal(){
        return  total;
    }
    public  long getId(){
        return  id;
    }
    public String getName(){
        return name;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String toString(){

        String a = "all information of the watermelon stand ：" + "\n" +
                "id of stand : " + id + "\n" +
                "name of the master: " + name + "\n" +
                "number of the watermelon on sale : " + total + "\n" +
                "if closed : " + isClosed;
        return a;

    }

    public static void  purchase(Booth q,int a){
        if(a>0&&a< q.getTotal()&&q.isClosed()==false){
            q.setTotal(q.getTotal()-a);
            System.out.println("trade done");
        }
        else System.out.println("trade failed");

    }
    public void restock(int b){
        if(b>0&&b<=200){
            this.total+=b;
            System.out.println("restock done");
        }
        else System.out.println("restock failed");
    }

    public static void closeBooth(Booth[] booths){
        for (int i=0;i<booths.length;i++){
            if(booths[i].isClosed==true) {
                System.out.println(booths[i].toString());
            }
            else booths[i].isClosed=true;

        }
    }




}
