package JDBC.orderManagemaentSystem;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;
/**
 * 商品订单控制系统
 *
 * @Author 31445
 * @Date 2023/11/23
 */
public class ManagementSystem implements orderManagement {

    @Override
    public void addProduct(String productName,double productPrice) throws SQLException {
        Connection conn=null;
        try {

            conn = jdbcutils.getConnection();
            //检测输入的商品名是否存在
            jdbcutils.productHasExistException(conn,productName);

            String sql = "insert into `商品` ( `商品名`, `商品价格`) values (?, ?)";
            int i = jdbcutils.executeUpdate(conn, sql,  productName, productPrice);
            if (i > 0) {
                System.out.println("商品【"+productName+"】添加成功！");
            } else {
                System.out.println("商品【"+productName+"】添加失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚事务
            jdbcutils.rollTranscation(conn);
        } catch (MyException e) {
            System.out.println("输入错误："+e.getMessage());
        }finally {
            jdbcutils.release(conn, null, null);
        }
    }

    @Override
    public void deleteProdect(int prodectId) throws SQLException {
        Connection conn=null;
        try {
            conn=jdbcutils.getConnection();
            //检查商品存在
            jdbcutils.productNotFountException(conn,prodectId);

            String sql="delete from `商品` where `商品编号`=?";
            int i = jdbcutils.executeUpdate(conn,sql,prodectId);
            if (i > 0) {
                System.out.println(prodectId+"号商品删除成功！");
            } else {
                System.out.println(prodectId+"号商品删除失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚事务
            jdbcutils.rollTranscation(conn);
        } catch (MyException e) {
            System.out.println("输入异常："+e.getMessage());
        }finally {
            jdbcutils.release(conn,null,null);
        }
    }

    @Override
    public void updateProdect(int productId,String productName,double productPrice) throws SQLException {
        Connection conn=null;
        try {
            conn=jdbcutils.getConnection();
            //检查商品是否存在
            jdbcutils.productNotFountException(conn,productId);

            String sql="update `商品` set `商品名`=? ,`商品价格`=? where `商品编号`=?";
            int i= 0;
                i = jdbcutils.executeUpdate(conn,sql,productName,productPrice,productId);
            if (i > 0) {
                System.out.println(productId+"号商品更改成功！");
            } else {
                System.out.println(productId+"号商品更改失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚事务
            jdbcutils.rollTranscation(conn);
        } catch (MyException e) {
            System.out.println("输入错误："+e.getMessage());
        } finally {
            jdbcutils.release(conn,null,null);
        }
    }

    @Override
    public void viewProdect() throws SQLException {
        Connection conn=null;
        ResultSet rs = null;
        try {
            conn=jdbcutils.getConnection();
            String sql = "select `商品编号`, `商品名`, `商品价格` from `商品` ";
            rs = null;
            rs = jdbcutils.executeQuery(conn, sql);
            System.out.println("商品编号\t商品名\t商品价格");
            while (rs.next()) {
                int productId = rs.getInt("商品编号");
                String productName = rs.getString("商品名");
                double productPrice = rs.getDouble("商品价格");
                System.out.println(productId + "\t" + productName + "\t" + productPrice);
            }
            jdbcutils.commitTranscation(conn);
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            jdbcutils.rollTranscation(conn);
        } finally {
            jdbcutils.release(conn, null, rs);
        }
    }
    @Override
    public void addOrder(int productId,double orderPrice) throws SQLException {
        Connection conn=null;
        ResultSet rs= null;
        try {
            conn=jdbcutils.getConnection();
            rs = jdbcutils.executeQuery(conn,"select `商品价格` from 商品 where 商品编号=?",productId);
            rs.next();
            String p= rs.getString(1);
            double productPrice=Double.parseDouble(p);
            //检查商品存在
            jdbcutils.productNotFountException(conn,productId);
            //检查订单单价是否合理
            jdbcutils.IllegalPriceException(orderPrice,productPrice);

            String sql="insert into `订单`(`商品编号`,`订单价格`,`商品价格`) values(?,?,?)";
            int i=jdbcutils.executeUpdate(conn,sql,productId,orderPrice,productPrice);

            if (i > 0) {
                System.out.println("订单添加成功！");
            } else {
                System.out.println("订单添加失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚事务
            jdbcutils.rollTranscation(conn);
        } catch (MyException e) {
            System.out.println("输入错误："+e.getMessage());
        } finally {
            jdbcutils.release(conn,null,rs);
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        Connection conn=null;
        try {
            conn=jdbcutils.getConnection();
            //检查订单是否存在
            jdbcutils.orderNotFoundException(conn,orderId);

            String sql1 = "DELETE FROM `订单` WHERE `订单编号` = ?";
            int rows = jdbcutils.executeUpdate(conn, sql1, orderId);
            if (rows > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (MyException e) {
            System.out.println("输入错误："+e.getMessage());
        } finally {
            jdbcutils.release(conn, null,null);
        }
    }

    public void updateOrder(int orderId,int newProductId,double newOrderPrice) throws SQLException {
        Connection conn=null;
        ResultSet rs = null;
        try {
            conn=jdbcutils.getConnection();
            //检查订单是否存在
            jdbcutils.orderNotFoundException(conn,orderId);

            rs = jdbcutils.executeQuery(conn, "select `商品价格` from 商品 where 商品编号=?", newProductId);
            rs.next();
            String p = rs.getString("商品价格");
            double productPrice = Double.parseDouble(p);
            jdbcutils.IllegalPriceException(newOrderPrice,productPrice);

            String sql = "update `订单` set `商品编号`=?,`订单价格`=?,`商品价格`=?  where `订单编号`=?";
            int i = jdbcutils.executeUpdate(conn, sql, newProductId, newOrderPrice, productPrice, orderId);
            if (i > 0) {
                System.out.println("商品更改成功！");
            }
            else {
                System.out.println("商品更改失败！");
            }
            jdbcutils.commitTranscation(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (MyException e) {
            System.out.println("输入错误："+e.getMessage());
        } finally {
            jdbcutils.release(conn, null, rs);
        }
    }
    @Override
    public void viweOrder() throws SQLException {
        Connection conn =jdbcutils.getConnection();
        String sql ="select `订单编号`, `商品编号`, `商品价格`,`订单价格` ,`下单时间`from `订单` ";
        ResultSet rs= null;
        try {
            rs = jdbcutils.executeQuery(conn, sql);

            System.out.println("订单编号"+"\t"+"商品编号"+"\t"+"商品价格"+"\t"+"订单价格"+"\t"+"下单时间");
            while (rs.next()) {
                int orderId =rs.getInt("订单编号");
                int productId = rs.getInt("商品编号");
                double productPrice = rs.getDouble("商品价格");
                double orderPrice =rs.getDouble("订单价格");
                Date orderDate=rs.getDate("下单时间");
                System.out.println(orderId +" \t    " + productId + "\t     " + productPrice + "\t  " + orderPrice +"\t  "+orderDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            jdbcutils.release(conn,null,rs);
        }
    }

    @Override
    public void rankOrder( Scanner sc) throws SQLException {
        Connection conn=jdbcutils.getConnection();
        String seat2, seat3;
        System.out.println("对商品还是订单排序：");
        System.out.println("1.商品");
        System.out.println("2.订单");
        int choice1 = sc.nextInt();
        System.out.println("请选择排序方式：");
        String sql = "";
        if (choice1 == 1) {
            sql = "SELECT * FROM `商品` ORDER BY ";
            seat2 = "商品价格";
            System.out.println("1.按价格升序");
            System.out.println("2.按价格降序");
            int i = sc.nextInt();
            switch (i) {
                case 2:
                    seat3 = "DESC";
                    break;
                case 1:
                default:
                    seat3 = "ASC";
                    break;
            }
            sql += "`" + seat2 + "` " + seat3;
            ResultSet rs = jdbcutils.executeQuery(conn, sql);
            System.out.println("商品编号"+"\t"+"商品名"+"\t"+"商品价格");
            while (rs.next()) {
                int productId = rs.getInt("商品编号");
                String productName=rs.getString("商品名");
                double productPrice = rs.getDouble("商品价格");
                System.out.println(productId + "\t     " +productName+"\t     "+ productPrice );
            }
        }
        else {
            sql = "SELECT * FROM `订单` ORDER BY ";
            System.out.println("1.按下单时间升序");
            System.out.println("2.按下单时间降序");
            System.out.println("3.按价格升序");
            System.out.println("4.按价格降序");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    seat2 = "下单时间";
                    seat3 = "ASC";
                    break;
                case 2:
                    seat2 = "下单时间";
                    seat3 = "DESC";
                    break;
                case 3:
                    seat2 = "订单价格";
                    seat3 = "ASC";
                    break;
                case 4:
                    seat2 = "订单价格";
                    seat3 = "DESC";
                    break;
                default:
                    System.out.println("无效的选择，默认按照下单时间顺序");
                    seat2 = "下单时间";
                    seat3 = "ASC";
                    break;
            }
            sql += "`" + seat2 + "` " + seat3;
            ResultSet rs = jdbcutils.executeQuery(conn, sql);
            System.out.println("订单编号"+"\t"+"商品编号"+"\t"+"商品价格"+"\t"+"订单价格"+"\t"+"下单时间");
            while (rs.next()) {
                int orderId =rs.getInt("订单编号");
                int productId = rs.getInt("商品编号");
                double productPrice = rs.getDouble("商品价格");
                double orderPrice =rs.getDouble("订单价格");
                Date orderDate=rs.getDate("下单时间");
                System.out.println(orderId +" \t    " + productId + "\t     " + productPrice + "\t  " + orderPrice +"\t  "+orderDate);

            }
            jdbcutils.release(conn, null, rs);
        }
    }
}
