public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getTotal(){
        return total;
    }
    public boolean getIsClosed(){
        return isClosed;
    }
    public void setId(int newId){
        id=newId;
    }
    public void setName(String newName){
        name=newName;
    }
    public void setTotal(int newTotal){
        total=newTotal;
    }
    public void setClosed(boolean newCondition){
        isClosed=newCondition;
    }
    @Override
    public String toString(){
        return ("摊位摊号    : "+getId()+
                "\n摊主姓名    : "+getName()+
                "\n摊位在售西瓜数 : "+getTotal()+
                "\n是否处于休摊  : "+(getIsClosed()?"是":"否")+
                '\n');
    }
    public Booth(int theId,String theName,int theTotal,boolean theIsClosed){
        id=theId;
        name=theName;
        total=theTotal;
        isClosed=theIsClosed;
    }
    public static void purchase(Booth booth,int purchaseNum){
        if(purchaseNum<0){
            System.out.println("购买失败，购买的西瓜数需要大于零");
            return;
        }
        if(booth.getIsClosed()){
            System.out.println("购买失败，"+booth.getName()+"摊主正在休摊整改中，请换一个摊位购买");
            return;
        }
        if(purchaseNum> booth.getTotal()){
            System.out.println("购买失败，该摊位剩余西瓜"+booth.getTotal()+"个，无法满足购买需求");
            return;
        }
        booth.setTotal(booth.getTotal()-purchaseNum);
        System.out.println("购买成功！");
    }
    public void restock(int amount){
        if(amount>200){
            System.out.println("进货失败，单次进货量应不大于200");
            return;
        }
        if(amount<0){
            System.out.println("进货失败，进货量应大于等于0");
        }
        setTotal(total+amount);
        System.out.println("进货成功！");
    }
    public static void closeBooths(Booth []booths){
        for (Booth booth : booths) {
            if (booth.isClosed) {
                System.out.println(booth.toString());
            } else {
                booth.setClosed(true);
            }
        }
    }
}
