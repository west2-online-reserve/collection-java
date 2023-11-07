package westwork2;

import java.time.LocalDate;

//顾客类
public class Customer {
 private String name;
 private int visitCount;
 private LocalDate lastVisitDate;
 public Customer(String name) {
        this.name = name;
        this.times = 0;
        this.lastVisitDate = LocalDate.now();
    }
    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }
    public int getTimes() {

        return times;
    }
    public void setTimes(int times) {

        this.times = times;
    }
    public LocalDate getlastVisitDate() {

        return lastVisitDate;
    }
    public void setlastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

 @Override
 public String toString() {
     return String.format("顾客名字: %s, 到店次数: %d, 最新到店时间: %s",
             this.name, this.visitCount, this.lastVisitDate.toString());
 }
}
