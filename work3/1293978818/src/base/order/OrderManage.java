package base.order;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import base.domain.Good;
import base.domain.OrderGood;
import base.domain.TotalOrder;

/**
 * 此类用于进行订单业务的实现
 * @author 1293978818
 * @version 1.0
 */
public class OrderManage {

    /**提前创建Connection，防止重复定义 */

    private GoodFromToDataBase goodFromToDataBase;
    private OrderGoodFromToDataBase orderGoodFromToDataBase;
    private TotalOrderFromToDataBase totalOrderFromToDataBase;

    

    /**订单管理系统的构造方法 */
    public OrderManage(){
        this.goodFromToDataBase = new GoodFromToDataBase();
        this.orderGoodFromToDataBase = new OrderGoodFromToDataBase();
        this.totalOrderFromToDataBase = new TotalOrderFromToDataBase();
    }

    /**此方法用于创建一个空的订单 */
    public void addorder(){
        int orderId = totalOrderFromToDataBase.addEmptyOrder();
        if(orderId == -1){
            System.out.println("创建失败");
            return;
        }
        System.out.println("新建订单成功，您的订单编号为：" + orderId);     
    }

    /**此方法用于添加商品到数据库 */
    public void addGood(Scanner sc){
        Good good = new Good();

        /*存入商品名字 */
        System.out.println("请输入商品名称");
        String goodName = sc.next();
        good.setGoodName(goodName);
        
        /*存入商品价格 */
        System.out.println("请输入该商品的价格");
        Double goodPrice = 0.0;

        /*如果价格小于0则重新输入 */
        while(true){
            goodPrice = sc.nextDouble();
            if(goodPrice > 0){
                break;
            }
            System.out.println("您输入的商品价格有误，请重试");
        }
        good.setGoodPrice(goodPrice);

        goodFromToDataBase.addGood(good);
              
    }

    /**此方法用于添加商品到订单中 */
    public void addGoodToOrder(Scanner sc){

        System.out.println("请输入您的订单号");
        int orderId = 0;        
        orderId = sc.nextInt();
        TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(orderId);

        if (totalOrder == null){
            System.out.println("您输入的订单编号不存在，请创建一个新的订单或检查订单号后再来");
            return;
        }

        printAllGood(sc);
        System.out.println("请输入您要购买的商品编号");
        
        int goodId = 0;
        Good good = new Good();

        /**判断商品id是否存在 */
        while(true){
            goodId = sc.nextInt();
            good = goodFromToDataBase.getGood(goodId);
            if (good != null){
                break;
            }
            System.out.println("您输入的商品id有误，请重试");
        }

        /*判断之前是否购买过该商品 */
        if (orderGoodFromToDataBase.getOrderGood(orderId, goodId) != null){
            System.out.println("您已经购买过该商品了，不妨试试修改商品个数");
            return;
        }

        /*获得商品的单价 */
        double unitPrice = good.getGoodPrice();

        System.out.println("请输入该商品的个数");
        int num = 0;
        /*判断商品个数是否合理 */
        while (true){
            num = sc.nextInt();
            if (num > 0){
                break;
            }
            System.out.println("个数应为大于0的数");
        }
        
        /*获得该商品的总价 */
        double totalPrice = unitPrice * num;

        /*将该商品加入到订单中 */
        OrderGood orderGood = new OrderGood(orderId,goodId,num,totalPrice);
        orderGoodFromToDataBase.addOrderGood(orderGood);

        /*将订单总表的总价改变 */
        totalOrder.setTotalPrice(totalOrder.getTotalPrice() + totalPrice);

        /*将totalorder上传到数据库中 */
        totalOrderFromToDataBase.updateTotalOrder(totalOrder);

        System.out.println("添加成功");

    }

    /**此方法用于删除指定商品*/
    public void deleteGood(Scanner sc){

        /*打印所有商品 */
        printAllGood(sc);

        System.out.println("请输入您要删除的商品编号");
        int deleteGoodId = 0;

        /*判断商品编号是否存在*/
        while (true){
            deleteGoodId = sc.nextInt();
            if(goodFromToDataBase.getGood(deleteGoodId) != null){
                break;
            }
            System.out.println("您输入的商品编号有误，请重试");
        }

        /*获得所有购买该商品的订单信息 */
        ArrayList<OrderGood> list = orderGoodFromToDataBase.getOrderGoodArrayListByGoodId(deleteGoodId);

        /*删除该商品在商品表的信息 */
        goodFromToDataBase.deleteGood(deleteGoodId);

        /*删除所有购买该商品的目录 */
        orderGoodFromToDataBase.deleteOrderGoodByGoodId(deleteGoodId);

        /*将所有购买过该商品的订单总价调整 */
        for(int i = 0;i < list.size();i ++){
            TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(list.get(i).getOrderId());
            totalOrder.setTotalPrice(totalOrder.getTotalPrice() - list.get(i).getGoodPrice());
            totalOrderFromToDataBase.updateTotalOrder(totalOrder);
        }
        System.out.println("删除成功");        
    }

    /**此方法用于修改指定商品 */
    public void updateGood(Scanner sc){

        /*打印所有商品信息 */
        printAllGood(sc);

        System.out.println("请输入要修改的商品编号");
        int goodId = 0;
        Good good = new Good();

        /*判断商品id是否存在 */
        while (true){
            goodId = sc.nextInt();
            good = goodFromToDataBase.getGood(goodId);
            if (good != null){
                break;
            }
            System.out.println("您输入的商品id有误，请重试");
        }

        System.out.println("请输入修改后的商品名字");
        String goodName = sc.next();
        System.out.println("请输入修改后的单价");
        double goodprice = 0;

        /*判断该单价是否合法 */
        while(true){
            goodprice = sc.nextDouble();
            if(goodprice > 0){
                break;
            }
            System.out.println("您输入的单价有误，请重试");
        }
        
        /*获得该商品的原单价 */
        double originGoodPrice = good.getGoodPrice();
        double changeGoodPrice = goodprice - originGoodPrice;

        /*修改商品信息 */
        good.setGoodName(goodName);
        good.setGoodPrice(goodprice);
        goodFromToDataBase.updategood(good);

        /*修改所有购买该商品的目录信息 */
        ArrayList<OrderGood> list1 = orderGoodFromToDataBase.getOrderGoodArrayListByGoodId(goodId);
        for(int i = 0;i < list1.size();i ++){
            OrderGood orderGood = list1.get(i);
            orderGood.setGoodPrice(orderGood.getGoodNum() * goodprice);
            orderGoodFromToDataBase.updateOrderGood(orderGood);

            /*修改订单总表的信息 */
            TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(orderGood.getOrderId());
            totalOrder.setTotalPrice(totalOrder.getTotalPrice() + changeGoodPrice * orderGood.getGoodNum());
            totalOrderFromToDataBase.updateTotalOrder(totalOrder);
        }

        System.out.println("修改成功");       
    }

    /**此方法用于删除订单中的一条目录 */
    public void deleteOrderGood(Scanner sc){

        System.out.println("请输入要删除的订单号");
        int orderId = sc.nextInt();
        TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(orderId);

        /*判断订单号是否存在 */
        if (totalOrder == null){
            System.out.println("您输入的订单号不存在，请重试");
            return;
        }

        System.out.println("该订单号的所有商品如下");

        /*判断该订单号是否有商品 */
        if(!printAllOrderGood(orderId)){
            return;
        }

        System.out.println("请输入您要删除的商品编号");
        int goodId = 0;
        OrderGood orderGood = new OrderGood();

        /*判断该订单中是否有该商品 */
        while(true){
            goodId = sc.nextInt();
            orderGood = orderGoodFromToDataBase.getOrderGood(orderId, goodId);
            if (orderGood != null){
                break;
            }
            System.out.println("您的输入有误，请重试");
        }

        /*删除该目录信息 */
        orderGoodFromToDataBase.deleteOrderGood(orderId, goodId);

        /*修改总订单价格 */
        totalOrder.setTotalPrice(totalOrder.getTotalPrice() - orderGood.getGoodPrice());
        totalOrderFromToDataBase.updateTotalOrder(totalOrder);
        System.out.println("删除成功");
    }

    /**此方法用于修改指定目录下商品的购买个数 */
    public void changeOrderGood(Scanner sc){
        
        System.out.println("请输入您要修改的订单编号");
        int orderId = sc.nextInt();
        TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(orderId);

        /*判断订单id是否存在 */
        if(totalOrder == null){
            System.out.println("该订单号不存在，请重试");
            return;
        }

        /*输出商品所有信息并判断订单是否存在商品 */
        System.out.println("该订单下有如下商品");
        if(!printAllOrderGood(orderId)){
            return;
        }

        System.out.println("请输入您要修改个数的商品id");
        int goodId = 0;
        OrderGood orderGood = new OrderGood();

        /*判断该商品编号是否存在 */
        while (true){
            goodId = sc.nextInt();
            orderGood = orderGoodFromToDataBase.getOrderGood(orderId, goodId);
            if(orderGood != null){
                break;
            }
            System.out.println("您输入的商品编号有误或不在订单中");

        }

        /*获得原来的商品个数和商品价格 */
        double goodPrice = orderGood.getGoodPrice();
        int originGoodNum = orderGood.getGoodNum();
        goodPrice = goodPrice / originGoodNum;

        System.out.println("请输入新的商品个数");
        int goodNum = 0;

         /*判断个数是否合法 */
        while(true){
            goodNum = sc.nextInt();
            if(goodNum > 0){
                break;
            }
            System.out.println("您输入的个数有误，请重试");
        }

        /*修改该商品目录的信息 */
        orderGood.setGoodNum(goodNum);
        orderGood.setGoodPrice(goodNum * goodPrice);
        orderGoodFromToDataBase.updateOrderGood(orderGood);
        
        /*修改订单总表的信息 */
        totalOrder.setTotalPrice(totalOrder.getTotalPrice() + (goodNum - originGoodNum) * goodPrice);
        totalOrderFromToDataBase.updateTotalOrder(totalOrder);

        System.out.println("修改成功");        
    }

    /**此方法用于删除指定订单 */
    public void deleteTotalOrder(Scanner sc){

        System.out.println("请输入您要删除的订单号码");
        int orderId = 0;
        TotalOrder totalOrder = new TotalOrder();
        orderId = sc.nextInt();
        totalOrder = totalOrderFromToDataBase.getTotalOrder(orderId);
        if(totalOrder == null){ 
            System.out.println("您输入的订单号不存在，请重试");               
            return;
        }
        
        /*删除总表的信息 */
        totalOrderFromToDataBase.deleteTotalOrder(orderId);

        /*删除此订单的所有目录 */
        orderGoodFromToDataBase.deleteOrderGoodByOrderId(orderId);
        System.out.println("删除成功");    
    }

    /**此方法用于打印该订单的全部内容 */
    public void printTotalOrder(Scanner sc){

        System.out.println("请输入您想要查询的订单号");
        int orderId = sc.nextInt();
        TotalOrder totalOrder = totalOrderFromToDataBase.getTotalOrder(orderId);

        /*判断订单号是否存在 */
        if (totalOrder == null){
            System.out.println("您输入的订单号不存在，请重试");
            return;
        }

        System.out.println("订单编号：" + totalOrder.getOrderId());
        printAllOrderGood(orderId);
        System.out.println("总价格为：" + totalOrder.getTotalPrice());

        /*获得最近一次修改时间 */
        Date date = new Date(totalOrder.getOrderTime());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println("最近一次修改时间" + format);       
    }

    /** 此方法用于打印所有商品*/
    public void printAllGood(Scanner sc){
        System.out.println("以下是在售的所有商品");
        ArrayList<Good> list = goodFromToDataBase.getAllGood();
        
        
        /*获得总页码数 */
        int totalpage = 0;
        int num = 3;
        if (list.size() % num == 0){
            totalpage = list.size() / num;
        }else{
            totalpage = list.size() / num + 1;
        }

        /*获得每个页码的内容 */
        for(int i = 0;i < totalpage;i ++){

            System.out.println("商品编号\t商品名\t商品价格");

            /*获得每页的内容 */
            for(int j = i * num;j < (i + 1) * num;j ++){
                if (j >= list.size()){
                    break;
                }
                System.out.println(list.get(j));
            }            

            System.out.println("第" + (i + 1) + "页，共" + totalpage + "页");

            if(i + 1 == totalpage){
                break;
            }

            System.out.println("输入任意字符进入下一页");
            sc.next();

        }
        
    }

    /**此方法用于输出某一订单号下的所有目录 */
    public boolean printAllOrderGood(int orderId){
        
        System.out.println("序号\t\t商品编号\t商品名称\t购买数量\t购买金额");
        ArrayList<OrderGood> list = orderGoodFromToDataBase.getOrderGoodArrayListByOrderId(orderId);

        /*如果目录不存在，直接结束方法 */
        if(list.size() == 0){
            System.out.println("您还没有添加过商品哦");
            return false;
        }

        /*如果目录存在，输出信息 */
        for(int i = 0;i < list.size();i ++){
            OrderGood orderGood = list.get(i);
            Good good = goodFromToDataBase.getGood(orderGood.getGoodId());
            System.out.println((i + 1) + "\t\t" + good.getGoodid() + "\t\t" + good.getGoodName() + "\t\t" + orderGood.getGoodNum() + "\t\t" + orderGood.getGoodPrice());
        }
        return true;
    } 
}
