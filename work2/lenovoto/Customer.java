package homework.work2;
import sun.util.resources.LocaleData;

import java.time.LocalDate;


public class Customer {


    LocalDate latestDate=LocalDate.now();


    String customerName="Piper";
    String sex="女";
    int age=27;
    int cnt=1;



    @Override
    public String toString() {
        return "Customer{" +
                "latestDate=" + latestDate +
                ", customerName='" + customerName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", cnt=" + cnt +
                '}';
    }
}
