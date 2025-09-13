package Work1;
import java.time.LocalDate;
public class Customer {
    private String name;
    private int visitCount;
    private LocalDate dateTime;
    private Animal wantAnimal;

    public Customer(String name, int visitCount, LocalDate dateTime, Animal wantAnimal) {
        this.name = name;
        this.visitCount = visitCount;
        this.dateTime = dateTime;
        this.wantAnimal = wantAnimal;
    }

    public String toString() {
        return "顾客的名字："+this.name+",到店铺的次数："+this.visitCount+",最新到店时间"+this.dateTime+"，想要的动物是"+this.wantAnimal;
    }

    public Animal getWantAnimal() {
        return wantAnimal;
    }
}
