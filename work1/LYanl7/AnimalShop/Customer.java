import java.time.LocalDate;

public class Customer {
    private String name;                // 姓名
    private int frequency;              // 到店次数
    private LocalDate lastVisitDate;    // 上次到店日期

    /**
     * 全参构造方法
     * @param name          顾客姓名
     * @param frequency     到店次数
     * @param lastVisitDate 上次到店日期
     */
    public Customer(String name, int frequency, LocalDate lastVisitDate) {
        this.name = name;
        this.frequency = frequency;
        this.lastVisitDate = lastVisitDate;
    }

    /**
     * 按格式返回顾客信息
     * <p> 格式要求:
     * <ul>
     *     <li>姓名: 顾客姓名</li>
     *     <li>到店次数: 顾客到店次数</li>
     *     <li>上次到店日期: 顾客上次到店日期</li>
     * </ul>
     * </p>
     * @return 格式化的顾客信息
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名: ").append(name).append("\n");
        sb.append("到店次数: ").append(frequency).append("\n");
        sb.append("上次到店日期: ").append(lastVisitDate.toString()).append("\n");
        return sb.toString();
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public int getFrequency() {
        return frequency;
    }
}
