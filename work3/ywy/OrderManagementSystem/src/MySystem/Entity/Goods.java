package MySystem.Entity;

import java.util.Objects;

public class Goods {
    private int id;//iD编号
    private String name;//名称
    private double price;//价格

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id && Double.compare(price, goods.price) == 0 && Objects.equals(name, goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    public Goods() {
    }

    public Goods(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Goods{id = " + id + ", name = " + name + ", price = " + price + "}";
    }

}
