public class AnimalNotFountException extends RuntimeException{
    int num;

    public AnimalNotFountException(int num){
         this.num = num;
    }



    @Override
    public String toString() {
        return "AnimalNotFountException";

    }
}
