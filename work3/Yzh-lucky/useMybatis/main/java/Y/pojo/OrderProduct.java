package Y.pojo;

public class OrderProduct {
    private int orderCode;
    private int productCode;
    private String productName;
    private double productPrice;
    private double orderPrice;

    public OrderProduct(){}

    //get
    public int getOrder_code() {
        return orderCode;
    }
    public void setOrder_code(int order_code) {
        this.orderCode = order_code;
    }
    public int getProduct_code() {
        return productCode;
    }
    public void setProduct_code(int product_code) {
        this.productCode = product_code;
    }
    public String getProduct_name() {
        return productName;
    }
    public void setProduct_name(String product_name) {
        this.productName = product_name;
    }
    public double getProduct_price() {
        return productPrice;
    }
    public void setProduct_price(double product_price) {
        this.productPrice = product_price;
    }
    public double getOrder_price() {
        return orderPrice;
    }
    public void setOrder_price(double order_price) {
        this.orderPrice = order_price;
    }

    @Override
    public String toString() {
        return "OrderProduct [order_code=" + orderCode + ", product_code=" + productCode + ", product_name=" + productName + ", product_price=" + productPrice + ", order_price=" + orderPrice + "]";
    }
}
