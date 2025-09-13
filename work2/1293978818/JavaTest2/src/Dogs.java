/**
 * 作为动物类的子类之一
 * @author 1293978818
 * @version 1.0
 */
public class Dogs extends AbstractAnimal{

    private boolean isVaccineInjected;

    public Dogs(String animalName, int animalAge, String sex,boolean isVaccineInjected) {
        super(animalName, animalAge, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        String temp = "该中华田园犬的名字为" + animalName + "，年龄为" + animalAge + "，性别为" + sex + "，价格为" + animaiPrice;
        if(isVaccineInjected){
            return temp + "，已打狂犬疫苗";
        }else{
            return temp + "，未打狂犬疫苗";
        }
    }

      
}
