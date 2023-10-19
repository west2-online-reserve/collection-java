import java.time.LocalDate;

class Customer{
    public String customerName;
    public int arrivalTimes;
    LocalDate localDate=LocalDate.now();
    public Customer(String customerName,int arrivalTimes){
        this.customerName=customerName;
        this.arrivalTimes=arrivalTimes;
    }
    @Override
    public String toString(){
        return("顾客名："+customerName+"\n顾客到店次数："+arrivalTimes+"\n顾客到店时间"+localDate);
    }
}
