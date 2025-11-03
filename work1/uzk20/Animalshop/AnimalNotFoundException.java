package work1.Animalshop;

class AnimalNotFoundException extends RuntimeException{
    //题目给的是AnimalNotFountException，是打错了吗，我这里改成了Found应该没问题吧

    public AnimalNotFoundException() {
    }

    public AnimalNotFoundException(String error){
        super(error);
    }
}
