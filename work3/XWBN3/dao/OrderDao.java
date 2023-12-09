package XWBN3.dao;



import XWBN3.entity.commodity;
import XWBN3.entity.commoditylinkorder;
import XWBN3.entity.orders;
import XWBN3.entity.target;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * @Author：XWBN
 * @Package：XWBN3.dao
 * @Project：MyCodes
 * @name：OrderDao
 * @Date：2023/12/1 18:59
 * @Filename：OrderDao
 */
public interface OrderDao {

    void commit();

    // 回滚事务
    void rollback() ;


    /**
     * 查询所有订单的实现 select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id;
     * 参数：无
     * 返回值：List
     */
    List<target> selectAll();

    /**
     * 查询单个订单实现 select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id and oreder_id = ?;
     */
    List<target> selectOne(int order_id);

    /**
     * 查询商品列表  select * from commodity;
     */
    List<commodity> selectCommodityAll();

    /**
     * 查询指定的商品 select commodity_id,commodity_name,commodity_price from commodity where commodity_id= ?;
     */
    commodity selectCommodityOne(int commodity_id);

    /**
     * 查询订单的基本信息
     */
    orders selectOrderOne(int order_id);

    /**
     * 添加订单 insert into orders values(default,?,?,?);
     */
    int insertOrder(orders order1);

    int insertCommoditylinkOrder(commoditylinkorder commoditylinkorder1);

    int insertCommodity(commodity commodity1);

    /**
     * 实现商品表的数据的修改 update commodity set commodity_name = ?,commodity_price=?;
     */
    int updateCommodity(commodity commodity1);

    int updateCommodityLinkOrder(commoditylinkorder commoditylinkorder1);


    int updateOrder(orders order1);

    /**
     * 删除订单信息  delete from order where order_id = ?;
     *
     */

    int deleteOrder(int order_id);

    /**
     * 删除商品信息 delete from commodity where commodity_id = ?;
     */
    int deleteCommodity(int commodity_id);

    List<target> SortOrderByTime();

    List<target> SortOrderByPrice();


}
