/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: InsufficientBalanceException
 * @description（类描述）: 找不到动物异常类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class AnimalNotFountException extends BaseException {
    public AnimalNotFountException(String s) {
        super(s);
    }

    public AnimalNotFountException() {
        super(ErrorCode.NOTFOUND_ANIMAL);
    }

    /**
     * @description输出详细的错误信息
     */
    public void printDetailedError() {
        this.printError();
        System.out.println("找不到的动物");
    }
}
