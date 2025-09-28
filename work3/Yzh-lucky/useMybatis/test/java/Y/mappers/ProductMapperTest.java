package Y.mappers;

import Y.pojo.Product;
import Y.utils.mybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProductMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        ArrayList<Product> products = productMapper.selectAll("desc");
        for  (Product product : products) {
            System.out.println(product);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.selectProductById(0);
        System.out.println(product);
        sqlSession.close();
    }

    @Test
    public void testSelectByPage() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        ArrayList<Product> products = productMapper.selectByPage(0, 5, "desc");
        for  (Product product : products) {
            System.out.println(product);
        }
        sqlSession.close();
    }
    @Test
    public void testInsert() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = new Product(88, "手机", 100.0);
        int result = productMapper.insertProduct(product);
        System.out.println(result);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int result =  productMapper.deleteProduct(88);
        System.out.println(result);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = mybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int result = productMapper.updateProduct(99, null, 1);
        System.out.println(result);
        sqlSession.commit();
        sqlSession.close();
    }
}
