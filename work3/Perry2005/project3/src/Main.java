import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*
        ArrayList<String>  arr = tool.getAllGoods();
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }*/
        /*Date D_util = new Date();
        java.sql.Date D = new java.sql.Date(D_util.getTime());
        orders order = new orders(2,"2*,墓穴指名者",D,12);
        tool.addOrders(order);*/
        System.out.println("选择操作对象 商品0,订单1");
        Scanner sc = new Scanner(System.in);
        int choose1 = sc.nextInt();
        if(choose1==0){
            Tool tool = new Tool();
            tool.selectInfoGood();
            System.out.println("增0,删1,改2,查3");
            int choose2 = sc.nextInt();
            switch (choose2){
                case 0:
                    System.out.println("id =");
                    int id0 = sc.nextInt();
                    System.out.println("name =");
                    String name0 = sc.next();
                    boolean flag0 = tool.checKGoodHaving(name0);
                    System.out.println("price =");
                    int price0 = sc.nextInt();
                    boolean Flag0 = tool.checkPriceLegal(price0);
                    if (!Flag0){
                        System.out.println("价格不合法");
                        break;
                    }
                    if(flag0) {
                        goods good = new goods(id0, name0, price0);
                        tool.addGoods(good);
                    }else {
                        System.out.println("该商品已存在");
                    }
                    break;
                case 1:
                    System.out.println("id=");
                    int id1 = sc.nextInt();
                    System.out.println("name=");
                    String name1 = sc.next();
                    boolean flag1 = tool.checKGoodHaving(name1);
                    if(!flag1){
                        boolean Flag = tool.checkOrderHavingGood(name1);
                        if(Flag){
                            tool.dropGoods(id1);
                        }else {
                            System.out.println("订单中存在该商品");
                        }
                    }else {
                        System.out.println("该商品不存在");
                    }
                    break;
                case 2:
                    System.out.println("id=");
                    int id2 = sc.nextInt();
                    System.out.println("修改后:name=");
                    String name2 = sc.next();
                    boolean flag2 = tool.checKGoodHaving(name2);
                    System.out.println("修改后:price");
                    int price2 = sc.nextInt();
                    boolean Flag2 = tool.checkPriceLegal(price2);
                    if (!Flag2){
                        System.out.println("价格不合法");
                        break;
                    }
                    if(flag2) {
                        tool.updateGoods(name2,price2,id2);
                    }else {
                        System.out.println("该商品已存在");
                    }
                    break;
                case 3:
                    tool.selectInfoGood();
            }

        } else if (choose1==1) {
            Tool tool = new Tool();
            tool.selectInfoUserOrder();
            System.out.println("增0,删1,查2");
            int choose2 = sc.nextInt();
            switch (choose2){
                case 0:
                    System.out.println("商品共有");
                    tool.selectInfoGood();
                    System.out.println("id=");
                    int id0 = sc.nextInt();
                    System.out.println("info=");
                    String info0 = sc.next();
                    System.out.println("time= (格式:年-月-日 如:2025-01-01)");
                    String time0 = sc.next();
                    System.out.println("price=");
                    int price0 = sc.nextInt();
                    boolean Flag0 = tool.checkPriceLegal(price0);
                    if (!Flag0){
                        System.out.println("价格不合法");
                        break;
                    }
                    Date D =null;
                    try {
                         D = java.sql.Date.valueOf(time0.substring(0,10));
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println("日期格式有误");
                    }
                    orders order = new orders(id0,info0, D,price0);
                    tool.addOrders(order);
                    break;
                case 1:
                    System.out.println("id = ");
                    int id1 = sc.nextInt();
                    tool.dropOrders(id1);
                    break;
            }
        }else {
            System.out.println("请输入正确的选择");
        }

    }
}