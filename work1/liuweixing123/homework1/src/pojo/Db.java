package pojo;

public class Db {
    private static Watermelon w1=new Watermelon(1001,"牛牛",30,true);
    private static Watermelon w2=new Watermelon(1002,"兔兔",20,false);
    private static Watermelon w3=new Watermelon(1003,"龙龙",40,false);

    public static  Watermelon []arry={w1,w2,w3};

    public Watermelon[] getArry() {
        return arry;
    }

    public void setArry(Watermelon[] arry) {
        this.arry = arry;
    }

    public Db() {
    }
}
