package XWBN3.entity;

import java.io.Serializable;

/**
 * @Author：XWBN
 * @Package：XWBN3.entity
 * @Project：MyCodes
 * @name：commodity
 * @Date：2023/12/1 20:45
 * @Filename：commodity
 */
public class commodity implements Serializable {
    private int commodityId;
    private String commodityName;
    private double commodityPirce;

    public commodity(){}

    public commodity(int commodity_id, String commodity_name, double commodity_pirce){
        this.commodityId = commodity_id;
        this.commodityName = commodity_name;
        this.commodityPirce = commodity_pirce;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodity_name) {
        this.commodityName = commodity_name;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodity_id) {
        this.commodityId = commodity_id;
    }

    public double getCommodityPirce() {
        return commodityPirce;
    }

    public void setCommodityPirce(double commodity_pirce) {
        this.commodityPirce = commodity_pirce;
    }

    @Override
    public String toString(){
        return "[商品编号]:"+commodityId+" [商品名称]:"+commodityName+" [商品单价]:"+commodityPirce;
    }
}
