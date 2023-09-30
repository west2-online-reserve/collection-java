package Homework;

public class Watermelon {
    public static void main(String[] args) {


    }


    long id;
    int total;
    String name;
    boolean isClosed;
    //构造方法
    public Watermelon(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }
    //get和set方法
    //---------------------------------------------------------------
    public long getId(){
        return  this.id;
    }
    public void setId(long id1){
        this.id = id1;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int tt){
        this.total = tt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //---------------------------------------------------------------------------------------
    //购买方法
    public static void purchase(Watermelon id,int buy){


    }

    }


