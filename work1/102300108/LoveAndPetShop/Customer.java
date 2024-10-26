package LoveAndPetShop;
import java.time.LocalDate;

/**
 * 顾客类的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class Customer {
    //构造函数
    public Customer(String name,int visitFrequency,LocalDate lastVisitDate){
        this.name=name;
        this.visitFrequency=visitFrequency;
        this.lastVisitDate=lastVisitDate;
    }
    @Override
    public String toString(){
        return "顾客名："+this.name+"\n到店次数："+visitFrequency+"\n最新到店时间:"+lastVisitDate+"\n";
    }
    
    //返回顾客姓名
    public String getName(){
        return name;
    }

    //返回顾客访问次数
    public int getFrequency(){
        return visitFrequency;
    }

    //返回顾客最近来访时间
    public LocalDate getLastVisiDate(){
        return lastVisitDate;
    }

    //修改顾客访问次数
    public void setVisitFrequency(int frequency){
        this.visitFrequency=frequency;
    }

    //修改最近访问时间
    public void setLastVisitDate(){
        lastVisitDate=LocalDate.now();
    }

    private String name;
    private int visitFrequency;
    private LocalDate lastVisitDate;
}