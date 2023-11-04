package westwork2;

import java.time.LocalDate;

//顾客类
class Customer {
 String name;
 int visitCount;
 LocalDate lastVisitDate;

 Customer(String name, int visitCount, LocalDate lastVisitDate) {
     this.name = name;
     this.visitCount = visitCount;
     this.lastVisitDate = lastVisitDate;
 }

 @Override
 public String toString() {
     return String.format("顾客名字: %s, 到店次数: %d, 最新到店时间: %s",
             this.name, this.visitCount, this.lastVisitDate.toString());
 }
}
