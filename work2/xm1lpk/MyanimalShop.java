import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class MyanimalShop implements AnimalShop{
    Boolean isopen=true;
    Double num;
    Double profit=10.00;
    /**
     * 店内余额
     **/
    List<Animal> animalArrayList = new ArrayList<>();
    List<Customer> customerArrayList = new ArrayList<>();
    public void addanimal(Animal newanimal) {
        if(num< newanimal.price){
            throw new InsufficientBalanceException("购买失败，原因：余额不足");
        }
        else {
            animalArrayList.add(newanimal);
            num = num - newanimal.price;
        }
    }
    public void dealCustomers(Customer customer){
        customerArrayList.add(customer);
        Scanner scanner = new Scanner(System.in);
        if (animalArrayList.size() == 0) {
            throw new AnimalNotFountException("购买失败，原因：本店已无可售动物");
        }
        else {
            int temp;
            temp = scanner.nextInt();
            if (temp < animalArrayList.size()) {
                System.out.println(animalArrayList.get(temp).toString());
                num = animalArrayList.get(temp).price + num + profit;
                animalArrayList.remove(temp);
                }
                customer.setCount(customer.getCount() + 1);
                customer.setArriveDay(LocalDate.now());
            }
        }

    public void Isopened(){
        isopen=false;
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println("今日第" + (i + 1) + "位顾客的信息为 " + customerArrayList.get(i).toString());
        }
        System.out.println("宠物店余额为："+num);
        System.out.println("宠物店还有：");
        for(int j = 0; j < animalArrayList.size(); j++){
            System.out.println(animalArrayList.get(j).toString());
        }
    }

}
