package animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MyAnimalShop implements AnimalBuyable {

    double money;
    boolean isOpen;


    public MyAnimalShop(double money, boolean isOpen) {

        this.money = money;
        this.isOpen = isOpen;
    }


    ArrayList<Animal> animalForSell = new ArrayList<>();//放店里的小动物

    ArrayList<Customer> customersCome = new ArrayList<>();//把来店里的顾客记录下来

    ArrayList<Animal> animalSold = new ArrayList<>();//等会儿作为计算总营业额的工具

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    public ArrayList getList1() {
        return animalForSell;
    }

    public void setList1(ArrayList list1) {
        this.animalForSell = list1;
    }


    public ArrayList getList2() {
        return customersCome;
    }

    public void setList2(ArrayList list2) {
        this.customersCome = list2;
    }

    @Override
    public void buyCat(Cat c) {


        if (money < c.cost) {
            throw new InsufficientBalanceException("商店余额为" + this.money + "超出范围");


        } else {
            this.setMoney(this.getMoney() - c.cost);

            this.animalForSell.add(c);

        }
    }

    @Override
    public void buyDog(Dog d) {


        if (money < d.cost) {
            throw new InsufficientBalanceException("商店余额为" + this.money + "超出范围");
        } else {
            this.setMoney(this.getMoney() - d.cost);
            this.animalForSell.add(d);

        }


    }

    @Override
    public void treatCustomer(Customer c) {


        System.out.println("欢迎" + c.name + "的到来");
//这里就是，动物卖完了，抛出异常的函数
        if (animalForSell.isEmpty()) {
            throw new AnimalNotFoundException("动物卖完了");
        } else {
            //如果成功买到宠物，就把顾客放到顾客列表里
            this.customersCome.add(c);

//然后宠物店的余额就增加了
            if (animalForSell.get(0) instanceof Cat) {
                this.money += 200;

                animalSold.add(animalForSell.get(0));

            }
            if (animalForSell.get(0) instanceof Dog) {
                this.money += 100;
                animalSold.add(animalForSell.get(0));

            }
//然后顾客到店次数增加
            this.animalForSell.remove(0);

            c.times++;
        }

    }

    //任务要求的方法，打印出当天顾客信息，以及总营业额
    @Override
    public void offDuty(LocalDate localDate) {


        for (Customer cu : customersCome) {

           if(cu.getDd().equals(localDate)) {
                System.out.println(cu.toString());
               System.out.println(cu.getDd());
            }
        }

        int bonus=0;

        for(Animal a : animalSold)
        {
            if(a instanceof Cat)
            {
                bonus+=200;
            }
            if(a instanceof Dog)
            {
                bonus+=100;
            }

        }

        System.out.println(bonus);
    }



    }



