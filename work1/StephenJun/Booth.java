import java.util.Scanner;

public class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    public Booth() {
    }

    public Booth(long id, String name, int tota, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.tota = tota;
        this.isClosed = isClosed;
    }

    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return tota
     */
    public int getTota() {
        return tota;
    }

    /**
     * 设置
     * @param tota
     */
    public void setTota(int tota) {
        this.tota = tota;
    }

    /**
     * 获取
     * @return isClosed
     */
    public boolean isIsClosed() {
        return isClosed;
    }

    /**
     * 设置
     * @param isClosed
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    //购买西瓜perchase方法
    public static void purchase(Booth booth,int num){
        if (booth.isClosed == false){
                    if (num > booth.tota){
                        System.out.println("摊主感谢你想买这么多，但是摊主没这么多西瓜");
                        System.out.println("现在只有这么多西瓜了" + booth.tota);;
                    }else {
                        booth.tota -= num;
                        System.out.println("*******成功卖出" + num +"个西瓜*******");
                        System.out.println("*******您还有" + booth.tota +"个西瓜*******");
                    }
        }else {
            System.out.println("这个西瓜摊已经关门了");
        }
    }

    //进货方法restock
    public void restock(int num){
        if (num > 200){
            System.out.println("买这么多，可卖不完哦（单次购买量不能超过200）");
        }else {
            this.tota += num;
            System.out.println("*******成功进货" + num +"个西瓜*******");
            System.out.println("*******现在一共有"+ this.tota +"个西瓜*******");
        }
    }

    //整改摊位状态closeBooths
    public static void closeBooths(Booth[] booths){
        int len = booths.length;
        for (int i = 0; i < len; i++) {
            if (booths[i].isIsClosed() == true){
                System.out.println(booths[i].toString());
            }
            if (booths[i].isIsClosed() == false){
                booths[i].setIsClosed(true);
            }
        }
    }

    public String toString() {
        return "摊号: " + id
                + ",摊主名字: " + name
                + ",在售西瓜数: " + tota
                + ",是否修摊整改: " + isClosed;
    }
}
