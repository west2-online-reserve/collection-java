package animal;//这里也是按照任务定义的一个自定义异常，就是顾客想要买宠物，但是卖光了

public class AnimalNotFoundException extends RuntimeException{

    public AnimalNotFoundException() {
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }
}
