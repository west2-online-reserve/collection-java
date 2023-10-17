import java.time.LocalDate;

/**
 * @author 102301617
 */

public class Customer {

   protected String name;
  protected int visit;

    public String getName() {
        return name;
    }



    protected LocalDate time;
    @Override
    public String toString() {
        return name+visit+time;
    }

}
