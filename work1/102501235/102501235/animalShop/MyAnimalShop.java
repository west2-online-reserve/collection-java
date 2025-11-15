package animalShop;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private double earning;
    private List<Animal> animals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animals = animals;
        this.customers = customers;
        this.isOpen = true;
        this.earning = 0;
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if(balance>animal.price){
            animals.add(animal);
            balance = balance - 50;
            earning = earning - 50;
            System.out.println("成功购入"+animal.toString());
        }
        else{
            throw new InsufficientBalanceException("余额不足，无法购买动物"+animal.toString());
        }
    }

    @Override
    public void serveCustomer(Customer customer,String animalName) {
        customers.add(customer);
        Animal toSell = null;
        Iterator<Animal> a = animals.iterator();
        while (a.hasNext()){
            Animal a1 = a.next();
            if(a1.name.equals(animalName)){
                toSell = a1;
                a.remove();
                break;
            }
        }
        if(toSell == null){
            throw new AnimalNotFountException(animalName+"不存在");
        }else{
            balance += toSell.price;
            earning += toSell.price;
            System.out.println("售出动物"+toSell.toString());
        }
    }

    @Override
    public void closeShop(LocalDate today) {
        isOpen = false;
        System.out.println("歇业");
        for(Customer c:customers){
            System.out.println("顾客信息:"+"\n"+c);
        }
        System.out.println("今天的余额是："+balance);
        System.out.println("今天的利润是："+earning);
    }

    public void printArray(int[] arr,int[] arr1){
        int m=Math.max(arr.length,arr1.length);
        int j = 0;
        int k = 0;
        for(int i=0;i<m;i++){
            if(j<arr.length){
                System.out.print(arr[j]+" ");
                j++;
            }
            if(k<arr.length){
                System.out.print(arr1[k]+" ");
                k++;
            }
        }
        System.out.println();
    }
    public boolean isValidEmail(String email) {
        return email.contains("@");
    };
}
