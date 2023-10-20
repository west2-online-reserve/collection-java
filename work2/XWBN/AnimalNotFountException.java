/**
 *@Date：2023/10/18
 *@Author：XWBN
 */

package XWBN2;

public class AnimalNotFountException extends RuntimeException {
    private String animalName;

    public AnimalNotFountException(String animalName) {
        this.animalName = animalName;
    }

    public String toString() {
        return "宠物店无客户想要购买的宠物" + this.animalName + '\n';
    }
}
