
import java.time.LocalDate;

class Customer{
    private String customerName;
    private int arrivalTimes;
    private LocalDate localDate;

    public void setArrivalTimes(int arrivalTimes) {
        this.arrivalTimes = arrivalTimes;
    }

    public void setLocalDate(LocalDate localDate){
        this.localDate=localDate;
    }
    public LocalDate localDate(){
        return localDate;
    }
    public int getArrivalTimes(){
        return arrivalTimes;
    }
    public String getCustomerName(){
        return customerName;
    }
    public Customer(String customerName,int arrivalTimes){
        this.customerName=customerName;
        this.arrivalTimes=arrivalTimes;
    }
    @Override
    public String toString(){
        return("顾客名："+customerName+"\n顾客到店次数："+arrivalTimes+"\n顾客到店时间"+localDate);
    }
}
