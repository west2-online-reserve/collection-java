package work2;

import java.time.LocalDate;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate visitTime;

    public Customer(String name) {
        this.name = name;
        this.visitCount = 0;
        this.visitTime = null;
    }

    @Override
    public String toString() {
        System.out.println("顾客姓名：" + name + "   到店次数：" + visitCount + "   最新到店时间：" + visitTime);
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDate visitTime) {
        this.visitTime = visitTime;
    }
}
