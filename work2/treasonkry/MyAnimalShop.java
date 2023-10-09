import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop{
    protected double money;
    protected ArrayList<Animal> animalList = new ArrayList<Animal>();
    protected ArrayList<Customer>  customers = new ArrayList<Customer>();
    protected boolean isOpened;
    protected double dayEarning;
    //重写一个toString，用于测试
    public String toString(){
        return "余额："+this.money+"\n"+"是否开放："+this.isOpened;
    }

    @Override
    public void purchase(Animal animal) throws InsufficientBalanceException {
        animalList.add(animal);
        if((this.money-animal.getPrice())<0){
            throw new InsufficientBalanceException(money);
        }else {
            this.money-=animal.getPrice();
        }


    }

    @Override
    public void treatCustomer (Customer customer) throws AnimalNotFountException {
        customers.add(customer);
        if(animalList.size()==0){
            throw new AnimalNotFountException(animalList.size());
        }else {
            animalList.remove(customer.getBuyAnimal());
            money+=customer.getBuyAnimal().getPrice();
            customer.setTime(customer.getTime()+1);
            System.out.println(customer.getBuyAnimal().toString());
        }

    }


    @Override
    public void close() {
        LocalDate localDate = LocalDate.now();
        this.isOpened = false;
        this.dayEarning = 0;
        for (int i = 0; i < this.customers.size(); i++) {

            if(localDate.equals(this.customers.get(i).getLatestTime())){
                this.dayEarning+=this.customers.get(i).getBuyAnimal().getPrice();
            }
        }
        System.out.println("今天的利润为"+this.dayEarning);
         }

        


    }

