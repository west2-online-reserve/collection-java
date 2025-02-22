import java.sql.Date;

public class orders {
    private int id;
    private String name;
    private Date date;
    private int price;

    public orders() {
    }

    public orders(int id ,String name, Date date, int price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
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
     *
     * @return date
     */
    public java.sql.Date getDate() {
        return  date;
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
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "orders{id =" +id+ ",name = " + name + ", date = " + date + ", price = " + price + "}";
    }
}
