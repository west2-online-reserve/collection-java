package work2;

import java.time.LocalDate;

/**
 * @author FAN
 */
public class Customer {
    private String name;
    private int count;
    private LocalDate time;

    public Customer(String name, int count) {
        this.name = name;
        this.count = count;
        this.time = LocalDate.now();
    }

    @Override
    public String toString() {
        return "姓名:" + name + "\t来过的次数:" + count + "\t最近一次来的时间:" + time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

}
