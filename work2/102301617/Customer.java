import java.time.LocalDate;

/**
 * @author 102301617
 */

public class Customer {

    private String name;
    /**
     * visit:到店次数
     */
    private int visit;

    private LocalDate time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return name + visit + time;

    }
     public void renewCustomer(Customer customer){
        customer.frequency += 1;
        customer.timeNew = LocalDate.now();
    }
}
