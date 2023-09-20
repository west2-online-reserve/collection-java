package com.PeanutJava.task1;

    //创建对象
public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    //构造方法
    //空参
    public Booth() {
    }
    //有参
    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    //Set/Get
    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }


    public boolean isIsClosed() {
        return isClosed;
    }


    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "WatermelonBooth{id = " + id + ", name = " + name + ", total = " + total + ", isClosed = " + isClosed + "}";
    }

    //进货
    public void restock(int ResNums){
        if(ResNums<=200){
            this.total+=ResNums;
            System.out.println("进货成功");
        }else{
            System.out.println("进货失败，单次进货量不能超过 200");
        }
    }


}
