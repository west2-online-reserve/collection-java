package petStore;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;
    private static double PRICE;

    public Animal() {

    }

    public Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public boolean equals(Animal a) {

        if (this == a) {
            return true;
        }
        return (this.name.equals(a.getName())) &&
                (this.getAge() == a.getAge()) &&
                (this.getGender().equals(a.getGender()));

    }

}
