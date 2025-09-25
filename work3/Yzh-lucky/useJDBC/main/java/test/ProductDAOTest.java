package test;

import DAO.ProductDAO;
import informationClass.Product;

public class ProductDAOTest {
    public static void main(String[] args) {
        Product p = new  Product("9","MLB帽子",300.00);
        Product p2 = new Product("6","蜡笔小新玩偶",200.00);
        Product p3 = new Product("6","雨伞",50.00);
        ProductDAO pdao = new ProductDAO();
        pdao.printQueryByPage(1,3);
//        pdao.queryAllProduct("asc");
//        pdao.queryProduct(p);
//        //i表示影响的行数
//
//        int i = pdao.deleteProduct(p);
//        i = pdao.addProduct(p3);
//        i = pdao.updateProduct(p2);
    }
}
