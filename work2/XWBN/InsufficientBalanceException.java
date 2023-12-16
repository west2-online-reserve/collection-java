/**
 *@Date：2023/10/18
 *@Author：XWBN
 */

package XWBN2;

public class InsufficientBalanceException extends RuntimeException {
    private String animalName;
    private double account;

    public InsufficientBalanceException(String aniName, double account) {
        this.animalName = aniName;
        this.account = account;
    }

    @Override
    public String toString() {
        return "账户余额不足无法购买" + animalName + '\n';
    }

}
