package Work2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int arrivetimes;
    private LocalDate latestarrive;


    public Customer(String name) {
        this.name = name;
        this.arrivetimes = 1;
        this.latestarrive = LocalDate.now();

    }

    public String getName() {
        return name;
    }

    public int getArrivetimes() {
        return arrivetimes;
    }

    public LocalDate getLatestarrive(LocalDate now) {
        return latestarrive;
    }

    @Override
    public String toString() {
        return "顾客名字" + name + "到店次数" + arrivetimes + "最新到店时间" + latestarrive;

    }

    public void visit() {
        arrivetimes++;
        latestarrive = LocalDate.now();
    }


}
