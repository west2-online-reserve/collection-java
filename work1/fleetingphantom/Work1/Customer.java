package Work1;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int time;
    private LocalDate arriveDate;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
        LocalDate tmpDate = LocalDate.now();
        time = 1;
        this.setArriveDate(tmpDate);
    }

    public Customer(String name, int year, int month, int day) {
        this.name = name;
        LocalDate tmpDate = LocalDate.of(year, month, day);
        time = 1;
        this.setArriveDate(tmpDate);
    }


    public Customer(String name, int time, LocalDate arriveDate) {
        this.name = name;
        this.time = time;
        this.arriveDate = arriveDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    @Override
    public String toString() {
        return "姓名:" + name
                + "\n到店次数:" + time
                + "\n最新到店时间:" + arriveDate.getYear() + "年" + arriveDate.getMonth().getValue() + "月" + arriveDate.getDayOfMonth() + "日";
    }

}
