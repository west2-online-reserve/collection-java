package collection

public class ChineseFieldDog extends Animal {
    //是否注射疫苗
    private boolean isVaccineInjected=true;
        @Override
        public String toString() {
            return "ChineseFieldDog name:"+name+" age:"+age
                    +" sex:"+sex+" 剩余数量： "+amount+" 价格：" +price+" 是否注射狂犬病疫苗："+isVaccineInjected;
        }
        public ChineseFieldDog (String name,int age,char sex,boolean isVaccineInjected) {
            super(name,age,sex,150);
            this.isVaccineInjected=isVaccineInjected;
        }

}
