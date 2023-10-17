import java.util.Scanner;

import static java.lang.Math.PI;   //静态导入包

public class Booth {
    static { //静态代码块，只执行一次，不论new了几个object 运行顺序1st
        System.out.println("欢迎来到充满西瓜摊的世界！");
        System.out.println("1.查看所有摊位信息");
        System.out.println("2.买西瓜");
        System.out.println("3.进货");
        System.out.println("4.整改");
    }

    /*
    {   //匿名代码块 运行顺序2nd
        System.out.println("一个新的西瓜摊诞生了！");
    }
    //构造方法：运行顺序3rd
    */  //learn
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    private long id;    //摊号
    private String name;    //摊主姓名
    private int total;    //在售西瓜数
    private boolean isClosed;    //是否休摊整改
    static Booth booth1 = new Booth(4001234567L, "YC", 100, false);
    static Booth booth2 = new Booth(1145141919L, "WT", 101, false);
    static Booth booth3 = new Booth(9999999999L, "CYX", 99, false);
    static Booth[] allBooths = {booth1, booth2, booth3};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入序号，执行对应的操作：");
        while (true) {

            int th = scanner.nextInt();
            action(th);
            System.out.println("请输入序号，执行对应的操作：");
        }


        //scanner.close();
        /*
        purchase(booth1, 50);
        booth1.restock(100);

        Booth[] badBooths = {booth1, booth3};
        closeBooths(badBooths);
        */
    }


    public static int inputInt(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        int num = scanner.nextInt();

        return num;
    }

    public static long inputLong(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        long num = scanner.nextLong();

        return num;
    }

    public static void action(int th) {
        long tmp;
        boolean flag;
        switch (th) {
            case 1://查看所有摊位信息
                for (Booth booth : allBooths) {
                    System.out.println(booth.toString());
                }

                break;
            case 2://买西瓜
                tmp = inputLong("请输入摊位ID：");
                flag = false;
                for (Booth booth : allBooths) {
                    if (booth.id == tmp) {
                        flag = true;
                        purchase(booth, inputInt("请输入购买数量："));
                    }
                }
                if (!flag) {
                    System.out.println("购买失败，原因：不存在该ID");
                }
                break;
            case 3://进货

                tmp = inputLong("请输入摊位ID：");
                flag = false;
                for (Booth booth : allBooths) {
                    if (booth.id == tmp) {
                        flag = true;
                        booth.restock(inputInt("请输入进货数量："));
                    }
                }
                if (!flag) {
                    System.out.println("进货失败，原因：不存在该ID");
                }

                break;
            case 4://集体整改

                closeBooths(allBooths);

                break;
            default:
                System.out.println("不被定义的序号！");
                System.out.println("西瓜有多圆？有" + PI + "那么圆。");
        }


    }

    @Override
    public String toString() {
        return "Booth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total=" + total +
                ", isClosed=" + isClosed +
                '}';
    }

    public static void closeBooths(Booth[] booths) {
        System.out.println("已在休业整改的摊位信息如下：");
        for (int i = 0; i < booths.length; i++) {
            booths[i].isClosed = true;
        }
        for (int i = 0; i < booths.length; i++) {
            if (booths[i].isClosed) {
                System.out.println(booths[i].toString());
            }
        }
    }

    public static void purchase(Booth booth, int numBuy) {
        if (numBuy <= 0) {
            System.out.println("购买失败,因为购买西瓜数不是正数");
        } else if (booth.isClosed) {
            System.out.println("购买失败,因为商家处于休摊整改状态");
        } else if (numBuy > booth.total) {
            System.out.println("购买失败,因为购买西瓜数大于在售西瓜数");
        } else {
            booth.total = booth.total - numBuy;
            System.out.println("购买成功");
			/*
			System.out.println("摊号："+booth.id);
			System.out.println("摊主姓名："+booth.name);
			System.out.println("目前在售西瓜数："+booth.total);
			*/
            //booth.toString();
        }
    }

    public void restock(int numIn) {
        if (numIn <= 0) {
            System.out.println("进货失败，因为进货量为非正数");
        } else if (numIn > 200) {
            System.out.println("进货失败，因为进货量超过200");
        } else {
            total += numIn;
            System.out.println("进货成功，进货量：" + numIn);
            System.out.println("当前在售西瓜总数：" + total);
        }
    }



    /*
    public void toString(int a) {
        System.out.println("==========================");
        System.out.println("西瓜摊信息");
        System.out.println("摊号：" + id);
        System.out.println("摊主姓名：" + name);
        System.out.println("在售西瓜数：" + total);
        System.out.print("是否休摊整改：");
        if (isClosed) {
            System.out.println("是");
        } else {
            System.out.println("否");
        }
        System.out.println("==========================");
    }

     */ //旧toString()


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setId(long num) {
        id = num;
    }

    public void setName(String flag) {
        name = flag;
    }

    public void setTotal(int num) {
        if (num < 0) {
            System.out.println("不可设定为负数");
        } else total = num;

    }

    public void setIsClosed(boolean flag) {
        isClosed = flag;
    }

}