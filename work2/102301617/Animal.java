
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

    public Animal(String name, int age, double price,String sex,int cost) {
        this.name = name;
        this.age = age;

        this.price =  price;

        this.sex= sex;
        this.cost=cost;
    }

    public abstract String toString ();




}
