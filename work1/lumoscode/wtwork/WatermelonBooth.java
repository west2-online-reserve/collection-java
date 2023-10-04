package com.dong.wtwork;

public class WatermelonBooth {
    private long id;
    private String name;
    private  int total;
    private  boolean isClosed;

    public WatermelonBooth() {
    }

    public WatermelonBooth(long id, String name, int total, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.isClosed = isClosed;
    }





    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
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
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取
     * @return isClosed
     */
    public boolean isIsClosed() {
        return isClosed;
    }

    /**
     * 设置
     * @param isClosed
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    public String toString() {
        return "WatermelonShop\n西瓜摊的基本信息如下: \n id = " + id + "\n name = " + name + "\n total = " + total + "\n isClosed = " + isClosed ;
    }
    public static void purchase(WatermelonBooth booth,int quantity){

        if(quantity<=0){
            System.out.println("购买数量必须为正数");
            return;
        }
        else if(booth.isIsClosed()){
            System.out.println("摊位正在修摊整改，无法购买");
            return;
        }
        else if(quantity> booth.getTotal()){
            System.out.println("购买数大于在售西瓜数，购买失败");
            return;
        }
        else{
            booth.setTotal(booth.getTotal()-quantity);
            System.out.println("购买成功"+quantity+"斤西瓜");
            }
    }
    public void restock(int quantity){
        if(quantity<=0){
            System.out.println("进货量必须为正数");
        }
        else if(quantity>200){
            System.out.println("单次进货量不得超过200");
        }
        else{
            this.total+=quantity;
            System.out.println("进货成功"+quantity+"斤西瓜");
        }
    }
    public static void closeBooths(WatermelonBooth[] booths){
        for(WatermelonBooth booth :booths){
            if(!booth.isIsClosed()){
                booth.setIsClosed(true);
                System.out.println("摊位"+booth.getId()+"已休业整改");
                System.out.println(booth.toString());
            }
        }
    }

}
