package MySystem.Entity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Order {
    private int id;     //订单编号
    private Map<Goods, Integer> ordergoods;     //商品信息
    private Date date;      //下单时间
    private double amount;      //订单金额

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(amount, order.amount) == 0 && Objects.equals(ordergoods, order.ordergoods) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordergoods, date, amount);
    }
    public Order() {
    }

    public Order(int id, Map<Goods, Integer> ordergoods, Date date, double amount) {
        this.id = id;
        this.ordergoods = ordergoods;
        this.date = date;
        this.amount = amount;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return ordergoods
     */
    public Map<Goods, Integer> getOrdergoods() {
        return ordergoods;
    }

    /**
     * 设置
     * @param ordergoods
     */
    public void setOrdergoods(Map<Goods, Integer> ordergoods) {
        this.ordergoods = ordergoods;
    }

    /**
     * 获取
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "Order{id = " + id + ", ordergoods = " + ordergoods + ", date = " + date + ", amount = " + amount + "}";
    }
}