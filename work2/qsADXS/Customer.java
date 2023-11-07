import java.time.LocalDate;
public class Customer {
    private String name;
    private int numberOfVisits;
    private LocalDate time;

    public Customer(String name) {
        this.name = name;
        this.numberOfVisits = 0;
        this.time = LocalDate.now();
    }


    public void arrive(){
        numberOfVisits++;
        time = LocalDate.now();
        System.out.println(name +"来了一次");
    }

    public int getNumberOfVisits(){
        return numberOfVisits;
    }

    @Override
    public String toString(){
        String str;
        str = "顾客名:"+ name + "\n到店次数:"+numberOfVisits;
        if(numberOfVisits == 1)
            str +="\n最新到店时间:" + time;
        return str;
    }

    public String getName() {
        return name;
    }

    public LocalDate getTime() {
        return time;
    }
}
