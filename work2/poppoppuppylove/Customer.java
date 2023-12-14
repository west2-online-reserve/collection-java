import java.time.LocalDate;
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitDate;

    public Customer(String name){
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }


    @Override
    public String toString() {
        return "Name: " + this.getName() + '，' +
                "Visit Count: " + this.getVisitCount() + '，' +
                "Last Visit Date: " + this.getLastVisitDate() ;
    }

}
