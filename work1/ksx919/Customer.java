import java.time.LocalDate;

public class Customer{
    protected String name;
    protected int count;
    protected LocalDate lastdate;

    public Customer(String name,LocalDate lastdate)
    {
        this.name=name;
        this.count=0;
        this.lastdate=lastdate;
    }
    public String toString()
    {
        return String.format("顾客名：%s，到店次数：%d，最新到店时间：%tF",name,count,lastdate);
    }
    public LocalDate getLastdate(){
        System.out.printf("%s最后一次光临时间为:%tF\n",this.name,this.lastdate);
        return lastdate;
    }
}