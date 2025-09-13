import java.time.LocalDate;

public interface AnimalShop {
    //判断是或成功买入宠物
    public void buyAnimal();
    //招待顾客
    public void serveCustomer(Customer customer);
    //是否营业
    public boolean isOpen(LocalDate localDate);
}
