import java.util.EnumSet;
import java.util.MissingFormatArgumentException;

public class Booth {       //私有变量
    private long id ;
    private String name ;
    private int tota ;
    private boolean isClosed;


    public long getId() {
        return id;
    }     //上述变量对应的 get 和 set 方法

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

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean closed) {
        isClosed = closed;
    }

    @Override      //一个重写的 toString()方法
    public  String   toString() {
        return ( "欢迎来到"+name+"的西瓜摊！"+ " "+
                "Id:"+id+" "+
                "在售的西瓜数:"+tota+" "+
                "店铺营业情况:"+isClosed);
    }
    //一个接受摊号(long)、摊主姓名(name) 、// 在售西瓜数(int)、是否休摊整改 (boolean)作为参数的构造方法
    public Booth(long id, String name, int tota, boolean isClosed){
        this.id = id;
        this.name = name;
        this.tota = tota;
        this.isClosed = isClosed;
    }
    public static void purchase(Booth business,int nums ){      //静态方法 purchase
        if (nums<0){
            System.out.println("购买的西瓜数目不可为负，请重新输入！");
        } else if (nums > business.tota) {
            System.out.println("你要的西瓜太多啦，请重新输入！");
        } else  if (business.isClosed){
            System.out.println("摊主已收摊！");
        } else {
            business.setTota(business.getTota()-nums);
            System.out.println("购买成功，剩余"+business.getTota()+"个西瓜！");
        }



    }
    public void restock (int stock){                       //一个实例方法 restock
        if (stock<0 || stock>200 ){
            System.out.println("进货数目不可为负数或是大于200！");
        }else{
            setTota(getTota()+ stock) ;
        }

    }

    public static void closeBooths(Booth[] Booths) {
        for( int i=0; i< Booths.length; i++ ) {
            if ( Booths[i].getIsClosed() ) {
                System.out.println(Booths[i]);
            } else {
                Booths[i].setIsClosed(true);}
        }
    }










}

