package Wangqiangstore;


class Booth{
    private long id;
    private String name;
    private  int total;
    private boolean isclosed;

    public void setName(String name ){
        this.name=name;
    }
    public void setId(long Id){
        this.id=Id;
    }
    public void setTotal(int total){
        this.total=total;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public int getTotal() {
        return total;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsclosed() {
        return isclosed;
    }
    public Booth(long id,String name,int total,boolean isClose){

    }
    @Override
    public String toString() {
        return "id:"+ getId() +"    name:"+ getName()  +"   total:"+  getTotal() +"    isclosed: "+ getIsclosed()  ;
    }

    public static void purchase(Booth name, int n){
        if (n<=0){
            System.out.println("购买数值错误");
        } else if (n>name.total) {
            System.out.println("购买数量大于在售西瓜数");
        } else if (name.isclosed==true) {
            System.out.println("该西瓜摊位在休摊整改");
        }else{
            System.out.println("恭喜你成功购买了 " + n +"个西瓜");
        }
    }
    public void restock(int num){
        if(num>200){
            System.out.println("进货数量过多导致进货失败");
        }else {
            System.out.println("成功进货 "+ num+ "西瓜");
        }
    }
    public static void closeBooths(Booth[] booths){
        int len= booths.length;
        for (int i=0;i<len;i++){
            if (booths[i].getIsclosed()==false){
                booths[i].setIsclosed(true);
            }
            System.out.println(booths[i]);
            }

        }

    }


