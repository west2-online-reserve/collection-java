import java.util.Vector;

public class Booth {

    private long id;
    private String name;
    private int toda;
    private boolean isClosed;

    public Booth() {


    }

    public Booth(long id, String name, int toda, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.toda = toda;
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

    public int getToda() {
        return toda;
    }

    public void setToda(int toda) {
        this.toda = toda;
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
        String str = "摊号：" + id + "\n摊主姓名: " + name + "\n在售西瓜数: " + toda;

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

        } else if (number > booth.toda) {
            System.out.println("购买失败");
            System.out.println("购买的西瓜超过该摊位的在售西瓜");

        } else {
            booth.toda -= number;
            System.out.println("成功购买" + number + "个西瓜");
        }
        System.out.println("摊位剩余西瓜数量：" + booth.toda );
    }

    public void restock(int number){
        if(number > 200)
            System.out.println("进货失败");
        else
            toda += number;
    }

    public static void closeBooths (Booth[] booths){
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


