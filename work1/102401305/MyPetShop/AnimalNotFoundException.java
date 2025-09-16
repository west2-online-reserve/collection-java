package MyPetShop;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException() {
        super();
    }
}
//Idea初始创建有String类型Message 但是我删去也没报错？所以这会是关键吗