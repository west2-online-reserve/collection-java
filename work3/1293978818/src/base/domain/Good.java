package base.domain;
/**
 * @author 1293978818
 */
public class Good {

    private int goodid;
    private String goodName;
    private double goodPrice;

    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Good(){

    }

    public Good(int goodid,String goodName,double goodPrice){
        this.goodid = goodid;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
    }


    @Override
    public String toString() {
        return goodid + "\t\t" + goodName + "\t" + goodPrice;
    }    
}
