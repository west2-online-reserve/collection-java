package work1;

public class Booth {

    private long id;
    private String name;
    private int total;
    private boolean isClosed;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean ifClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    //输出信息
    @Override
    public String toString() {
        String input = "摊号:" + id + "\n摊主姓名:" + name + "\n在售西瓜数:" + total + "是否休摊整改:" + isClosed;
        if (isClosed == true) {
            System.out.println("摊号:" + id + "\n摊主姓名:" + name + "\n在售西瓜数:" + total + "是否休摊整改:是");
        } else {
            System.out.println("摊号:" + id + "\n摊主姓名:" + name + "\n在售西瓜数:" + total + "是否休摊整改:否");
        }
        return input;
    }


    //构造方法
    public Booth(long id, String name, int total, boolean isClose) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClose;
    }

    public static void purchase(Booth booth, int num) {
        if (booth.getTotal() >= num && booth.ifClosed() != true && num >= 0) {
            booth.setTotal(booth.getTotal() - num);
            System.out.println("交易成功！\n");
        } else {
            System.out.print("交易失败！");
            if (booth.isClosed == true) {
                System.out.print("休摊整改中\n");
            } else if (num < 0) {
                System.out.print("不能买入负数\n");
            } else if (booth.getTotal() < num) {
                System.out.print("西瓜数量不足\n");
            }
        }
    }

    public void restock(int num) {
        if (num <= 200 && num >= 0) {
            total += num;
            System.out.printf("进货成功！现在有%d个西瓜\n", num);
        } else {
            System.out.println("进货失败！数量不合法\n");
        }
    }

    public static void closeBooths(Booth[] booths) {
        boolean titlePrinted = false;
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].ifClosed() == true) {
                if (titlePrinted == false) {
                    System.out.println("以下为休摊整改的摊位信息：\n");
                    titlePrinted = true;
                }
                System.out.println(booths[i].toString());
            } else {
                booths[i].setClosed(true);
            }
        }
    }




}
