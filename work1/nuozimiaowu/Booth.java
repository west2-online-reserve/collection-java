package com.sty;

import java.util.ArrayList;

public class Booth {
    private long id;
    private String name;
    private int total;
    private boolean isClosed;

    public Booth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }


    @Override
    public String toString() {
        return "西瓜摊的信息:\n" +
                "西瓜摊ID: " + id + "\n" +
                "西瓜摊名字: " + name + "\n" +
                "此西瓜摊目前的西瓜数目: " + total + "\n" +
                "是否关店: " +isClosed;
    }


    public static void purchase(Booth booth, int purchaseAmount) {
        if (purchaseAmount > 0 && !booth.isClosed() && purchaseAmount <= booth.getTotal()) {
            booth.setTotal(booth.getTotal() - purchaseAmount);
            System.out.println("购买成功   数量：" + purchaseAmount + " 西瓜摊号码： " + booth.getId() + ".");
            System.out.println("现在"+booth.getId()+"号西瓜摊剩余的西瓜数量："+booth.getTotal());
        }else if(purchaseAmount>booth.getTotal()) {
            System.out.println("Purchase failed.购买西瓜数不能大于在售西瓜数。");
        }else if(booth.isClosed){
            System.out.println("Purchase failed.商家不能处于休摊整改状态；");
        }else {
            System.out.println("Purchase failed.购买西瓜的数目不能小于0哦");
        }
    }


    public void restock(int restockAmount) {
        if (restockAmount > 0 && restockAmount <= 200) {
            total += restockAmount;
            System.out.println(id + "西瓜摊进货 " + restockAmount + ".");
        }else if(restockAmount>200) {
            System.out.println("进货失败，单次进货量不能超过 200");
        }
        else{
            System.out.println("进货失败，进货的数目不能小于零。");
        }
    }


    public static void closeBooths(ArrayList<Booth> booths) {
        for (Booth booth : booths) {
            if (!booth.isClosed()) {
                booth.setClosed(true);
                System.out.println(booth);
                System.out.println("");
            }
        }
    }
}
