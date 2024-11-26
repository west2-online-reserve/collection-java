package DWA;

import com.alibaba.fastjson.annotation.JSONField;

public class Dive {
    @JSONField(name = "DiveOrder",deserialize = false,serialize = false)
    private int diveOrder;
    @JSONField(name = "DivePoints")
    private double divePoints;

    public Dive(int diveOrder, double divePoints) {
        this.diveOrder = diveOrder;
        this.divePoints = divePoints;
    }

    public int getDiveOrder() {
        return diveOrder;
    }

    public void setDiveOrder(int diveOrder) {
        this.diveOrder = diveOrder;
    }

    public double getDivePoints() {
        return divePoints;
    }

    public void setDivePoints(double divePoints) {
        this.divePoints = divePoints;
    }
}
