package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.mapper.MyOrderMapper;
import org.example.pojo.Commodity;
import org.example.pojo.MyOrder;
import org.example.service.MyOrderService;
import org.example.utils.MyBatisUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MyOrderServiceImpl implements MyOrderService {
    CommodityServiceImpl commodityService = new CommodityServiceImpl();
    @Override
    public void add() {
        double price = 0;
        boolean key =true;
        List<Integer> commodityIds = List.of();
        while(key){
            key = false;
            //查询所有订单
            List<Commodity> commodityList=commodityService.queryAllShop();
            System.out.println("请输入订单中要加入的商品ID,并以','隔开");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            //获取商品ID
            commodityIds = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

            //计算价格+判断商品是否存在
            for (Integer commodityId : commodityIds) {
                Commodity commodity = commodityList.stream()
                        .filter(c -> c.getId() == commodityId)
                        .findFirst()
                        .orElse(null);
                if (commodity != null) {
                    price += commodity.getPrice();
                }else{
                    System.out.println("商品ID:"+commodityId.toString()+"，该商品不存在，请重新输入您所需的商品");
                    key=true;
                    break;
                }

            }
        }
            MyOrder myOrder = new MyOrder();
            myOrder.setOrderTime(LocalDateTime.now());
            myOrder.setPrice(price);
            //事务管理：确保不会出现某一部失败时数据库依旧有残留数据
            try(SqlSession session = MyBatisUtils.getSqlSession()){
                MyOrderMapper mapper = session.getMapper(MyOrderMapper.class);
                mapper.add(myOrder);

                //主键回填获得MyOrder:id
                int myOrderId = myOrder.getId();
                //创建关联表
                mapper.addOrderItem(myOrderId,commodityIds);
                //通过主键获得服务器中的订单，确保需求一致
                MyOrder myOrder1 = mapper.queryById(myOrderId);
                System.out.println("订单创建完成");
                System.out.println(myOrder1);
                session.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        



    }

    @Override
    public void queryAll() {
        int pageNum = 1;
        int pageSize = 5;
        try(SqlSession session = MyBatisUtils.getSqlSession()){
            MyOrderMapper mapper = session.getMapper(MyOrderMapper.class);
            List<MyOrder>myOrderList= mapper.queryAll();
            for(MyOrder myOrder:myOrderList){
                System.out.println(myOrder);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try(SqlSession session = MyBatisUtils.getSqlSession()){
            MyOrderMapper mapper = session.getMapper(MyOrderMapper.class);
            //先删除关联表再删除订单
            mapper.deleteItemsById(id);
            mapper.deleteByCommodityId(id);
            session.commit();
            System.out.println("订单删除成功");
        }
    }


    public static void main(String[] args) {
        MyOrderServiceImpl myOrderService = new MyOrderServiceImpl();
        myOrderService.queryAll();
    }
}
