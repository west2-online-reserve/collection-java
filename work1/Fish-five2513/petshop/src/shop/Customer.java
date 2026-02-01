// Customer.java
package shop;

import java.time.LocalDate;
import java.util.Random;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisitTime;
    private CustomerType type;
    private String introduction;
    private int day=1;
    // 顾客类型枚举
    public enum CustomerType {
        RESTAURANT_OWNER("餐厅老板"),
        HOUSEWIFE("家庭主妇"),
        CHANG_E("嫦娥"),
        ORDINARY("普通顾客");

        private final String displayName;

        CustomerType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // 预定义的顾客模板
    private static final String[] RESTAURANT_OWNERS = {"王老板", "李老板", "张老板"};
    private static final String[] HOUSEWIVES = {"李太太", "王太太", "张太太"};
    private static final String[] CHANG_ES = {"嫦娥姐姐", "太阴星君", "月宫仙子"};
    private static final String[] ORDINARY_CUSTOMERS = {"小明", "小红", "小刚", "小丽", "老张"};

    public Customer(String name,MyAnimalShop  shop) {
        this.name = name;
        this.visitCount = 0;
        this.lastVisitTime = LocalDate.now();
        this.type = CustomerType.ORDINARY;
        this.introduction = "一个普通顾客";
        this.day=shop.getCurrentdayDay();
    }

    public Customer(String name, CustomerType type, MyAnimalShop shop) {
        this.name = name;
        this.visitCount = 0;
        this.lastVisitTime = LocalDate.now();
        this.type = type;
        this.introduction = generateIntroduction(type);
        this.day=shop.getCurrentdayDay();
    }

    public void visit() {
        this.visitCount++;
        this.lastVisitTime = LocalDate.now();
    }
    public int getDay() {
        return day;
    }


    public CustomerType getType() {
        return type;
    }

    // 根据动物类型获取调整后的价格
    public double getAdjustedPrice(Animal animal) {
        double originalPrice = animal.price*2;

        switch (type) {
            case RESTAURANT_OWNER:
                if (animal instanceof ChineseRuralDog) {
                    return originalPrice * 1.5; // 狗加价50%
                } else if (animal instanceof Cat) {
                    return originalPrice * 0.5; // 猫降价50%
                }
                break;
            case HOUSEWIFE:
                if (animal instanceof Cat) {
                    return originalPrice * 1.5; // 猫加价50%
                } else if (animal instanceof Rabbit) {
                    return originalPrice * 0.5; // 兔子降价50%
                }
                break;
            case CHANG_E:
                if (animal instanceof Rabbit) {
                    return originalPrice * 1.5; // 兔子加价50%
                } else if (animal instanceof ChineseRuralDog) {
                    return originalPrice * 0.5; // 狗降价50%
                }
                break;
        }

        return originalPrice; // 默认价格
    }

    // 随机生成一个顾客
    public static Customer getRandomCustomer(MyAnimalShop shop) {
        Random random = new Random();
        CustomerType[] types = CustomerType.values();
        CustomerType randomType = types[random.nextInt(types.length)];

        String name;
        switch (randomType) {
            case RESTAURANT_OWNER:
                name = RESTAURANT_OWNERS[random.nextInt(RESTAURANT_OWNERS.length)];
                break;
            case HOUSEWIFE:
                name = HOUSEWIVES[random.nextInt(HOUSEWIVES.length)];
                break;
            case CHANG_E:
                name = CHANG_ES[random.nextInt(CHANG_ES.length)];
                break;
            default:
                name = ORDINARY_CUSTOMERS[random.nextInt(ORDINARY_CUSTOMERS.length)];
                break;
        }

        return new Customer(name, randomType, shop);
    }

    public String getName() {
        return name;
    }

    private String generateIntroduction(CustomerType type) {
        switch (type) {
            case RESTAURANT_OWNER:
                return "他是一个餐厅老板，他家的狗肉很火爆，不过他的狗肉来源似乎断了，听说他最近在研究麻辣兔头，我们真的要卖宠物给他吗？";
            case HOUSEWIFE:
                return "她是一名家庭主妇，最近一直困扰于家里的老鼠，她似乎非常害怕啮齿类动物，也许我们可以卖只宠物来帮助她解决家里的鼠患";
            case CHANG_E:
                return "来自广寒宫的仙子，她的玉兔似乎走丢了，也许我们可以推荐新宠物给她，不过要注意不要让这些宠物打破广寒宫的清净";
            default:
                return "一个普通顾客";
        }
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "顾客{" +
                "姓名='" + name + '\'' +
                ", 类型=" + type.getDisplayName() +
                ", 到店次数=" + visitCount +
                ", 最新到店时间=" + lastVisitTime +
                ", 第" +day+"天"+
                '}'+
                "\n简介："+introduction;
    }
}
