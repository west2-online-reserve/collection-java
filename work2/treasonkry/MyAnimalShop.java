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

        if((this.money-animal.getPrice())<0){
            throw new InsufficientBalanceException(money);
        }else {
            this.money-=animal.getPrice();
            animalList.add(animal);
        }
    }

    @Override
    public void treatCustomer (Customer customer) throws AnimalNotFountException {
        if(this.isOpened==true){
            customers.add(customer);
            ArrayList<Animal> buying = new ArrayList<Animal>();
            int a = buying.size();
            for (int i = 0; i < customer.getBuyAnimal().size(); i++) {
                for (int j = 0; j < animalList.size(); j++) {
                    if(animalList.get(j).equals(customer.getBuyAnimal().get(i))){
                        buying.add(customer.getBuyAnimal().get(i));
                    }
                }
            }
            if(buying.size()==a){
                throw new AnimalNotFountException(customer.getBuyAnimal().size());
            }else {
                for (int i = 0; i < customer.getBuyAnimal().size(); i++) {
                    money+=buying.get(i).getPrice();
                }
                for (int i = 0; i < buying.size(); i++) {
                    animalList.remove(buying.get(i));
                }
                customer.setTime(customer.getTime()+1);
                customer.setLatestTime(LocalDate.now());
                System.out.println(customer.getBuyAnimal().toString());
            }
        }

    }


    @Override
    public void close() {
        LocalDate localDate = LocalDate.now();
        this.isOpened = false;
        this.dayEarning = 0;
        for (int i = 0; i < this.customers.size(); i++) {

            if(localDate.equals(this.customers.get(i).getLatestTime())){
                for (int j = 0; j < this.customers.get(i).getBuyAnimal().size(); j++) {
                    this.dayEarning+=this.customers.get(i).getBuyAnimal().get(j).getPrice();

                }
            }
        }
        System.out.println("今天的利润为"+this.dayEarning);
    }
    public void open(){
        this.isOpened=true;
    }
}

