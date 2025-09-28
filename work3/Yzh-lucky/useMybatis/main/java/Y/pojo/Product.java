package Y.pojo;

import Y.mappers.ProductMapper;
import Y.utils.mybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class Product {
    private int productCode;
    private String productName;
    private double productPrice;

    public Product(int productCode, String productName, double productPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    // getters and setters

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    @Override
    public String toString() {
        return "Product [productCode=" + productCode + ", productName=" + productName + ", productPrice=" + productPrice + "]";
    }
}
