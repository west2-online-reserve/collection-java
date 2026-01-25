import java.time.LocalDate;

public class Customer {
    protected String name;
    protected int visitCount;//到店次数
    protected LocalDate lastestVisitTime;//最新到店时间


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastestVisitTime() {
        return lastestVisitTime;
    }

    public void setLastestVisitTime(LocalDate lastestVisitTime) {
        this.lastestVisitTime = lastestVisitTime;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public Customer(String name) {
        this.name = name;
        this.visitCount = 0;
    }
    public Customer(){};

    @Override
    public String toString() {
        return "姓名:"+this.name+",来店次数:"+this.visitCount+",最新一次来过的时间为"+this.lastestVisitTime;
    }
}
