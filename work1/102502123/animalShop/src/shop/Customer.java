package shop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String name;
    private int visitCount = 0;
    private LocalDate lastVisitDate = null;


    public Customer() {
    }
    //构造方法只接收name，其它设定空的初始值
    public Customer(String name) {
        this.name = name;
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

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }



    public void visit() {
        this.visitCount++;
        this.lastVisitDate = LocalDate.now();
    }


    @Override
    public String toString() {
        String dateStr = (lastVisitDate == null) ? "暂无记录"
                : lastVisitDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        return "顾客信息\n" +
                "姓名: " + name +
                " \n到店次数: " + visitCount + "次" +
                " \n最新到店时间: " + dateStr;
    }
}