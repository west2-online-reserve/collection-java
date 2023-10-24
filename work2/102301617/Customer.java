import java.time.LocalDate;

/**
 * @author 102301617
 */
public class Customer {
   protected String name;
    /**
     * visit:到店次数
     * */
    protected int visit;

    protected LocalDate time;
   @Override
    public String toString() {
        return name+visit+time;
    }

}
