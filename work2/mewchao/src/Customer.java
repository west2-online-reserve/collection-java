import java.time.LocalDate;
/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: Customer
 * @description（类描述）: 客户类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class Customer {
    public String name="unnamed";
    /**
     * 客户到店次数
     **/
    public int visitTimes;
    /**
     * 最新到店时间
     **/
    public LocalDate LatestArrivalTime;
    public Customer(String name){
        this.name=name;
    }

    public String toString() {
        String s;
        if(visitTimes==0){
            s="name:"+name+"\nvisitTimes:"+0;
        }
        else{
            s="name:"+name+"\nvisitTimes:"+visitTimes+"\nLatestArrivalTime:"+LatestArrivalTime.toString();
        }
        return s;
    }

    public LocalDate getLatestArrivalTime(){
        return this.LatestArrivalTime;
    }
    public int getVisitTimes(){
        return this.visitTimes;
    }
    public String getName(){
        return this.name;
    }
}
