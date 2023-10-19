import java.time.LocalDate;

/**
 * @author XiaoxiongMeng
 */
public class Customer {
    private String name;

    private int times;
    // 到店次数

    private LocalDate latest;
    // 最近到店时间

    public Customer(String name){
        this.name = name;
        this.times = 0;
        this.latest = LocalDate.now();
    }

    public void comeStore(){
        this.times++;
        this.latest = LocalDate.now();
        System.out.println("顾客"+ name +"光临商店！");
    }

    public LocalDate getLatest() {
        return latest;
    }

    @Override
    public String toString(){
        return "客户姓名：" + name +
                "\n光临次数" + times +
                "\n最近光临时间" + latest;
    }
}
