

public class goods {
    private int id;
    private String name;
    private int price;


    public goods() {
    }

    public goods(int id ,String name, int price) {
        this.name = name;
        this.price = price;
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

    public int getId(){
        return id;
    }
    public void  setId(int id){
        this.id = this.id;
    }

    public String toString() {
        return "goods{id = "+ id +"name = " + name + ", price = " + price + "}";
    }
}
