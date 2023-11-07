public   class ChineseRuralDog extends Animal {
        private  boolean isvaccineInjected;
    // 成员变量
        // 构造方法
        public ChineseRuralDog(String name, int age, String gender, boolean isvaccineInjected) {
            super(name, age, gender, 100);
            this.isvaccineInjected = isvaccineInjected;
        }
    // 获取疫苗注射情况
        public boolean isVaccineInjected()
        {
            return isvaccineInjected;
        }

    public void setIsvaccineInjected(boolean vaccineInjected) {
        this.isvaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "isvaccineInjected=" + isvaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}

