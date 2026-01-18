package org.example.pojo;

public class Commodity {
    protected Integer id;
    protected String name;
    protected double price;
    protected Integer count;
    protected Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }



    @Override
    public String toString() {
        if(status==null){
            return "Commodity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +'\'' +
                ", 当前售卖情况="+(status==1?"正在售卖":"已售罄")+
                '}';
    }


    public Commodity(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Commodity() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
