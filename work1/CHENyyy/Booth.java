package work1;

public class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getTota(){
        return tota;
    }
    public boolean getIsClosed(){
        return isClosed;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTota(int tota){
        this.tota = tota;
    }
    public void setIsClosed(boolean isClosed){
        this.isClosed = isClosed;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nNAME: " + name + "\nTOTA: " + tota + "\nISCLOSED: " + isClosed;
    }

    public Booth(){

    }
    public Booth(long id, String name, boolean isClosed){
        this.id = id;
        this.name = name;
        this.isClosed = isClosed;
    }

    public static void purchase(Booth booth, int num){
        boolean isFlag = true;
        if(num <= 0){
            System.out.println("The number of watermelons you need to buy should be positive!");
            isFlag = false;
        }
        if(booth.isClosed == true){
            System.out.println("The business is off the stall rectification!");
            isFlag = false;
        }
        if(num > booth.tota){
            System.out.println("Bought too many watermelons. They don't have enough watermelons.");
            isFlag = false;
        }
        if(isFlag){
            booth.tota -= num;
            System.out.println("Congratulations on the watermelon!");
        }
    }

    public void restock(int num){
        if(num > 200 || num < 0){
            System.out.println("Wrong number of watermelons in stock! Failed to stock!");
        }else{
            tota += num;
        }
    }

    public static void closeBooths(Booth[] booths){
        for(int i = 0; i < booths.length; i++){
            if(booths[i].isClosed == true){
                System.out.println(booths[i].toString());
            }else {
                booths[i].isClosed = true;
            }
        }
    }
}
