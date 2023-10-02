package szw.test2;

import java.time.LocalDate;

//成员变量:
//        顾客名字(String)
//        到店次数(int)
//        最新到店时间(LocalDate类)
//        方法
//        重写(@Override) toString() 方法, 要求按一定格式输出客户的所有信息
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitDate;



    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }
    public void addVisitCount(){
        visitCount++;
    }
    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }
    public void setLastVisitDate(){
        lastVisitDate=LocalDate.now();
    }
    @Override
    public String toString() {
        return "顾客{" +
                "名字：'" + name + '\'' +
                ", 到店次数：" + visitCount +
                ", 最新到店时间：" + lastVisitDate +
                '}';
    }
}
