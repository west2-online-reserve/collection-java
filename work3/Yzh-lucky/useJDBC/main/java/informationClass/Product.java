package informationClass;

public class Product {

    private String product_code;
    private String product_name;
    private double product_price;
    private String order_code;


    public Product(){}
    public Product(String product_code, String product_name, double product_price) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = product_price;
    }
    //set
    public void setProduct_code(String s) {
        product_code = s;
    }

    public void setProduct_name(String s) {
        product_name = s;
    }

    public void setProduct_price(double s) {
        product_price = s;
    }

    public void setOrder_code(String s) {
        order_code = s;
    }

    //get
    public String getProduct_code() {
        return product_code;
    }
    public String getProduct_name() {
        return product_name;
    }
    public double getProduct_price() {
        return product_price;
    }
    public String getOrder_code() {
        return order_code;
    }

    public String toString() {
        return "商品编号：" + product_code + "\n商品名称：" +product_name + "\n商品价格：" + product_price + "\n订单号：" + order_code + "\n";
    }
}
