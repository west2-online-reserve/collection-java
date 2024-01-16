package bean;
//动物未找到异常类
public class AnimalNotFountException extends RuntimeException{
    private int id;
    public AnimalNotFountException(int id) {
        this.id=id;
    }

    public AnimalNotFountException() {
    }
    public void showProblem(){
        if(id==-1000000)
            System.out.println("宠物店已没有动物了");
        else
            System.out.println("没有找到序号为"+id+"的动物");
    }
}
