package Y.utils;

import Y.pojo.Order;
import Y.pojo.Product;
import org.apache.ibatis.annotations.Param;

public interface Update {
    //将新订单的商品信息插入到订单项表中
    int insertToOrderProduct(Order o);

    //将订单中新加的商品信息插入到订单项表中
    int insertNewToOrderProduct(@Param("code")int code,@Param("p")Product p);

    //将从订单中(减少)删除的商品从订单项表中（减少）删除并更新订单总价
    int deleteToOrderProduct(@Param("code") int Code, @Param("p") Product p);

    //将从订单中(增加)删除的商品从订单项表中（增加）删除并更新订单总价
    int updateToOrderProduct(@Param("code") int orderCode);
}
