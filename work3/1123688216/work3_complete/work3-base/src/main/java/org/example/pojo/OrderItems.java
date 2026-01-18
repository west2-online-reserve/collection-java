package org.example.pojo;

public class OrderItems {
    protected Integer myOrderId;
    protected Integer commodityId;
    protected Integer commodityCount;
    public Integer getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
    }


    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Integer myOrderId) {
        this.myOrderId = myOrderId;
    }



}
