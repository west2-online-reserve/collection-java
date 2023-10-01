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
        //交易是否成功

    }
    public static   String purchase(Booth b,int buy){
        int x=b.tota;
        if(b.isClosed()){
            return "isClosed";
        }else {
            if(buy<0){
                return "交易失败-购买数量小于0";
            }else{
                if(x<buy){
                    return "交易失败 该摊库存不足";
                }else{
                    return "交易成功";
                }
            }
        }
    }
    public String restock(Booth b,int in){
        if (in > 200||in<0) {
            return "进货失败";
        }else {
           System.out.println(b.getName()+" total: " b.setTota(b.getTota()+in);
            return "进货成功";
        }
    }
    public static void closeBooths(Booth[]booths){
        //现打印已在休业的摊位
        for(int i=0;i<booths.length;i++){
            if(booths[i].isClosed==true){
                System.out.println(booths[i].toString());
            }
            //再将未休业的改为休业
            if(booths[i].isClosed!=true){
                booths[i].setClosed(true);
            }
        }


    }


}
