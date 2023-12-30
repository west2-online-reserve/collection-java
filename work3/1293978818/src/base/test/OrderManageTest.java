package base.test;

import java.util.Scanner;

import base.order.OrderManage;
/**
 * 朴实无华的测试类
 * @author 1293978818
 * @version 1.0
 */
public class OrderManageTest {

    public static void main(String[] args) {

        /*前期准备 */
        Scanner sc = new Scanner(System.in);
        OrderManage orderManage = new OrderManage();

        int choice = 0;
        System.out.println("欢迎来到订单管理系统，请输入您的选择");
        System.out.println("1.创建一份空的订单");
        System.out.println("2.新建商品");
        System.out.println("3.将指定的商品加入到订单中");
        System.out.println("4.删除指定商品信息");
        System.out.println("5.修改指定商品");
        System.out.println("6.查询所有商品信息");
        System.out.println("7.将订单中的指定商品删除");
        System.out.println("8.修改订单中购买商品的个数");
        System.out.println("9.删除订单");
        System.out.println("10.输出指定订单信息");
        System.out.println("11.输出所有订单信息");
        System.out.println("12.退出程序");
        lo:while(true){

            choice = sc.nextInt();

            switch(choice){
                case 1:
                    orderManage.addorder();
                    break;
                case 2:
                    orderManage.addGood(sc);
                    break;
                case 3:
                    orderManage.addGoodToOrder(sc);
                    break;
                case 4:
                    orderManage.deleteGood(sc);
                    break;
                case 5:
                    orderManage.updateGood(sc);
                    break;
                case 6:
                    orderManage.printAllGood(sc);
                    break;
                case 7:
                    orderManage.deleteOrderGood(sc);
                    break;
                case 8:
                    orderManage.changeOrderGood(sc);
                    break;
                case 9:
                    orderManage.deleteTotalOrder(sc);
                    break;
                case 10:
                    orderManage.printTotalOrder(sc);
                    break;
                case 12:
                    System.out.println("再见");
                    break lo;
                case 11:
                    orderManage.printAllTotalOrder(sc);
                    break;
                default:
                    System.out.println("输入有误，再来一次吧");
                    continue lo;
            }

            System.out.println("还有什么要做的吗");
        }
        orderManage.closeConnection();
        sc.close();
    }

}
