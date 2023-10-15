import java.time.LocalDate;

/**
 * @author pengyuezhe
 */
public class Customer {
private String name;
private int frequency;
private LocalDate newestDate;

    public Customer(String name, int frequency,int year,int month,int day) {
        this.name = name;
        this.frequency = frequency;
        this.newestDate = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public LocalDate getNewestDate() {
        return newestDate;
    }

    public void setNewestDate() {
        this.newestDate = LocalDate.now();
    }


    @Override
    public String toString(){
        return( "顾客名字"+name+
                "\n到店次数"+frequency+
                "\n最新到店时间"+newestDate);}
}
