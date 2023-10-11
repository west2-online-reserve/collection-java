public class Booth
{
    private long id;            //摊号
    private String name;        //摊主姓名
    private int total;          //在售西瓜数
    private boolean isClosed;   //是否休摊整改

    public Booth(long id, String name, int total, boolean isClosed)//构造函数
    {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    public long getId()//得到摊号
    {
        return id;
    }

    public void setId(long id)//设置摊号
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public boolean isClosed()
    {
        return isClosed;
    }

    public void setClosed(boolean closed)
    {
        this.isClosed = closed;
    }

    public String toString()
    {
        return "西瓜摊的" + "摊号为：" + id + "，摊主姓名为：'" + name + '\'' +
                "，在售西瓜数为：" + total + "，是否休摊整改：" + isClosed + '}';
    }

    public void restock(int amount)
    {
        if (amount <= 0 || amount > 200)
        {
            System.out.println("进货失败：单次进货量必须在1~200之间。");
            return;
        }
        total += amount;
        System.out.println("进货成功，当前库存量为：" + total);
    }

    public static void purchase(Booth booth, int purchaseAmount)
    {
        if (purchaseAmount <= 0)
        {
            System.out.println("购买失败：购买数量必须为正数！");
            return;
        }
        if (booth.isClosed())
        {
            System.out.println("购买失败：摊位已经休业整改！");
            return;
        }
        if (purchaseAmount > booth.getTotal())
        {
            System.out.println("购买失败：购买数量超过在售西瓜数！");
            return;
        }
        booth.setTotal(booth.getTotal() - purchaseAmount);
        System.out.println("购买成功，当前库存量为：" + booth.getTotal());
    }

    public static void closeBooths(Booth[] booths)
    {
        for (Booth booth : booths)
        {
            if (!booth.isClosed())
            {
                booth.setClosed(true);
                System.out.println(booth.getName() + "的摊位原未休业整改，现已休业整改");
            }
        }
    }
    
}