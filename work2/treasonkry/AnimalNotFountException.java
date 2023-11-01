public class AnimalNotFountException extends RuntimeException{
    private int num;

    public AnimalNotFountException(int num){
         this.num = num;
    }



    @Override
    public String toString() {
        return "AnimalNotFountException: animal="+this.num;

    }
}
