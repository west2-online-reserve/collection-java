class Test{
    public void test(){
        //创建一个我的宠物店
        ArrayList<Animal>animals=new ArrayList<>();
        ChineseDog chineseDog=new ChineseDog();
        animals.add(chineseDog);
        Cat cat=new Cat();
        animals.add(cat);
        Rabbit rabbit=new Rabbit();
        animals.add(rabbit);
        ArrayList<Customer>customers=new ArrayList<>();
        MyAnimalShop myAnimalShop=new MyAnimalShop(100,animals,customers,true);
        System.out.println(myAnimalShop.toString());
        //test BuyList
        //myAnimalShop.BuyList();

        //test WelcomeCustomer
        while(true){
            System.out.println("开始迎客：");
            System.out.println("请输入顾客姓名：");
            Scanner scanner=new Scanner(System.in);
            String name=scanner.nextLine();
            System.out.println("请输入顾客到店次数：");
            int times= scanner.nextInt();
            System.out.println("请输入顾客最新到店时间：");
            int year=scanner.nextInt();
            int month=scanner.nextInt();
            int day=scanner.nextInt();
            int hour=scanner.nextInt();
            int minute=scanner.nextInt();
            int second=scanner.nextInt();
            LocalDateTime t= LocalDateTime.of(year,month,day,hour,minute,second);
            System.out.println("请输入顾客喜欢的动物：");
            String like=scanner.next();
            boolean flag=true;
            Customer c=new Customer();
            while(flag)
                switch(like){
                    case "ChineseDog":
                        ChineseDog chineseDog1=new ChineseDog();
                        c=new Customer(name,times,t,chineseDog1);
                        flag=false;
                        break;
                    case "Cat":
                        Cat cat1=new Cat();
                        c=new Customer(name,times,t,cat1);

                        flag=false;
                        break;
                    case "Rabbit":
                        Rabbit rabbit1=new Rabbit();
                        c=new Customer(name,times,t,rabbit1);
                        flag=false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入：");
                        break;
                }
            try{
                myAnimalShop.WelcomeCustomer(c);
            }
            catch(AnimalNotFoundException e){
                e.toString();
            }
            catch (StoreNotOpen e){
                e.toString();
            }

            System.out.println("是否停止营业：（0：yes  1：no)");
            int choice=scanner.nextInt();
            if(choice==0)break;
        }
        //test close
        myAnimalShop.Close();

    }
}