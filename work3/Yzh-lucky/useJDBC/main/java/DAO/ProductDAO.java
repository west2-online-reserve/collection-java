package DAO;

import JDBC.JdbcUtils;
import JDBC.ResultSetHandler;
import MyExceptions.ProductException;
import informationClass.Product;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    /**
     * 添加商品
     * @return 影响的行数
     */
    public int addProduct(Product p) {
        if (p.getProduct_code() == null || p.getProduct_name() == null){
            throw new ProductException("商品信息不能为空！");
        }
        if (p.getProduct_price() < 0) {
            throw new ProductException("商品价格不能为负数！");
        }
        String sql = "insert into `product_information`(`product_code`,`product_name`,`product_price`) values(?,?,?)";
        int i = 0;
        try {
            Connection c = JdbcUtils.getConnection();
            i = JdbcUtils.executeUpdate(sql, Integer.parseInt(p.getProduct_code()), p.getProduct_name(), p.getProduct_price());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    /**
     * 删除商品
     * @return 影响的行数
     */
    public int deleteProduct(Product p){
        //判断订单中是否商品
   //     String sql = "select count(*) from `order_product` where `product_code` = ?";//   String code = p.getProduct_code();
//        int c = JdbcUtils.executeQuery(sql,new ResultSetHandler<Integer>() {
//            @Override
//            public Integer handle(ResultSet rs){
//                try {
//                    if(rs.next()){
//                        if(rs.getInt(1)>0){
//                            return 1;
//                        }
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                return 0;
//            }
//        },code);
        boolean b = isExist(p);

        if (!b){
            //订单中没有该商品，则直接删除
            String sql = "delete from `Product_information` where `product_code`=?";
            int i = JdbcUtils.executeUpdate(sql, p.getProduct_code());
            return i;
        } else {
            return 0;
        }
    }

    /**
     *改变商品信息
     * @return 影响的行数
     */
    public int updateProduct(Product p){
        if (p.getProduct_code() == null || p.getProduct_name() == null){
            throw new ProductException("商品信息不能为空！");
        }
        if (p.getProduct_price() < 0) {
            throw new ProductException("商品价格不能为负数！");
        }
//        String sql = "select count(*) from `order_product` where `product_code`=?";
//        boolean b = Boolean.TRUE.equals(JdbcUtils.executeQuery(sql, new ResultSetHandler<Boolean>() {
//            @Override
//            public Boolean handle(ResultSet rs) {
//                try {
//                    if (rs.next()) {
//                        if (rs.getInt(1) > 0) {
//                            return true;
//                        }
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                return false;
//            }
//        }, p.getProduct_code()));
        boolean b = isExist(p);
        if (!b){
            String sql = "update `product_information` set `product_name`=?,`product_price`=? where `product_code`=?";
            int i = JdbcUtils.executeUpdate(sql, p.getProduct_name(), p.getProduct_price(), Long.parseLong(p.getProduct_code()));
            return i;
        } else {
            return 0;
        }
    }

    /**
     * 查询商品信息(根据商编)
     */
    public void queryProduct(Product p){
        if (isExist(p)){
            String sql = "select `product_code`,`product_name`,`product_price` from `product_information` where `product_code`=?";
            JdbcUtils.executeQuery(sql,new ResultSetHandler<Boolean>(){
                @Override
                public Boolean handle(ResultSet rs){
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));){
                        if (rs.next()){
                            StringBuilder sb = new StringBuilder();
                            sb.append("商品编号：" + rs.getInt("product_code") + "\n");
                            sb.append("商品名字：" + rs.getString("product_name") + "\n");
                            sb.append("商品价格：" + rs.getBigDecimal("product_price") + "\n");
                            bw.write(sb.toString());
                            bw.flush();
                            return true;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }
            },p.getProduct_code());
        } else {
            System.out.println("该商品不存在！");
        }

    }

    /**
     * 查询所有商品信息
     * return 空
     */
    public void queryAllProduct(String sort_order){
        String sql = "select * from product_information order by asc";
        String sql2 = "select * from product_information order by desc";
        if (sort_order.equals("asc")){
            JdbcUtils.executeQuery(sql,new ResultSetHandler<Void>(){
                @Override
                public Void  handle(ResultSet rs){
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
                        while (rs.next()){
                            StringBuilder sb = new StringBuilder();
                            sb.append("商品编号："+ rs.getInt("product_code") + "\n");
                            sb.append("商品名字：" + rs.getString("product_name") + "\n");
                            sb.append("商品价格：" + rs.getBigDecimal("product_price") + "\n");
                            sb.append("=====================================\n");
                            bw.write(sb.toString());
                            bw.flush();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
            },new Object[0]);
        } else if (sort_order.equals("desc")){
            JdbcUtils.executeQuery(sql2,new ResultSetHandler<Void>(){
                @Override
                public Void  handle(ResultSet rs){
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
                        while (rs.next()){
                            StringBuilder sb = new StringBuilder();
                            sb.append("商品编号："+ rs.getInt("product_code") + "\n");
                            sb.append("商品名字：" + rs.getString("product_name") + "\n");
                            sb.append("商品价格：" + rs.getBigDecimal("product_price") + "\n");
                            sb.append("=====================================\n");
                            bw.write(sb.toString());
                            bw.flush();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
            },new Object[0]);
        }

    }

    /**
     * 查询商品是否存在在订单中
     * return true:存在 false:不存在
     */
    public boolean isExist(Product p){
        if (p.getProduct_code() == null || p.getProduct_name() == null){
            throw new ProductException("商品信息不能为空！");
        }
        if (p.getProduct_price() < 0){
            throw new ProductException("商品价格不能为负数！");
        }
        String sql = "select count(*) from `order_product` where `product_code`=?";
        boolean b= Boolean.TRUE.equals((JdbcUtils.executeQuery(sql,new ResultSetHandler<Boolean>(){
            @Override
            public Boolean handle(ResultSet rs){
                try {
                    if (rs.next()){
                        if (rs.getInt(1)>0){
                            return true;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }
        },Integer.parseInt(p.getProduct_code()))));
        return b;
    }

    /**
     * 分页查询(一页的)商品信息
     * return 空
     */
    public ArrayList<Product> queryByPage(int pageNum, int pageSize){
        //验证页码和页大小的合理性
        if (pageNum <1){
            pageNum = 1;
        }
        if (pageSize <10 || pageSize >50){
            pageSize = 10;
        }
        //计算起始位置
        int offset = (pageNum-1) * pageSize;
        //开始查询
        String sql = "select `product_code`,`product_name`,`product_price` from `product_information` order by `product_code` limit ?,?";
        return JdbcUtils.executeQuery(sql,new ResultSetHandler<ArrayList<Product>>(){
            @Override
            public ArrayList<Product> handle(ResultSet rs){
                ArrayList<Product> list = new ArrayList<>();
//                BufferedWriter bw = null;
//                try {
//                    bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                while(true){
                    Product p = new Product();
                    try {
                        if (!rs.next())
                            break;
//                        StringBuilder sb = new StringBuilder();
//                        sb.append("商品编号: "+ rs.getString("product_code") + "\n");
//                        sb.append("商品名字: "+ rs.getString("product_name") + "\n");
//                        sb.append("商品价格: "+ rs.getString("product_price") + "\n");
//                        sb.append("======================================\n");
//                        bw.write(sb.toString());
//                        bw.flush();
                        p.setProduct_code(String.valueOf(rs.getInt("product_code")));
                        p.setProduct_name(rs.getString("product_name"));
                        p.setProduct_price(rs.getDouble("product_price"));
                        list.add(p);
                    } catch (SQLException e) {
                        System.out.println("分页查询出现异常！");
                        throw new RuntimeException(e);
                    }
                }
                return list;
            }
        },offset,pageSize);
    }
    /**
     * 判断商品信息完整
     * return true:完整 false:不完整
     */
    public static boolean checkProduct(Product p){
        if (p.getProduct_code() == null || p.getProduct_name() == null || p.getProduct_price() == 0){
            return false;
        }
        return true;
    }
    /**
     * 打印出分页查询出来的信息
     * @param pageNum 页码
     * @param pageSize 页大小
     * return 空
     */
    public void printQueryByPage(int pageNum, int pageSize){
        ArrayList<Product> list = queryByPage(pageNum, pageSize);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Product p : list){
            StringBuilder sb = new StringBuilder();
            sb.append("商品编号: "+ p.getProduct_code() + "\n");
            sb.append("商品名字: "+ p.getProduct_name() + "\n");
            sb.append("商品价格: "+ p.getProduct_price() + "\n");
            sb.append("======================================\n");
            try {
                bw.write(sb.toString());
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
