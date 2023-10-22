/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */

import java.time.LocalDate;

/*
顾客名字(String)
到店次数(int)
最新到店时间(LocalDate类)
 */
public class Customer {
    private String name;
    private int visitNum;
    private LocalDate latestArrivalDate;

    public Customer(String name, int visitNum, LocalDate latestArrivalDate) {
        this.name = name;
        this.visitNum = visitNum;
        this.latestArrivalDate = latestArrivalDate;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "name='" + name + '\'' +
                ", visitNum=" + visitNum +
                ", latestArrivalDate=" + latestArrivalDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    public LocalDate getLatestArrivalDate() {
        return latestArrivalDate;
    }

    public void setLatestArrivalDate(LocalDate latestArrivalDate) {
        this.latestArrivalDate = latestArrivalDate;
    }
}
