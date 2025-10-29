public class ChineseVillageDog extends Animal {
        // 变量: isVaccineInjected(boolean 是否注射狂犬病疫苗)
        private boolean isVaccineInjected;

        // 构造方法
        public ChineseVillageDog(String animalName, int age, String sex, boolean isVaccineInjected) {
            // 调用父类构造方法，设置价格为100元
            super(animalName, age, sex, 100);
            this.isVaccineInjected = isVaccineInjected;
        }

        @Override
        public String toString() {
            return "中华田园犬{" +
                    "名字='" + getAnimalName() + '\'' +
                    ", 年龄=" + getAge() +
                    ", 性别='" + getSex() + '\'' +
                    ", 价格=" + getPrice() +
                    ", 是否注射狂犬病疫苗=" + isVaccineInjected +
                    "}";
        }

    // getter和setter方法
    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
    }


