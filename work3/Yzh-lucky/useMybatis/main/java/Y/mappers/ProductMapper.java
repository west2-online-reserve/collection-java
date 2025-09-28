package Y.mappers;

import Y.pojo.Order;
import Y.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ProductMapper {
    //查询所有商品信息(按商品编号排序)
    ArrayList<Product> selectAll(String orderBy);

    //根据商品编号查询商品信息
    Product selectProductById(int code);

    //分页查询
    ArrayList<Product> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("orderBy") String orderBy);

    //添加一个表中没有的商品信息
    int insertProduct(Product product);

    //删除一个存在的商品信息
    int deleteProduct(int code);

    //更新一个存在的商品信息
    int updateProduct(@Param("code")int code,@Param("name") String name, @Param("price")double price);
}
