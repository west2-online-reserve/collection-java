import MySystem.DaoImpl.GoodsDaoImpl;
import MySystem.DaoImpl.OrderDaoImpl;
import MySystem.Entity.Goods;
import MySystem.Entity.Order;
import MySystem.Tools.JDBCUtil;

import java.sql.SQLException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws SQLException {
        Test test = new Test();

        test.testGoodsInsert();

        test.testGoodsUpDAte();

        test.testGoodsDelete();

        test.testGoodsGetById();

        test.testGoodsGetByAmount();

        test.testGoodsGetByPage();

        test.testGoodsGetAll();

        test.testGoodsGetCount();

        test.testOrderInsert();

        test.testOrderUpdate();

        test.testOrderDelete();

        test.testOrderGetByPage();

        test.testOrderGetAll();

        test.testOrderGetCount();

    }
    void testGoodsInsert(){//测试插入
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        System.out.println(dao1.insert(new Goods(5,"意面",-20)));//插入异常值
        System.out.println(dao1.insert(new Goods(5,"意面",20)));//插入正常
        System.out.println(dao1.insert(new Goods(5,"意面",20)));//插入冲覅
        System.out.println(dao1.insert(new Goods(6,"加多宝",4),new Goods(7,"王老吉",4)));//插入多个
    }

    void testGoodsUpDAte(){//测试更新
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        dao1.update(new Goods(5,"意面",-20));//更新异常值
        System.out.println("更改的行数"+dao1.update(new Goods(5,"意面",21)));//更新一个
        System.out.println("更改的行数"+dao1.update(new Goods(5,"意大利面",20),new Goods(4,"匹萨",30)));//跟新多个
        System.out.println("更改的行数"+dao1.update(new Goods(10,"炒面",25)));//更新不存在
    }

    void testGoodsDelete(){//测试删除
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        System.out.println(dao1.deleteById(7));//删除正常
        System.out.println(dao1.deleteById(10));//删除不存在
    }

    void testGoodsGetById(){//测试select即get通过id
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        System.out.println(dao1.getById(1));//获取
        System.out.println(dao1.getById(4));//获取
        System.out.println(dao1.getById(10));//获取不存在
        System.out.println(dao1.getById(-10));//获取不存在
    }

    void testGoodsGetByAmount(){//测试select即get通过金额大于或者小于
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        System.out.println(dao1.getByAmountAbove(5));
        System.out.println(dao1.getByAmountAbove(10));
        System.out.println(dao1.getByAmountAbove(-20));
        System.out.println(dao1.getByAmountAbove(20));
        System.out.println(dao1.getByAmountBelow(5));
        System.out.println(dao1.getByAmountBelow(10));
        System.out.println(dao1.getByAmountBelow(-20));//查询不到抛出异常
        System.out.println(dao1.getByAmountBelow(20));
    }

    void testGoodsGetByPage(){//测试select即get通过分页查询
        try {
            GoodsDaoImpl dao1=new GoodsDaoImpl();
            //错误页码
            System.out.println(dao1.getByPage(-1,2));
            //正常页码
            System.out.println(dao1.getByPage(1,4));
            System.out.println(dao1.getByPage(1,10));
            System.out.println(dao1.getByPage(2,5));
            //超过最大页码
            System.out.println(dao1.getByPage(3,5));

            //查询通过字符串
            System.out.println(dao1.getByPage("Price",1,5));
            System.out.println(dao1.getByPage("PriCe",1,10));
            System.out.println(dao1.getByPage("price",2,5));

            System.out.println(dao1.getByPage("Name",1,5));
            System.out.println(dao1.getByPage("NaMe",1,10));
            System.out.println(dao1.getByPage("name",2,5));

            System.out.println(dao1.getByPage("Id",1,10));
            System.out.println(dao1.getByPage("iD",2,5));
            System.out.println(dao1.getByPage("os",1,5));//异常
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void testGoodsGetAll(){//测试getall
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        //测试无参和各种排序方法
        System.out.println(dao1.getAll());
        System.out.println(dao1.getAll("iD"));
        System.out.println(dao1.getAll("NAME"));
        System.out.println(dao1.getAll("price"));
        System.out.println(dao1.getAll("date"));
    }

    void testGoodsGetCount(){//测试查询订单数量
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        System.out.println("商品的数量为"+dao1.getCount());
    }

    void testOrderInsert(){
        OrderDaoImpl dao1=new OrderDaoImpl();
        HashMap<Goods,Integer> map=new HashMap();
        map.put(new Goods(1,"薯条",5),-13);
        System.out.println(dao1.insert(new Order(4,map, new Date(2000000),5)));//异常，商品数量错误
        map.clear();
        map.put(new Goods(2,"薯条",5),2);
        System.out.println(dao1.insert(new Order(4,map, new Date(2000000),5)));//异常，商品信息错误
        map.clear();
        map.put(new Goods(1,"薯条",5),2);
        System.out.println(dao1.insert(new Order(4,map, new Date(2000000),-10)));//异常，订单金额错误
        map.put(new Goods(2,"汉堡",20),1);
        System.out.println(dao1.insert(new Order(4,map, new Date(2000000),25)));//正确信息
    }

    void testOrderUpdate(){
        OrderDaoImpl dao1=new OrderDaoImpl();
        HashMap<Goods,Integer> map=new HashMap();
        map.put(new Goods(4,"匹萨",30),-2);
        System.out.println(dao1.update(new Order(4,map, new Date(2000000),5)));//异常，商品数量错误
        map.clear();
        map.put(new Goods(1,"匹萨",30),2);
        System.out.println(dao1.update(new Order(4,map, new Date(2000000),5)));//异常，商品信息错误
        map.clear();
        map.put(new Goods(4,"匹萨",30),2);
        map.put(new Goods(3,"可乐",3),4);
        System.out.println(dao1.update(new Order(4,map, new Date(2000000),-72)));//异常，商品信息错误
        System.out.println(dao1.update(new Order(4,map, new Date(9000000),72)));//正确信息
    }

    void testOrderDelete(){
        OrderDaoImpl dao1=new OrderDaoImpl();
        System.out.println(dao1.deleteById(5));//删除不存在的订单
        System.out.println(dao1.deleteById(4));//删除存在的订单
    }

    void testOrderGetByPage(){
        try {
            OrderDaoImpl dao1=new OrderDaoImpl();
            //错误页码
            System.out.println(dao1.getByPage(-1,2));
            //正常页码
            System.out.println(dao1.getByPage(1,2));
            System.out.println(dao1.getByPage(1,5));
            System.out.println(dao1.getByPage(2,2));
            //超过最大页码
            System.out.println(dao1.getByPage(3,2));//异常

            //查询通过字符串
            System.out.println(dao1.getByPage("Price",1,2));
            System.out.println(dao1.getByPage("PriCe",1,5));
            System.out.println(dao1.getByPage("price",2,2));

            System.out.println(dao1.getByPage("Name",1,2));
            System.out.println(dao1.getByPage("NaMe",1,5));
            System.out.println(dao1.getByPage("name",2,2));

            System.out.println(dao1.getByPage("Id",1,4));
            System.out.println(dao1.getByPage("iD",2,2));
            System.out.println(dao1.getByPage("os",1,5));//异常
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void testOrderGetAll(){
        GoodsDaoImpl dao1=new GoodsDaoImpl();
        //测试无参和各种排序方法
        System.out.println(dao1.getAll());
        System.out.println(dao1.getAll("iD"));
        System.out.println(dao1.getAll("NAME"));
        System.out.println(dao1.getAll("price"));
        System.out.println(dao1.getAll("date"));//异常
    }

    void testOrderGetCount(){
        OrderDaoImpl dao1=new OrderDaoImpl();
        System.out.println("订单的数量为"+dao1.getCount());
    }


}