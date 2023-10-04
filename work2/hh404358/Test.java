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

            try{
                myAnimalShop.WelcomeCustomer();
            }
            catch(AnimalNotFoundException e){
                e.toString();
            }
            catch (StoreNotOpen e){
                e.toString();
            }

            System.out.println("是否停止营业：（0：yes  1：no)");
            Scanner scanner=new Scanner(System.in);
            int choice=scanner.nextInt();
            if(choice==0) {
                break;
            }
        }
        //test close
        myAnimalShop.Close();

    }
}