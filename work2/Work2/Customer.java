package Work2;
import java.time.LocalDate;
public class Customer {
    private String name;
    private int arrivetimes;
    private LocalDate latestarrive;
    public Customer(){}


    public Customer(String name){
        this.name=name;
        this.arrivetimes=arrivetimes;
        this.latestarrive=latestarrive;

    }
    public String getName(){
        return name;
    }
    public int getArrivetimes(){
        return arrivetimes;
    }
    public LocalDate getLatestarrive(){
        return latestarrive;
    }
    @Override
    public String toString(){
        return "顾客名字"+name+"到店次数"+arrivetimes+"最新到店时间"+latestarrive;

    }



}
