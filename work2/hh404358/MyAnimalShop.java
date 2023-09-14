class MyAnimalShop implements  AnimalShop{
    double res;
    ArrayList<Animal>animals;
    ArrayList<Customer>customers;
    boolean isOpen;

    public MyAnimalShop(double res, ArrayList<Animal> animals, ArrayList<Customer> customers, boolean isOpen) {
        this.res = res;
        this.animals = animals;
        this.customers = customers;
        this.isOpen = isOpen;
    }

    public boolean isOpen(LocalDateTime time){
        isOpen=false;
        switch(time.getDayOfWeek()){
            case MONDAY : case TUESDAY:case WEDNESDAY:case THURSDAY:case FRIDAY:
                if(time.getHour()>8&&time.getHour()<22)
                    isOpen=true;
                break;
            default:
                isOpen=false;
        }
        return isOpen;
    }

    //介于不可能买只小动物的，设置此函数~~~
    public void Buy(Animal x,int num){
        if(res-num*x.price<0)throw new InsufficientBalanceException();
        res-=num*x.price;
        animals.add(x);

    }

    public void BuyList(){
        while(true){
            System.out.println("请输入需要买的动物：");
            Scanner scanner=new Scanner(System.in);
            String animal=scanner.nextLine();
            System.out.println("请输入要买的数量：");
            int num=scanner.nextInt();
            boolean flag=false;
            while(flag){
                switch(animal){
                    case "ChineseDog":
                        ChineseDog chineseDog=new ChineseDog();
                        try{
                            Buy(chineseDog,num);
                        }
                        catch(InsufficientBalanceException e){
                            e.toString();
                        }
                        break;
                    case "Cat":
                        Cat cat=new Cat();
                        try{
                            Buy(cat,num);
                        }
                        catch(InsufficientBalanceException e){
                            e.toString();
                        }
                        break;
                    case "Rabbit":
                        Rabbit rabbit=new Rabbit();
                        try{
                            Buy(rabbit,num);
                        }
                        catch(InsufficientBalanceException e){
                            e.toString();
                        }
                        break;
                    default:
                        System.out.println("输入错误，请重新输入：");
                        flag=true;
                        break;
                }
            }


            System.out.println("是否继续购买（0：no  1:yes)");
            int choice=scanner.nextInt();
            if(choice==0)break;

        }

    }
    @Override
    public void WelcomeCustomer(Customer c) {
        if(!isOpen) {
            throw new StoreNotOpen();
        }
        if(animals.isEmpty()){
            throw new AnimalNotFoundException();
        }
        customers.add(c);

        boolean flag=false;
        for (Iterator<Animal> it=animals.iterator();it.hasNext();) {
            Animal animal=it.next();
            if(animal==c.like){
                flag=true;
                it.remove();
                break;
            }
        }
        System.out.println(c.like.toString());
        res+=c.like.price;
//        if(!animals.contains(c.like)){
//            System.out.println("无此顾客喜欢的动物！");
//            return;
//        }


        //remove的多线程错误ConcurrentModificationException注意！！！
        // animals.remove(c.like);


    }
    public void Close(){
        double sum=0.0;
        for (Customer customer:customers) {
            if(isOpen(customer.t)){
                System.out.println(customer.toString());
                sum+=customer.like.price;
            }
        }
        System.out.println("今天盈利："+sum);


    }

    @Override
    public String toString() {
        return "MyAnimalShop\n{" +
                "\nres=" + res +
                ",\n animals=" + animals +
                ",\n customers=" + customers +
                ",\n isOpen=" + isOpen +
                "\n}";
    }
}
