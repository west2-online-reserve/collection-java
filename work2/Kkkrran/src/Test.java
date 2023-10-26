import java.time.LocalDate;
import java.util.Random;
/**
 * Date 2023/10/22  11:49
 *
 * @author Kkkrran
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) {
        Random r = new Random();

        //数据规模为 10000 时 需要超过5s的时间
        final int size = 100;
        MyAnimalShop shop = new MyAnimalShop();
        Customer[] customers = new Customer[size];
        Animal[] dogs = new Animal[size];
        Animal[] cats = new Animal[size];
        Animal[] mice = new Animal[size];

        shop.setIfPrintAnimals(false);
        // 必须先getMoney再open，否则profit无法正常计算
        shop.getMoney(99999999999999999.0);
        shop.open();

        for (int i=0;i<size;i++){
            customers[i] = new Customer(randomString(6,false),r.nextInt(10)+1
                    ,LocalDate.of(2000,1,1));
            dogs[i] = new Dog(randomString(5,false),r.nextInt(7)+1,
                    "male",r.nextDouble()*(r.nextInt(1000)+1),true);
            cats[i] = new Cat(randomString(5,false),r.nextInt(7)+1,
                    "male",r.nextDouble()*(r.nextInt(10000)+1));
            mice[i] = new Mouse(randomString(5,false),r.nextInt(7)+1,
                    "male",r.nextDouble()*(r.nextInt(100)+1),r.nextDouble());
            shop.buyIn(dogs[i]);
            shop.buyIn(cats[i]);
            shop.buyIn(mice[i]);
        }

        for(int i=0;i<size;i++){
            shop.customerReception(customers[i],"mOuSe");
            shop.customerReception(customers[i],"Cat");
            shop.customerReception(customers[i],"Dog");
        }


        shop.close();


    }
    static String randomString(int length,boolean hasNum) {
        Random random = new Random();
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allCharacters;
        // create a super set of all characters
        if(hasNum){
            allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
        }else {
            allCharacters = alphabetsInLowerCase + alphabetsInUpperCase;
        }
        // initialize a string to hold result
        StringBuffer randomString = new StringBuffer();
        // loop for 10 times
        for (int i = 0; i < length; i++) {
            // generate a random number between 0 and length of all characters
            int randomIndex = random.nextInt(allCharacters.length());
            // retrieve character at index and add it to result
            randomString.append(allCharacters.charAt(randomIndex));
        }
        return randomString.toString();
    }
}


