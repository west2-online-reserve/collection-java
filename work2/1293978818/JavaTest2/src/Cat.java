/**
 * 作为动物类的子类之一
 * @author 1293978818
 */
public class Cat extends AbstractAnimal{

    
    public Cat(String animalName, int animalAge, String sex) {
        super(animalName, animalAge, sex, 200);
    }

    @Override
    public String toString() {
        return "该猫猫的名字为" + animalName + "，年龄为" + animalAge + "，性别为" + sex + "，价格为" + animaiPrice;
        
    }
    
    
}
