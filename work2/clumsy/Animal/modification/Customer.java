package modification;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int nums;
    private LocalDate time;

    public Customer(String name,int nums) {
        this.name = name;
        this.nums=nums;
        this.time=LocalDate.now();
    }


    //创造对应的get,set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int number) {
        this.nums = number;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "顾客的名字是"+name+"\n"+
                "顾客到店次数是"+nums+"\n"+
                "顾客到店时间是"+time+"\n"+
                "___________________"+"\n";

    }
}

