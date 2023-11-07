import java.time.LocalDate;

public class Customer {
    private String custname;
    private int num;
    private LocalDate gettime;
    public Customer(String custname, int num, LocalDate gettime) {
        this.custname = custname;
        this.num = num;
        this.gettime = gettime;
    }
    public void setArriveDay(LocalDate gettime) {
        this.gettime = gettime;
    }
    public String getName() {
        return custname;
    }

    public void setName(String custname) {
        this.custname = custname;
    }

    public int getCount() {
        return num;
    }

    public void setCount(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
        return "name:"+custname
                +" "+"num:"+num+" "+"arriveday:"+ gettime.toString();
    }
}
