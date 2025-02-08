import java.time.LocalDate;
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate latestVisitDate;
    public Customer(String name, int visitCount, LocalDate latestVisitDate) {
        this.name = name;
        this.visitCount = visitCount;
        this.latestVisitDate = latestVisitDate;
    }
    @Override
    public String toString() {
        return "Customer{"+
                "name='"+name+'\''+
                "visitCount='"+visitCount+'\''+
                "latestVisitDate='"+latestVisitDate+"'}";
    }
}
