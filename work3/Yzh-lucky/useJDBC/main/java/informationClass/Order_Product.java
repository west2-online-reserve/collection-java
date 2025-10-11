package informationClass;

public class Order_Product {
    private String order_code;
    private String product_code;
    private String product_name;
    private double product_price;
    private double order_price;

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }
    public void setProduct_code(String product_code){
        this.product_code = product_code;
    }
    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }
    public void setProduct_price(double product_price){
        this.product_price = product_price;
    }
    public void setOrder_price(double order_price){
        this.order_price = order_price;
    }

    //get
    public String getOrder_code(){
        return order_code;
    }

    public String getProduct_code(){
        return product_code;
    }

    public String getProduct_name(){
        return product_name;
    }

    public double getProduct_price(){
        return product_price;
    }

    public double getOrder_price(){
        return order_price;
    }

    public String toString(){
        return "Order Code: " + order_code + "\n" + "Product Code: " + product_code + "\n" + "Product Name: " + product_name + "\n" + "Product Price: " + product_price + "\n" + "Order Price: " + order_price;
    }
}
