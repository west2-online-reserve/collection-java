public class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;


    public Booth() {
    }
    public Booth(long id, String name, int tota, boolean isClosed) {
        this.id=id;
        this.name=name;
        this.tota=tota;
        this.isClosed=isClosed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTota() {
        return tota;
    }

    public void setTota(int tota) {
        this.tota = tota;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
    public String toString(){
        String details="Number of id: "+id;
        details+=" Name: "+name;
        details+=" Tota: "+tota;
        details+=" Isclosed: "+isClosed;
        return details;
        //交易成功与否

    }
    public static void purchase(Booth b, int purchase){


            if(b.isClosed()){
            return "isClosed";
        }else {
            if(purchase<0){
                return "交易失败，购买数量小于0";
            }else{
                if(b.tota<purchase){
                    return "交易失败 西瓜不够";
                }else{
                    b.tota=b.tota-purchase;
                    return "交易成功";
                }
            }
        }
    }
    public String restock(Booth b, int in){
        if (in > 200||in<0) {
            return "进货da失败";
        }else {
            b.tota=b.tota+in;
            System.out.println(b.getName()+" total: "+b.getTota());
            return "进货da成功";
        }
    }
    public static void closeBooths(Booth[]booths){
        for(int i=0;i<booths.length;i++){
            if(booths[i].isClosed==true){
                System.out.println(booths[i].toString());
            }
            else{
                booths[i].setClosed(true);
            }
        }


    }


}
