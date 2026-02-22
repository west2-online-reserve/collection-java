## Java-work1

其实我有点不理解serveCustomer函数中需要出售动物是要出售动物列表中的什么动物，所以我默认使用了动物list的第一位

```java
public void serveCustomer(Customer customer) {
        if (!isWorking) {
            System.out.println("宠物店没有营业");
            return;
        }
        listMemory.add(customer);
        System.out.println("正在招待顾客" + customer.toString());
        if (list.isEmpty()) {
            throw new AnimalNotFountException("目前没有宠物可以出售");
        }
        Animal soldAnimal = this.list.get(0);//这里不知道顾客想要买什么动物默认买动物列表的第一个动物
        list.remove(0);
        balance += soldAnimal.getPrice();
        profitDay += soldAnimal.getPrice();
        System.out.println("成功卖出" + soldAnimal.toString());
    }
```

