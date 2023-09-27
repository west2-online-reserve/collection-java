package animal;

import java.time.LocalDate;
import java.util.ArrayList;

//这个算是所学知识的一个运用，就是一个中介类和一个接口接起来，然后后面想要用接口的类直接继承这个，好处是不用重写所有方法
public  abstract class ComeTrue implements buyAnimal{

    @Override
    public void buyCat() {

    }

    @Override
    public void buyDog(Dog d) {

    }

    @Override
    public void treatCustomer(Customer c) {

    }

    @Override
    public void offDuty(LocalDate localDate) {

    }
}
