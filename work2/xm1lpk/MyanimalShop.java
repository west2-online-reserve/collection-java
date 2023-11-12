package peikailv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyanimalShop implements AnimalShop{
    private Boolean isopen=false;
    private Double num=100.0;
    /**
     * 店内余额
     **/
    private List<Animal> animalArrayList = new ArrayList<>();
    private List<Customer> customerArrayList = new ArrayList<>();

    public void setNum(Double num) {
        this.num = num;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    @Override
    public void addanimal(Animal newanimal) {
        if(num< newanimal.price){
            throw new InsufficientBalanceException("购买失败，原因：余额不足"+"\n"+"购买失败的动物信息："+"\n"+newanimal);
        }
        else {
            animalArrayList.add(newanimal);
            num = num - newanimal.price;
        }
    }
    @Override
    public void dealCustomers(Customer customer){
        if(!isopen) {
            System.out.println("店铺已歇业，无法购买");
            return ;
        }

        Scanner scanner = new Scanner(System.in);
        if (animalArrayList.size() == 0) {
            throw new AnimalNotFountException("购买失败，原因：本店已无可售动物"+"\n"+"购买失败的顾客信息："+"\n"+customer);
        }
        else {
            int temp;
            temp = scanner.nextInt();
            if (temp>=0 && temp < animalArrayList.size()) {
                System.out.println(animalArrayList.get(temp).toString());
                num = animalArrayList.get(temp).price + num;
                animalArrayList.remove(temp);
            }
            customer.setCount(customer.getCount() + 1);
            customer.setArriveDay(LocalDate.now());
            customerArrayList.add(customer);
        }
    }
    @Override
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
