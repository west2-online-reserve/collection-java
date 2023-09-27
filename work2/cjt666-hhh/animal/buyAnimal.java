package animal;

import java.time.LocalDate;
import java.util.ArrayList;

public interface buyAnimal {
//这里就是按照任务定义的一个接口
    public abstract void buyCat();

    public abstract void buyDog(Dog d);

    public abstract void treatCustomer(Customer c );


    public abstract void offDuty(LocalDate localDate);








}
