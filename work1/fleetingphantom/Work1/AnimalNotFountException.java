package Work1;

public class AnimalNotFountException extends RuntimeException {
    private int num;
    private int max;

    public AnimalNotFountException() {
    }

    public AnimalNotFountException(String message, int num, int max) {
        super(message);
        this.num = num;
        this.max = max;
    }

    @Override
    public String toString() {
        return "未找到所要购买的动物,预购动物编号为:" + num + "现有动物编号为1~" + max;
    }
}
