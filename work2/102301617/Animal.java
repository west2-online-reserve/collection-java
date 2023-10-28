
/***
 * @author 102301617
 *
 *
 * */



public abstract  class Animal {
    protected String name;
    protected int age;
    protected double price;
    protected String sex;
    protected int cost;
    protected Boolean isVaccinated;

    public Animal(String name, int age, double price,String sex,int cost，Boolean isVaccinated) {
        this.name = name;
        this.age = age;

        this.price =  price;

        this.sex= sex;
        this.cost=cost;
    }
@override
    public abstract String toString ();




}
