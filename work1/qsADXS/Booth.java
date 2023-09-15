import java.util.Vector;

//我之前有写测试的代码，但是没有一起上传，现在补充上传
public class Main {

    public static void main(String[] args) {
        //创建5个摊位
        Booth[] booths = new Booth[5];
        for (int i = 0; i < 5; i++) {
            booths[i] = new Booth();
            booths[i].setId(i+1);
        }
        //进行set、get、toString的测试
        booths[0].setName("超级飞侠");
        booths[0].setClosed(false);
        booths[0].setonSale(100);
        System.out.println("name:"+booths[0].getName()+" ifClose:"+booths[0].getClosed()+" onSale:"+ booths[0].getOnSale());
        System.out.println(booths[0].toString());
        //测试关闭摊位函数
        booths[1].setClosed(true);
        booths[2].setClosed(false);
        booths[3].setClosed(true);
        Booth.closeBooths(booths);
        Booth.closeBooths(booths);
        //测试顾客购买的函数
        Booth a = new Booth(10001, "我", 20, true);
        System.out.println(a.toString());

        System.out.println();
        Booth.purchase(a,30);
        a.setClosed(false);

        Booth.purchase(a,30);
        Booth.purchase(a,10);
        //测试进货的函数
        System.out.println("摊位拥有西瓜数" + a.getOnSale());
        a.restock(100);
        System.out.println("摊位拥有西瓜数" + a.getOnSale());
        a.restock(-1);
        System.out.println("摊位拥有西瓜数" + a.getOnSale());
        a.restock(300);
        System.out.println("摊位拥有西瓜数" + a.getOnSale());
        System.out.println();
    }
}




public class Booth {

    private long id;
    private String name;
    private int onSale;//在github上给的是在售西瓜数int tota,当时感觉很奇怪，但还是跟着要求写
    private boolean isClosed;
    public Booth() {
    }

    public Booth(long id, String name, int onSale, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.onSale = onSale;
        this.isClosed = isClosed;
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

    public int getOnSale() {
        return onSale;
    }

    public void setonSale(int onSale) {
        this.onSale = onSale;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getClosed() {

        if(isClosed)
            return "摊位正在整改";
        else
            return "摊位未进行整改";

    }


    public String toString() {
        String str = "摊号：" + id + "\n摊主姓名: " + name + "\n在售西瓜数: " + onSale;

        if (isClosed)
            str += "\n摊位正在进行整改";
        else
            str += "\n摊位未整改";

        return str;

    }

    public static void purchase(Booth booth, int number) {

        if (number <= 0) {
            System.out.println("购买失败");
            System.out.println("购买的西瓜必须为正数");

        } else if (booth.isClosed) {
            System.out.println("购买失败");
            System.out.println("该西瓜摊正在整改");

        } else if (number > booth.onSale) {
            System.out.println("购买失败");
            System.out.println("购买的西瓜超过该摊位的在售西瓜");

        } else {
            booth.onSale -= number;
            System.out.println("成功购买" + number + "个西瓜");
        }
        System.out.println("摊位剩余西瓜数量：" + booth.onSale );
    }

    public void restock(int number){
        if(number > 200 || number <= 0)
            System.out.println("进货失败");
        else
            onSale += number;
    }

    public static void closeBooths (Booth[] booths){//这里没有想到不传入booth参数的方法
        Vector<Booth> closed= new Vector<>();
        for (Booth booth : booths) {
            if (!booth.isClosed) {
                closed.add(booth);
                booth.isClosed = true;
            }
        }
        if(closed.size() == 0)
            System.out.println("所有摊位都未修业");
        else {
            System.out.println("以下为已修业摊位：");
            for (Booth booth : closed) {
                System.out.println("\n" + booth.toString());
            }
        }

    }
}


