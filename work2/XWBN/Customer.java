/**
 *@Date：2023/10/18
 *@Author：XWBN
 */

package XWBN2;

import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int times;
    protected LocalDate latestArrivedTime;

    public Customer(String name, int times) {
        this.name = name;
        this.times = times;
        latestArrivedTime = LocalDate.now();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setLatestArrivedTime(LocalDate latestArrivedTime) {
        this.latestArrivedTime = latestArrivedTime;
    }

    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public LocalDate getLatestArrivedTime() {
        return latestArrivedTime;
    }


    public void setLatestArrivedTime() { latestArrivedTime = LocalDate.now(); }

    @Override
    public String toString() {
        return "[顾客姓名:" + getName() + "]" + '\n' + "[顾客到店次数:" + getTimes() + "]" + '\n' + "[顾客最新到店时间:" + getLatestArrivedTime() + "]" + '\n';
    }
}
