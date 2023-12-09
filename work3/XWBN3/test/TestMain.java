package XWBN3.test;

import XWBN3.dao.OrderDao;
import XWBN3.dao.impl.OrderDaoimpl;
import XWBN3.entity.commodity;
import XWBN3.entity.commoditylinkorder;
import XWBN3.entity.orders;
import XWBN3.entity.target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 * @Author：XWBN
 * @Package：XWBN3.test
 * @Project：MyCodes
 * @name：TestMain
 * @Date：2023/11/22 20:18
 * @Filename：TestMain
 */
public class TestMain {
    private static OrderDao orderDao = new OrderDaoimpl();

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("******欢迎进入订单管理系统*******");
            System.out.println("\t1.查询所有订单");
            System.out.println("\t2.查询指定订单");
            System.out.println("\t3.查询所有商品信息");
            System.out.println("\t4.查询指定商品信息");
            System.out.println("\t5.添加订单信息");
            System.out.println("\t6.添加商品信息");
            System.out.println("\t7.修改订单信息");
            System.out.println("\t8.修改商品信息");
            System.out.println("\t9.删除订单信息");
            System.out.println("\t10.删除商品信息");
            System.out.println("\t11.实现订单的排序（按价格排序）");
            System.out.println("\t12.实现订单的排序（按下单时间排序）");
            System.out.println("\t13.退出");
            System.out.println("******************************");
            System.out.println("请选择菜单：");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("查询所有的订单");
                    List<target> list1 = orderDao.selectAll();
                    for (target target1:list1) {
                        System.out.println(target1);
                    }
                    break;
                case 2:
                    System.out.println("请输入查询的订单id:");
                    int order_id = scanner.nextInt();
                    List<target> list2 = orderDao.selectOne(order_id);
                    if(list2 == null){

                    }else{

                        for (target target2:list2) {
                            System.out.println(target2);
                        }
                    }
                    break;
                case 3:
                    System.out.println("查询所有的商品");
                    List<commodity> list3 = orderDao.selectCommodityAll();
                    for(commodity commodity1:list3){
                        System.out.println(commodity1);
                    }

                    break;
                case 4:
                    System.out.println("请输入查询的商品id:");
                    int commodity_id = scanner.nextInt();
                    commodity commodity2 = orderDao.selectCommodityOne(commodity_id);
                    if(commodity2.getCommodityId() == 0){
                        System.out.println("无此商品！！！");
                    }else{
                        System.out.println(commodity2);
                    }

                    break;
                case 5:
                    System.out.println("请输入订单编号:");
                    int oid1 = scanner.nextInt();
                    System.out.println("请输入下单时间:");
                    String otime = scanner.next();


                    int tprice = 0;
                    while(true){
                        System.out.println("请输入订单关联商品编号:");
                        int coid = scanner.nextInt();
                        System.out.println("请输入商品编号:");
                        int cid = scanner.nextInt();
                        System.out.println("请输入商品数量:");
                        int cnum = scanner.nextInt();
                       commodity commodity3 = orderDao.selectCommodityOne(cid);
                       double oprice = commodity3.getCommodityPirce();
                       oprice = oprice * cnum;
                       tprice += oprice;
                        commoditylinkorder clo = new commoditylinkorder(coid,oid1,cid,cnum,oprice);
                        int insert2 = orderDao.insertCommoditylinkOrder(clo);
                        if(insert2 > 0){
                            System.out.println("添加成功！！！");
                        }else{
                            System.out.println("添加失败！！！");
                        }
                        System.out.println("是否继续添加该订单的信息？（是1/否2）");
                        int choice1 = scanner.nextInt();
                        if(choice1 == 1){

                        }else{
                            break;
                        }
                    }
                    orders or = new orders(oid1,otime,tprice);
                    int insert1 = orderDao.insertOrder(or);

                    break;
                case 6:
                    System.out.println("请输入商品编号：");
                    int cid = scanner.nextInt();
                    System.out.println("输入商品名称：");
                    String cname = scanner.next();
                    System.out.println("请输入商品的单价：");
                    double cprice = scanner.nextDouble();
                    commodity commodity1 = new commodity(cid, cname,cprice);
                    int insert = orderDao.insertCommodity(commodity1);
                    if(insert > 0){
                        System.out.println("添加成功！！！");
                    }else{
                        System.out.println("添加失败！！！");
                    }
                    break;
                case 7:
                    System.out.println("请输入要修改的订单编号：");
                    int oid2 = scanner.nextInt();
                    List<target> list4 = orderDao.selectOne(oid2);
                    if(list4 == null){
                        System.out.println("无此订单！！！");
                    }
                    for (target target:list4) {
                        System.out.println(target);
                    }
                    System.out.println("请选择您要修改的详细订单编号：");
                    int coid = scanner.nextInt();
                    System.out.println("请输入您要修改商品编号为：");
                    int cid2 = scanner.nextInt();
                    System.out.println("请输入您要修改的购买数量为：");
                    int cnum = scanner.nextInt();
                    commodity commodity5 = orderDao.selectCommodityOne(cid2);
                    double oprice = cnum * commodity5.getCommodityPirce();
                    commoditylinkorder commoditylinkorder2 = new commoditylinkorder(coid,cid2,oid2,cnum,oprice);
                    List<target> list5 = orderDao.selectAll();
                    int tprice1 = 0;
                    for (target target3:list5) {
                        tprice1 += target3.getOrder_price();
                    }
                    orders order1 = orderDao.selectOrderOne(oid2);
                    String orderDate = order1.getOrderTime();
                    orders order2 = new orders(oid2,orderDate,tprice1);
                    int update2 = orderDao.updateCommodityLinkOrder(commoditylinkorder2);
                    int update3 = orderDao.updateOrder(order2);
                    if(update2 > 0){
                        System.out.println("修改成功！！！");
                    }
                    else{
                        System.out.println("修改失败！！！");
                    }

                    break;
                case 8:
                    System.out.println("请输入要修改的商品id：");
                    int cid1 = scanner.nextInt();
                    commodity commodity3 = orderDao.selectCommodityOne(cid1);
                    if(commodity3 == null){
                        System.out.println("当前商品不存在！！！");
                        break;
                    }
                    System.out.println("修改前的商品信息:"+commodity3);
                    System.out.println("请输入修改后商品的名称：");
                    String cname1 = scanner.next();
                    System.out.println("请输入修改后商品的单价：");
                    double cprice1 = scanner.nextDouble();

                    commodity commodity4 = new commodity(cid1,cname1,cprice1);
                    int update1 = orderDao.updateCommodity(commodity4);

                    if(update1 > 0){
                        System.out.println("修改成功！！！");
                    }
                    else{
                        System.out.println("修改失败！！！");
                    }
                    break;
                case 9:
                    System.out.println("请输入您要删除的订单编号：");
                    int oid3 = scanner.nextInt();
                    int deleteOrder = orderDao.deleteOrder(oid3);

                    if(deleteOrder >0 ){
                        System.out.println("删除成功！！！");
                    }else{
                        System.out.println("删除失败！！！");
                    }
                    break;
                case 10:
                    System.out.println("请输入您想要删除的商品编号：");
                    int cid3 = scanner.nextInt();
                    int deleteCommodity = orderDao.deleteCommodity(cid3);
                    if(deleteCommodity > 0){
                        System.out.println("删除成功！！！");
                    }else{
                        System.out.println("删除失败！！！");
                    }
                    break;
                case 11:
                    List<target> list6 = orderDao.SortOrderByPrice();
                    for (target target1:list6) {
                        System.out.println(target1);
                    }
                    break;
                case 12:
                    List<target> list7 = orderDao.SortOrderByTime();
                    for (target target1:list7) {
                        System.out.println(target1);
                    }orderDao.SortOrderByTime();
                    break;
                case 13:
                    System.out.println("谢谢使用");
                    return;
                default:System.out.println("输入错误！！！");
            }
            System.out.println("按任意键继续。。。。。。");
            scanner.nextLine();
            scanner.nextLine();
        }while(true);
    }
}
